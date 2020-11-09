package ooga;

import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.FroyoController;
import ooga.controller.IFroyoController;
import ooga.view.screens.SplashScreen;

public class Main extends Application {

    private static final boolean RESIZABLE_WINDOW = false;
    private static final String WINDOW_NAME = "Froyo Games";

    @Override
    public void start(Stage stage) {
        setUpStage(stage);
        // TODO: temporary implementation, need to add language select
        playGame(stage);
    }

    private void setUpStage(Stage stage) {
        stage.setResizable(RESIZABLE_WINDOW);
        stage.setTitle(WINDOW_NAME);
    }

    private void playGame(Stage stage) {
        Locale locale = new Locale("en");
        IFroyoController controller = new FroyoController(stage, locale);
        SplashScreen startScreen = new SplashScreen(locale, controller);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
