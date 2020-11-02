package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.GridGameController;
import ooga.view.Display;

public class Main extends Application {

    private static final boolean RESIZABLE_WINDOW = false;
    private static final String WINDOW_NAME = "Froyo Games";

    @Override
    public void start(Stage stage) {
        // TODO: temporary implementation, need to add language select
        playGame(stage);
    }

    private void playGame(Stage stage) {
        Display display = new Display();
        stage.setScene(display.getScene());
        stage.setResizable(RESIZABLE_WINDOW);
        stage.setTitle(WINDOW_NAME);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
