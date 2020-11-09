package ooga.view.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import ooga.controller.IFroyoController;
import ooga.controller.IGameController;
import ooga.fileHandler.Resources;
import ooga.view.screens.SplashScreen;

public class GameScreenButtonBox extends VBox {

  private static final String HOME_BTN = "Home";
  private static final String SAVE_BTN = "Save";
  private static final int CONTROL_BTN_WIDTH = 65;
  private static final int CONTROL_BTN_HEIGHT = 20;
  private static final ButtonFactory BUTTON_FACTORY = new ButtonFactory();

  private Resources resources;
  private IFroyoController controller;

  public GameScreenButtonBox(Resources resources, IFroyoController controller) {
    this.resources = resources;
    this.controller = controller;

    getChildren().addAll(getControlButtonGroup());
  }

  private VBox getControlButtonGroup() {
    VBox result = new VBox();
    result.setSpacing(10);
    result.setAlignment(Pos.CENTER);

    Button startButton = BUTTON_FACTORY.makeButton(resources.getString(HOME_BTN),
        event -> new SplashScreen(resources.getLocale(), controller), CONTROL_BTN_WIDTH,
        CONTROL_BTN_HEIGHT);

    Button saveButton = BUTTON_FACTORY.makeButton(resources.getString(SAVE_BTN),
        event -> saveGame(), CONTROL_BTN_WIDTH, CONTROL_BTN_HEIGHT);

    result.getChildren().addAll(startButton, saveButton);
    return result;
  }

  private void goToHomeScreen() {

  }

  private void saveGame() {

  }

}
