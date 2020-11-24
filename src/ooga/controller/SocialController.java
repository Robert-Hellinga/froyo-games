package ooga.controller;

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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import ooga.exceptions.FileException;
import ooga.fileHandler.DatabaseGame;
import ooga.fileHandler.Resources;
import ooga.model.game.Game;
import ooga.model.player.Player;

/**
 * Class used to interface with Firebase RealtimeDB
 * Updates database with new board values, and listens for changes depending on the turn
 * @author Nate Mela
 */
public class SocialController {

  private static final String KEY_PATH = "../../resources/social/database-key.txt";
  private static final String APP_URL = "https://froyogames-1df28.firebaseio.com/";

  private static final String BOARD_STATE_KEY = "boardState";
  private static final String GAME_TYPE_KEY = "gameType";
  private static final String GAMES_REFERENCE = "/games";
  private static final String GAME_NOT_CREATED_KEY = "GameNotCreated";

  private Resources error;
  private DatabaseReference ref, gameRef;
  private Player player, opponent;
  private Game game;
  private IGameController gameController;

  public SocialController(Player player, Player opponent, IGameController gameController,
      Game game) {
    this.player = player;
    this.opponent = opponent;
    this.gameController = gameController;
    this.game = game;
    error = new Resources(Resources.ERROR_MESSAGES_FILE);
    initializeDB();
  }

  /**
   * Method which connects to the database and throws appropriate errors if unsuccessful
   */
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

  /**
   * Method called to join a game (existing or not existing). Callback requires both methods to be
   * defined, hence the syntax. The callback feeds the DataSnapshot to startGame method
   */
  public void joinGame() {
    ref.child(opponent.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
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

  /**
   * Method which uses the information from joinGame() to either create a new game or join an
   * existing game.
   * @param snapshot contains the initial data in the database when the game is created
   */
  private void startGame(DataSnapshot snapshot) {
    // This logic asks "does the game exist, and is the existing game the same type?
    // If yes, join the game, otherwise, create a new game
    if (snapshot.exists() && game.getGameType().equals(snapshot.child(GAME_TYPE_KEY).getValue())) {
      gameRef = ref.child(opponent.getName());
      waitForOpponentTurn();
    } else {
      Map<String, DatabaseGame> newGame = Map.of(
          player.getName(),
          new DatabaseGame(game.getAllBlockStatesAsString(), game.getGameType())
      );

      ref.setValueAsync(newGame);
      gameRef = ref.child(player.getName());
    }
  }

  /**
   * Method to create a listener for a change in the database which indicates the opponent player
   * has moved. Callback requires all five ChildEventListener() methods to be defined.
   *
   * Once the listener senses a change, it updates the current block states with the new states and
   * forces the game to take a turn.
   */
  private void waitForOpponentTurn() {
    gameController.setClickingEnabled(false);
    gameRef.addChildEventListener(new ChildEventListener() {

      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {
      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
        game.setAllBlockStates((String) dataSnapshot.getValue());
        if (!game.currentPlayerHavePotentialMoves()) {
          updateGame(true);
        } else {
          game.playerTakeTurn();
          gameController.setClickingEnabled(true);
        }

        gameRef.removeEventListener(this);
      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {
      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) {
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        throw new DatabaseException(databaseError.getMessage());
      }
    });

  }

  /**
   * Method which pushes the current game states to the database
   * @param forceTurnSwitch is a boolean used to force the opponent turn. This is currently only
   *                        used by Othello when the opponent has no moves and the turns have to
   *                        cycle.
   */
  public void updateGame(boolean forceTurnSwitch) {
    String dataToUpload = game.getAllBlockStatesAsString();
    if (forceTurnSwitch) {
      dataToUpload += "-1";
    }

    try {
      gameRef.child(BOARD_STATE_KEY).setValue(dataToUpload,
          (databaseError, databaseReference) -> {
            if (databaseError != null) {
              throw new DatabaseException(databaseError.getMessage());
            } else {
              waitForOpponentTurn();
            }
          }
      );
    } catch (NullPointerException e) {
      throw new DatabaseException(error.getString(GAME_NOT_CREATED_KEY));
    }
  }
}
