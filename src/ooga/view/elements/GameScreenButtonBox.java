package ooga.view.elements;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import ooga.controller.IGameController;
import ooga.fileHandler.Resources;

public class GameScreenButtonBox extends VBox {

  private static final String HOME_BTN = "Home";
  private static final String SAVE_BTN = "Save";

  private Resources resources;
  private IGameController controller;

  public GameScreenButtonBox(Resources resources, IGameController controller) {
    this.resources = resources;
    this.controller = controller;

    getChildren().addAll(getControlButtonGroup());
  }

  private VBox getControlButtonGroup() {
    VBox result = new VBox();
    result.setSpacing(10);
    result.setAlignment(Pos.CENTER);

    CustomButton startButton = new CustomButton(resources.getString(HOME_BTN),
        event -> goToHomeScreen(), 65, 20);

    CustomButton saveButton = new CustomButton(resources.getString(SAVE_BTN),
        event -> saveGame(), 65, 20);

    result.getChildren().addAll(startButton, saveButton);
    return result;
  }

  private void goToHomeScreen() {

  }

  private void saveGame() {

  }

}
