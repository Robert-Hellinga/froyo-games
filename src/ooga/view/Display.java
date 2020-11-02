package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import ooga.controller.GameController;
import ooga.view.screens.SplashScreen;

public class Display {

  private static final String DEFAULT_RESOURCE_BUNDLE_PATH = "ui.Display";
  private static final GameController DEFAULT_CONTROLLER = new GameController();

  private GameController currentController;
  private ResourceBundle resourceBundle;
  private Pane layout;
  private Scene scene;

  public Display() {
    this(DEFAULT_CONTROLLER);
  }

  public Display(GameController controller) {
    currentController = controller;
    resourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE_PATH);
    SplashScreen startScreen = new SplashScreen(resourceBundle, currentController, this);
    setNewLayout(startScreen);
  }

  public void setNewLayout(Pane layout) {
    this.layout = layout;
    scene = new Scene(layout, layout.getWidth(), layout.getHeight());
  }

  public Scene getScene() {
    return scene;
  }
}
