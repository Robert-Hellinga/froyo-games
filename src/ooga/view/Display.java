package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import ooga.controller.GridGameController;
import ooga.view.screens.SplashScreen;

public class Display {

  private static final String DEFAULT_RESOURCE_BUNDLE_PATH = "ui.Display";
  private static final GridGameController DEFAULT_CONTROLLER = new GridGameController();

  private GridGameController currentController;
  private ResourceBundle resourceBundle;
  private Pane layout;
  private Scene scene;

  public Display() {
    this(DEFAULT_CONTROLLER);
  }

  public Display(GridGameController controller) {
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
