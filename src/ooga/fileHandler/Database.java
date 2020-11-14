package ooga.fileHandler;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.exceptions.FileException;

public class Database {

  private static final String KEY_PATH = "/Users/nate/Documents/Duke/CS307/final_team11/src/resources/social/froyogames-1df28-8e29e2f7996b.json";
  private static final String APP_URL = "https://froyogames-1df28.firebaseio.com/";

  private Resources error;
  private DatabaseReference ref;

  public Database() {
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

  public void addNewGame(String creatorName) {
    Map<String, DatabaseGame> game = new HashMap<>();
    game.put(creatorName, new DatabaseGame(creatorName, "test"));
    ref.setValueAsync(game);
  }

  public void getGameTurn() {

  }

  public void write() {

  }

  public void read() {

  }
}
