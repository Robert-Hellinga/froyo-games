package ooga.view.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class CustomButton extends Button {

  private static final int DEFAULT_WIDTH = 100;
  private static final int DEFAULT_HEIGHT = 20;

  public CustomButton(String text, EventHandler<ActionEvent> handler) {
    this(text, handler, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public CustomButton(String text, EventHandler<ActionEvent> handler, int width, int height) {
    setAlignment(Pos.CENTER);
    setText(text);
    setOnAction(handler);
    setId(text);
    setMinHeight(height);
    setPrefWidth(width);
  }
}
