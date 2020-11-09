package ooga.view.screens;

import java.util.Locale;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import ooga.controller.IFroyoController;
import ooga.fileHandler.Resources;
import ooga.view.Display;
import ooga.view.Styleable;
import ooga.view.Util;
import ooga.view.elements.SplashScreenButtonBox;
import ooga.view.elements.SplashScreenTitleBox;

public class SplashScreen extends GridPane implements Styleable {

  private static final String BACKGROUND_STYLE_CLASS = "background";
  private static final String DEFAULT_STYLE_SHEET = "resources/style/default.css";
  private static final String RESOURCE_FILE = "SplashScreen";
  private static final int SCREEN_WIDTH = 600;
  private static final int SCREEN_HEIGHT = 450;

  private Resources resources;

  public SplashScreen(Locale locale, IFroyoController controller) {

    resources = new Resources(locale, Resources.UI_RESOURCE_PACKAGE, RESOURCE_FILE);
    setStyleSheet(DEFAULT_STYLE_SHEET);

    setWidth(SCREEN_WIDTH);
    setHeight(SCREEN_HEIGHT);

    getColumnConstraints().add(Util.getColumnConstraints(Priority.NEVER, SCREEN_WIDTH));

    add(new SplashScreenTitleBox(), 0, 0);
    getRowConstraints().add(Util.getRowConstraints(Priority.NEVER, 120));
    getStyleClass().add(BACKGROUND_STYLE_CLASS);

    add(new SplashScreenButtonBox(resources, controller), 0, 1);
    getRowConstraints().add(Util.getRowConstraints(Priority.SOMETIMES, 200));
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
