package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.FroyoController;
import ooga.controller.IFroyoController;
import ooga.view.Display;

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
        stage.show();
    }

    private void playGame(Stage stage) {
        IFroyoController controller = new FroyoController(stage);
        //Display display = new Display(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
