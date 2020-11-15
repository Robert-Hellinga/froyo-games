package ooga;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.FroyoController;
import ooga.controller.IFroyoController;
import ooga.fileHandler.Database;
import ooga.view.screens.LanguageScreen;
import ooga.view.screens.SplashScreen;

public class Main extends Application {

    private static final boolean RESIZABLE_WINDOW = false;
    private static final String WINDOW_NAME = "Froyo Games";

    @Override
    public void start(Stage stage) {
        setUpStage(stage);
        startGame(stage);
    }

    private void setUpStage(Stage stage) {
        stage.setResizable(RESIZABLE_WINDOW);
        stage.setTitle(WINDOW_NAME);
    }

    private void startGame(Stage stage) {
        IFroyoController controller = new FroyoController(stage);
        LanguageScreen startScreen = new LanguageScreen(controller);
    }

    public static void main(String[] args) {
//        Database db = new Database();
//        db.addNewGame("idk");

        launch(args);
    }

}
