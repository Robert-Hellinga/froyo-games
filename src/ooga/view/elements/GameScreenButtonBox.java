package ooga.view.elements;

import javafx.scene.layout.VBox;
import ooga.fileHandler.Resources;

public class GameScreenButtonBox extends VBox {

  private Resources resources;

  public GameScreenButtonBox(Resources resources) {
    this.resources = resources;

    CustomButton startButton = new CustomButton("Home", event -> goToHomeScreen());

    getChildren().addAll(startButton);
  }

  private void goToHomeScreen() {

  }

}
