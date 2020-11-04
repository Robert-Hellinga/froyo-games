package ooga.view;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.IFroyoController;
import ooga.view.screens.SplashScreen;

public class Display implements IDisplay {

  private static final String DEFAULT_RESOURCE_BUNDLE_PATH = "resources.ui.Display";
  //private static final GameController DEFAULT_CONTROLLER = new GameController();

  private IFroyoController myController;
  private ResourceBundle resourceBundle;
  private Scene scene;
  private Stage stage;

//  public Display(Stage stage) {
//    this(stage, DEFAULT_CONTROLLER);
//  }

  public Display(IFroyoController controller) {
    myController = controller;
    resourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE_PATH);
    SplashScreen startScreen = new SplashScreen(resourceBundle, myController, this);
    myController.setNewLayout(startScreen);
  }






}
