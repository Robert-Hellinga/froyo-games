package ooga.view.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class CustomButton extends Button {

  public CustomButton(String text, EventHandler<ActionEvent> handler) {
    setAlignment(Pos.CENTER);
    setText(text);
    setOnAction(handler);
    setId(text);
  }
}
