package ooga.view.elements;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import ooga.fileHandler.Resources;

public class ToggleButtonGroup {

  private static final int DEFAULT_TOGGLE_BTN_WIDTH = 230;
  private static final int DEFAULT_TOGGLE_BTN_HEIGHT = 55;

  private ToggleGroup toggleGroup;
  private List<ToggleButton> buttons;

  public ToggleButtonGroup(Pane pane, Resources resources, int textSize, String... labels) {
    this(pane, resources, DEFAULT_TOGGLE_BTN_WIDTH, DEFAULT_TOGGLE_BTN_HEIGHT, textSize, labels);
  }

  public ToggleButtonGroup(Pane pane, Resources resources, int width, int height, int textSize,
      String... labels) {
    buttons = new ArrayList<>();
    toggleGroup = new ToggleGroup();
    for(String label : labels) {
      ToggleButton btn = new ToggleButton();
      configureButton(btn, resources.getString(label), width, height, textSize);
      buttons.add(btn);
    }
    pane.getChildren().addAll(buttons);
  }

  private void configureButton(ToggleButton button, String text, int width, int height,
      int textSize) {
    button.setToggleGroup(toggleGroup);
    button.setAlignment(Pos.CENTER);
    button.setText(text);
    button.setId(text);
    button.setMinHeight(height);
    button.setPrefWidth(width);
    button.setFont(new Font(textSize));
  }

  public void setOnButtonPushed(int buttonIndex, EventHandler<ActionEvent> event) {
    ToggleButton btn = buttons.get(buttonIndex);
    btn.setOnAction(event);
  }

  public void setButtonStyles(String styleClass) {
    for(ToggleButton btn : buttons) {
      btn.getStyleClass().add(styleClass);
    }
  }

  public int getToggleIndexSelected() {
    ObservableList<Toggle> toggles = toggleGroup.getToggles();
    for (int i = 0; i < toggles.size(); i++) {
      if (toggles.get(i).isSelected()) {
        return i;
      }
    }
    return -1; // No toggle selected
  }

  public boolean hasSelectedToggle() {
    return toggleGroup.getSelectedToggle() != null;
  }
}
