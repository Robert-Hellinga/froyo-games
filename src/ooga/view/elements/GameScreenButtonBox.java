package ooga.view.elements;

import java.util.ResourceBundle;
import javafx.scene.layout.VBox;

public class GameScreenButtonBox extends VBox {

  private ResourceBundle resourceBundle;

  public GameScreenButtonBox(ResourceBundle resourceBundle) {
    this.resourceBundle = resourceBundle;

    CustomButton startButton = new CustomButton("Home", event -> goToHomeScreen());

    getChildren().addAll(startButton);
  }

  private void goToHomeScreen() {

  }

}
