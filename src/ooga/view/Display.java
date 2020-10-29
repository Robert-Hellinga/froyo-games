package ooga.view;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import ooga.controller.GridGameController;

public class Display implements Styleable, Languageable {

  private static final ResourceBundle DEFAULT_RESOURCE_BUNDLE = ResourceBundle.getBundle("Display");
  private static final GridGameController DEFAULT_CONTROLLER = new GridGameController();

  private GridGameController currentController;
  private ResourceBundle currentResourceBundle;

  public Display() {
    this(DEFAULT_CONTROLLER);
  }

  public Display(GridGameController controller) {
    currentController = controller;
    currentResourceBundle = DEFAULT_RESOURCE_BUNDLE;
  }

  public Scene makeScene() {
    
  }

  @Override
  public void setStyleSheet() {

  }

  @Override
  public String getStyleSheet() {
    return null;
  }

  @Override
  public void setResourceBundle(ResourceBundle bundle) {

  }

  @Override
  public ResourceBundle getResourceBundle() {
    return null;
  }
}
