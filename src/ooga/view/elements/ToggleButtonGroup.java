package ooga.view.elements;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import ooga.fileHandler.Resources;

public class ToggleButtonGroup extends ButtonGroup {

  private static final int DEFAULT_TOGGLE_BTN_WIDTH = 230;
  private static final int DEFAULT_TOGGLE_BTN_HEIGHT = 55;
  private static final int DEFAULT_TOGGLE_BTN_TEXT_SIZE = 14;

  private ToggleGroup toggleGroup;

  public ToggleButtonGroup(Pane pane, Resources resources) {
    this(pane, resources, DEFAULT_TOGGLE_BTN_WIDTH, DEFAULT_TOGGLE_BTN_HEIGHT, DEFAULT_TOGGLE_BTN_TEXT_SIZE);
  }

  public ToggleButtonGroup(Pane pane, Resources resources, int width, int height, int textSize) {
    super(pane, resources, width, height, textSize);
    toggleGroup = new ToggleGroup();

  }

  @Override
  public void addButtons(String... labels) {
    for(String label : labels) {
      ToggleButton btn = new ToggleButton();
      btn.setToggleGroup(toggleGroup);
      addButton(btn, getText(label));
    }
    addButtonsToPane();
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
