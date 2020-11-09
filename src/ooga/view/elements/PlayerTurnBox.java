package ooga.view.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PlayerTurnBox extends HBox {

  public PlayerTurnBox() {
    setAlignment(Pos.CENTER);
    Label l = new Label("TEST");
    l.setAlignment(Pos.CENTER);
    getChildren().add(l);
  }
}
