package ooga.view.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;

public class ButtonFactory {

  private static final int DEFAULT_BTN_WIDTH = 130;
  private static final int DEFAULT_BTN_HEIGHT = 20;
  private static final int DEFAULT_TEXT_SIZE = 14;
  public static final String SUCCESS_STYLE = "success";
  public static final String INFO_STYLE = "info";
  public static final String DANGER_STYLE = "danger";

  public ButtonFactory() {

  }

  public Button makeButton(String text, EventHandler<ActionEvent> handler) {
    return makeButton(text, handler, DEFAULT_BTN_WIDTH, DEFAULT_BTN_HEIGHT);
  }

  public Button makeButton(String text, EventHandler<ActionEvent> handler, int width, int height) {
    return makeButton(text, handler, width, height, DEFAULT_TEXT_SIZE);
  }

  public Button makeButton(String text, EventHandler<ActionEvent> handler, int width, int height,
   int textSize) {
    Button button = new Button();
    button.setOnAction(handler);
    configureButton(button, text, width, height, textSize);
    return button;
  }

  private void configureButton(ButtonBase button, String text, int width, int height,
      int textSize) {
    button.setAlignment(Pos.CENTER);
    button.setText(text);
    button.setId(text);
    button.setMinHeight(height);
    button.setPrefWidth(width);
    button.setFont(new Font(textSize));
  }
}
