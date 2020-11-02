package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.view.Display;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // TODO: temporary implementation, need to add language select
        playGame(stage);
    }

    private void playGame(Stage stage) {
        Display display = new Display(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
