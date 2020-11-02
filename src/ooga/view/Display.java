package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import ooga.controller.GridGameController;
import ooga.view.screens.SplashScreen;

public class Display implements Styleable {

  private static final String DEFAULT_STYLE_SHEET = "style/default.css";
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
    setNewLayout(new SplashScreen(resourceBundle));
    Util.setSceneStylesheet(scene, DEFAULT_STYLE_SHEET);
    System.out.println("TEST");
  }

  public void setNewLayout(Pane layout) {
    this.layout = layout;
    scene = new Scene(layout, layout.getWidth(), layout.getHeight());
    System.out.println(scene);

  }

  public Scene getScene() {
    return scene;
  }

  @Override
  public void setStyleSheet(String stylesheet) {
    scene.getStylesheets().clear();
    Util.setSceneStylesheet(scene, stylesheet);
  }

  @Override
  public String getStyleSheet() {
    return scene.getStylesheets().get(0);
  }
}
