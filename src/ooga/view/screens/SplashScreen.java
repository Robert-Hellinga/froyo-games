package ooga.view.screens;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import ooga.controller.GameController;
import ooga.view.Display;
import ooga.view.Styleable;
import ooga.view.Util;
import ooga.view.elements.SplashScreenButtonBox;
import ooga.view.elements.SplashScreenTitleBox;

public class SplashScreen extends GridPane implements Styleable {

  private static final String BACKGROUND_STYLE_CLASS = "background";
  private static final String DEFAULT_STYLE_SHEET = "style/default.css";
  private static final int WIDTH = 600;
  private static final int HEIGHT = 450;

  private ResourceBundle resourceBundle;
  private Display display;

  public SplashScreen(ResourceBundle resourceBundle, GameController controller, Display display) {
    this.resourceBundle = resourceBundle;
    this.display = display;
    setStyleSheet(DEFAULT_STYLE_SHEET);

    setWidth(WIDTH);
    setHeight(HEIGHT);

    getColumnConstraints().add(Util.getColumnConstraints(Priority.NEVER, true, WIDTH));

    add(new SplashScreenTitleBox(), 0, 0);
    getRowConstraints().add(Util.getRowConstraints(Priority.NEVER, false, 120));
    getStyleClass().add(BACKGROUND_STYLE_CLASS);

    add(new SplashScreenButtonBox(resourceBundle, controller, display), 0, 1);
    getRowConstraints().add(Util.getRowConstraints(Priority.SOMETIMES, true, 200));
  }

  @Override
  public void setStyleSheet(String stylesheet) {
    getStylesheets().clear();
    Util.setPaneStylesheet(this, stylesheet);
  }

  @Override
  public String getStyleSheet() {
    return getStylesheets().get(0);
  }
}
