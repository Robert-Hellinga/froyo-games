package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ooga.controller.GameController;
import ooga.view.screens.SplashScreen;

public class Display {

  private static final String DEFAULT_RESOURCE_BUNDLE_PATH = "ui.Display";
  private static final GameController DEFAULT_CONTROLLER = new GameController();
  private static final boolean RESIZABLE_WINDOW = false;
  private static final String WINDOW_NAME = "Froyo Games";

  private GameController currentController;
  private ResourceBundle resourceBundle;
  private Pane layout;
  private Scene scene;
  private Stage stage;

  public Display(Stage stage) {
    this(stage, DEFAULT_CONTROLLER);
  }

  public Display(Stage stage, GameController controller) {
    this.stage = stage;
    currentController = controller;
    resourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE_PATH);
    SplashScreen startScreen = new SplashScreen(resourceBundle, currentController, this);
    setNewLayout(startScreen);
    setupStage();
  }

  private void setupStage() {
    stage.setResizable(RESIZABLE_WINDOW);
    stage.setTitle(WINDOW_NAME);
    stage.show();
  }

  public void setNewLayout(Pane layout) {
    this.layout = layout;
    scene = new Scene(layout, layout.getWidth(), layout.getHeight());
    stage.setScene(scene);
  }
}
