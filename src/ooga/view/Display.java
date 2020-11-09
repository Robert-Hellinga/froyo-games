package ooga.view;

import java.util.Locale;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.controller.IFroyoController;
import ooga.view.screens.SplashScreen;

public class Display {

  private IFroyoController myController;

  public Display(IFroyoController controller, Locale locale) {
    myController = controller;
    SplashScreen startScreen = new SplashScreen(locale, myController);
    myController.setNewLayout(startScreen);
  }


}
