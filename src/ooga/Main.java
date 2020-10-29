package ooga;


import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.GameControllerInterface;
import ooga.controller.GameController;

public class Main extends Application {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameControllerInterface controller = new GameController();
    }
}
