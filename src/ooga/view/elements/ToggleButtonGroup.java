package ooga.view.elements;

import javafx.collections.ObservableList;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import ooga.fileHandler.Resources;

public class ToggleButtonGroup extends ButtonGroup {

  private static final int DEFAULT_TOGGLE_BTN_WIDTH = 230;
  private static final int DEFAULT_TOGGLE_BTN_HEIGHT = 55;
  private static final int DEFAULT_TOGGLE_BTN_TEXT_SIZE = 14;
  private static final String BTN_STRING = "Btn";

  private ToggleGroup toggleGroup;

  public ToggleButtonGroup(Pane pane, Resources resources) {
    this(pane, resources, DEFAULT_TOGGLE_BTN_WIDTH, DEFAULT_TOGGLE_BTN_HEIGHT,
        DEFAULT_TOGGLE_BTN_TEXT_SIZE);
  }

  public ToggleButtonGroup(Pane pane, Resources resources, int width, int height) {
    this(pane, resources, width, height, DEFAULT_TOGGLE_BTN_TEXT_SIZE);
  }

  public ToggleButtonGroup(Pane pane, Resources resources, int width, int height, int textSize) {
    super(pane, resources, width, height, textSize);
    toggleGroup = new ToggleGroup();

  }

  @Override
  public void addButtons(String... labels) {
    for (String label : labels) {
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
