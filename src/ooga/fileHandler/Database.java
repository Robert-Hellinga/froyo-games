package ooga.fileHandler;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.SyncTree.CompletionListener;
import com.google.firebase.database.core.view.Event;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.Coordinate;
import ooga.exceptions.FileException;
import ooga.model.game.Game;
import ooga.model.player.Player;

public class Database {

  private static final String KEY_PATH = "/Users/nate/Documents/Duke/CS307/final_team11/src/resources/social/froyogames-1df28-8e29e2f7996b.json";
  private static final String APP_URL = "https://froyogames-1df28.firebaseio.com/";

  private Resources error;
  private DatabaseReference ref, gameRef;
  private Player creatorPlayer, opponentPlayer;
  private String gameName;
  private Game game;

  public Database(Player creatorPlayer, Player opponentPlayer, Game game) {
    this.creatorPlayer = creatorPlayer;
    this.opponentPlayer = opponentPlayer;
    this.game = game;
    error = new Resources(Resources.ERROR_MESSAGES_FILE);
    initializeDB();
  }

  private void initializeDB() {
//    System.out.println(getClass().getResource("../resources/social/froyogames-1df28-8e29e2f7996b"
//        + ".json").getFile().toString());
    try {
      File file = new File(KEY_PATH);
      FileInputStream serviceAccount = new FileInputStream(file);
      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl(APP_URL)
          .build();
      FirebaseApp.initializeApp(options);
      ref = FirebaseDatabase.getInstance().getReference("/games");
    } catch (FileNotFoundException e) {
      throw new FileException(String.format(error.getString("InvalidCredentialFile"), KEY_PATH), e);
    } catch (IOException e) {
      throw new FileException(String.format(error.getString("InvalidDatabase"), APP_URL), e);
    }
  }

  private void createGame(boolean alreadyExists, Iterable<DataSnapshot> gameData) {
    if(alreadyExists) {
      gameRef = ref.child(opponentPlayer.getName());
      System.out.println("Game already exists, joining...");
      game.setCurrentPlayer(opponentPlayer);
      createTurnListener();
      gameName = opponentPlayer.getName();
    }
    else {
      Map<String, DatabaseGame> newGame = new HashMap<>();
      newGame.put(creatorPlayer.getName(), new DatabaseGame(game.getAllBlockStatesAsString(), game.getGameType()));
      ref.setValueAsync(newGame);
      gameRef = ref.child(creatorPlayer.getName());
      System.out.println("Created new game");
      gameName = creatorPlayer.getName();
    }
  }

  public void addNewGame() {
    ref.child(opponentPlayer.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
          createGame(snapshot.exists(), snapshot.getChildren());
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }


  private void createTurnListener() {
    game.disableTurns();
    gameRef.addChildEventListener(new ChildEventListener() {

      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {}

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
        System.out.println(game.getAllBlockStatesAsString());
        System.out.println("Opponent Moved!");
        game.setAllBlockStates((String) dataSnapshot.getValue());
        System.out.println(game.getAllBlockStatesAsString());
        game.enableTurns();
//        game.notifyObservers();
        game.getOppositePlayer().makePlay(new Coordinate(0, 0));
        gameRef.removeEventListener(this);
      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {}

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

      @Override
      public void onCancelled(DatabaseError databaseError) {}
    });

  }


  public void updateGame() {
//      Map<String, Object> update = new HashMap<>();
//      update.put("boardState", game.getAllBlockStatesAsString());
//      gameRef.updateChildrenAsync(update);
//      createTurnListener();

    gameRef.child("boardState").setValue(game.getAllBlockStatesAsString(), new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        if (databaseError != null) {
          System.out.println("Data could not be saved " + databaseError.getMessage());
        } else {
          System.out.println("Data saved successfully.");
          createTurnListener();
        }
      }
    });


  }
}
