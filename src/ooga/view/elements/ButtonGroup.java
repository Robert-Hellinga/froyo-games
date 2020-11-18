package ooga.view.elements;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import ooga.fileHandler.Resources;

public class ButtonGroup {

  public static final String SUCCESS_STYLE = "success";
  public static final String INFO_STYLE = "info";
  public static final String DANGER_STYLE = "danger";

  private static final int DEFAULT_BTN_WIDTH = 130;
  private static final int DEFAULT_BTN_HEIGHT = 20;
  private static final int DEFAULT_TEXT_SIZE = 14;

  private List<ButtonBase> buttons;
  private Pane pane;
  private Resources resources;
  private int width, height, textSize;

  public ButtonGroup(Pane pane, int width, int height) {
    this(pane, null, width, height);
  }

  public ButtonGroup(Pane pane, Resources resources) {
    this(pane, resources, DEFAULT_BTN_WIDTH, DEFAULT_BTN_HEIGHT, DEFAULT_TEXT_SIZE);
  }

  public ButtonGroup(Pane pane, Resources resources, int width, int height) {
    this(pane, resources, width, height, DEFAULT_TEXT_SIZE);
  }

  public ButtonGroup(Pane pane, Resources resources, int width, int height, int textSize) {
    this.pane = pane;
    this.resources = resources;
    this.width = width;
    this.height = height;
    this.textSize = textSize;
    buttons = new ArrayList<>();
  }

  public void addButtons(String... labels) {
    for (String label : labels) {
      ButtonBase btn = new Button();
      addButton(btn, getText(label));
    }
    addButtonsToPane();
  }

  public void addButtonsToPane() {
    pane.getChildren().addAll(buttons);
  }

  public void setButtonStyles(String styleClass) {
    for (ButtonBase btn : buttons) {
      btn.getStyleClass().add(styleClass);
    }
  }

  public void setOnButtonPushed(EventHandler<ActionEvent> event) {
    setOnButtonPushed(0, event);
  }

  public void setOnButtonPushed(int buttonIndex, EventHandler<ActionEvent> event) {
    ButtonBase btn = buttons.get(buttonIndex);
    btn.setOnAction(event);
  }

  public void addButton(ButtonBase button, String label) {
    button.setAlignment(Pos.CENTER);
    button.setText(label);
    button.setId(label);
    button.setMinHeight(height);
    button.setPrefWidth(width);
    button.setFont(new Font(textSize));
    buttons.add(button);
  }

  public String getText(String label) {
    return resources == null ? label : resources.getString(label);
  }
}
