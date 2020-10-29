package ooga.view;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import ooga.controller.GridGameController;
import ooga.view.elements.Title;

public class Display implements Styleable {

  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  private static final String DEFAULT_STYLE_SHEET = "style/default.css";
  private static final String DEFAULT_RESOURCE_BUNDLE_PATH = "ui.UI";
  private static final GridGameController DEFAULT_CONTROLLER = new GridGameController();
  private static final String TITLE_KEY = "TITLE";

  private GridGameController currentController;
  private ResourceBundle resourceBundle;
  private BorderPane layout;
  private Scene scene;

  private Title title;

  public Display() {
    this(DEFAULT_CONTROLLER);
  }

  public Display(GridGameController controller) {
    currentController = controller;
    resourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE_PATH);
    layout = new BorderPane();
    scene = new Scene(layout, WIDTH, HEIGHT);
    Util.setSceneStylesheet(scene, DEFAULT_STYLE_SHEET);
    initSplashScreen();
  }

  private void initSplashScreen() {
    title = new Title(Util.getResourceAsString(resourceBundle, TITLE_KEY));
    Util.configureElement(title, Pos.CENTER);
    layout.setTop(title);
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

  public void setResourceBundle(ResourceBundle bundle) {
     resourceBundle = bundle;
  }

  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }
}
