package ooga.view.elements;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import ooga.fileHandler.Resources;

public class GameScreenButtonBox extends VBox {

  private static final String HOME_BTN = "Home";
  private Resources resources;

  public GameScreenButtonBox(Resources resources) {
    this.resources = resources;

    getChildren().addAll(getControlButtonGroup());
  }

  private VBox getControlButtonGroup() {
    VBox result = new VBox();
    result.setSpacing(10);
    result.setAlignment(Pos.CENTER);

    CustomButton startButton = new CustomButton(resources.getString(HOME_BTN),
        event -> goToHomeScreen());

    result.getChildren().addAll(startButton);
    return result;
  }

  private void goToHomeScreen() {

  }

}
