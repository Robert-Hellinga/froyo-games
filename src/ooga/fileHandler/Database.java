package ooga.fileHandler;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.SyncTree.CompletionListener;
import com.google.firebase.database.core.view.Event;
import com.google.protobuf.Value;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.crypto.Data;
import ooga.Coordinate;
import ooga.controller.IGameController;
import ooga.exceptions.FileException;
import ooga.model.game.Game;
import ooga.model.player.Player;

public class Database {

  private static final String KEY_PATH = "../../resources/social/database-key.txt";
  private static final String APP_URL = "https://froyogames-1df28.firebaseio.com/";

  private static final String BOARD_STATE_KEY = "boardState";
  private static final String GAMES_REFERENCE = "/games";

  private Resources error;
  private DatabaseReference ref, gameRef;
  private Player creatorPlayer, opponentPlayer;
  private Game game;
  private IGameController gameController;

  public Database(Player creatorPlayer, Player opponentPlayer, IGameController gameController, Game game) {
    this.creatorPlayer = creatorPlayer;
    this.opponentPlayer = opponentPlayer;
    this.gameController = gameController;
    this.game = game;
    error = new Resources(Resources.ERROR_MESSAGES_FILE);
    initializeDB();
  }

  private void initializeDB() {
    try {
      File keyFile = new File(getClass().getResource(KEY_PATH).getPath());
      FileInputStream serviceAccount = new FileInputStream(keyFile);
      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl(APP_URL)
          .build();
      FirebaseApp.initializeApp(options);
      ref = FirebaseDatabase.getInstance().getReference(GAMES_REFERENCE);
    } catch (FileNotFoundException e) {
      throw new FileException(String.format(error.getString("InvalidCredentialFile"), KEY_PATH), e);
    } catch (IOException e) {
      throw new FileException(String.format(error.getString("InvalidDatabase"), APP_URL), e);
    }
  }

  public void joinGame() {
    ref.child(opponentPlayer.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        startGame(snapshot);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        throw new DatabaseException(databaseError.getMessage());
      }
    });
  }

  private void startGame(DataSnapshot snapshot) {
    if(snapshot.exists()) {
      gameRef = ref.child(opponentPlayer.getName());

      // TODO check if game types match up
      System.out.println("Game already exists, joining...");
      waitForOpponentTurn();
    }
    else {
      Map<String, DatabaseGame> newGame = Map.of(
          creatorPlayer.getName(),
          new DatabaseGame(game.getAllBlockStatesAsString(), game.getGameType())
      );

      ref.setValueAsync(newGame);
      gameRef = ref.child(creatorPlayer.getName());
      System.out.println("Created new game");
    }
  }


  private void waitForOpponentTurn() {
    gameController.setClickingEnabled(false);
    gameRef.addChildEventListener(new ChildEventListener() {

      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {}

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
        game.setAllBlockStates((String) dataSnapshot.getValue());
        game.playerTakeTurn();
        gameController.setClickingEnabled(true);
        gameRef.removeEventListener(this);
      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {}

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        throw new DatabaseException(databaseError.getMessage());
      }
    });

  }

  public void updateGame() {
    gameRef.child(BOARD_STATE_KEY).setValue(game.getAllBlockStatesAsString(),
        (databaseError, databaseReference) -> {
          if (databaseError != null) {
            throw new DatabaseException(databaseError.getMessage());
          } else {
            waitForOpponentTurn();
          }
        }
    );
  }
}
