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

  private static final String[] CONTROL_BTNS = {"Home", "Save"};
  private static final int CONTROL_BTN_WIDTH = 100;
  private static final int CONTROL_BTN_HEIGHT = 100;
  private static final int CONTROL_BTN_SPACING = 10;
  private static final int CONTROL_BTN_FONT_SIZE = 18;

  private Resources resources;
  private IFroyoController controller;

  public GameScreenButtonBox(Resources resources, IFroyoController controller) {
    this.resources = resources;
    this.controller = controller;

    getChildren().addAll(getControlButtonGroup());
  }

  private VBox getControlButtonGroup() {
    VBox result = new VBox();
    result.setSpacing(CONTROL_BTN_SPACING);
    result.setAlignment(Pos.CENTER);

    ButtonGroup controlButtonGroup = new ButtonGroup(
        result,
        resources,
        CONTROL_BTN_WIDTH,
        CONTROL_BTN_HEIGHT,
        CONTROL_BTN_FONT_SIZE
    );

    controlButtonGroup.addButtons(CONTROL_BTNS);
    controlButtonGroup.setOnButtonPushed(0, event -> new SplashScreen(resources.getLocale(), controller));
    controlButtonGroup.setOnButtonPushed(1, event -> saveGame());

    return result;
  }

  private void saveGame() {

  }

}
