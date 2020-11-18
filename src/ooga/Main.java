package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.FroyoController;
import ooga.controller.IFroyoController;
import ooga.view.screens.LanguageScreen;

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
    launch(args);
  }

}
