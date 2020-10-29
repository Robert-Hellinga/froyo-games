package ooga;


import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.GridGameController;
import ooga.view.Display;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        GridGameController controller = new GridGameController();
        Display display = new Display(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
