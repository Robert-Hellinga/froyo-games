package ooga.view.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class CustomToggleButton extends ToggleButton {

  private static final int DEFAULT_WIDTH = 230;
  private static final int DEFAULT_HEIGHT = 55;

  public CustomToggleButton(String text, ToggleGroup toggleGroup, int width, int height) {
    setToggleGroup(toggleGroup);
    setAlignment(Pos.CENTER);
    setText(text);
    setId(text);

    setPrefWidth(width);
    setMinHeight(height);
  }

  public CustomToggleButton(String text, ToggleGroup toggleGroup) {
    this(text, toggleGroup, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

}
