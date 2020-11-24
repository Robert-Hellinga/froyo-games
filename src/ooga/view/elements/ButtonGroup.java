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

/**
 * Class to encapsulate groups of buttons in the view.
 * Typical use case would be creating a ButtonGroup with an HBox or a VBox in the pane parameter
 * Contains multiple constructors, with default values for every parameter if not used.
 * @author Nate Mela
 */
public class ButtonGroup {

  public static final String SUCCESS_STYLE = "success";
  public static final String INFO_STYLE = "info";
  public static final String DANGER_STYLE = "danger";
  // The following are for setting id's
  private static final String BTN_STRING = "Btn";
  private static final String SPACE_STRING = "\\s+";
  private static final String EMPTY_STRING = "";

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

  /**
   * Method to add buttons to the button group
   * @param labels is an array of String keys to create the buttons with. Keys must be in resource
   *               file of the ButtonGroup
   */
  public void addButtons(String... labels) {
    for (String label : labels) {
      ButtonBase btn = new Button();
      addButton(btn, getText(label));
    }
    addButtonsToPane();
  }

  /**
   * Method to update the Pane associated with this ButtonGroup to have the new buttons
   */
  public void addButtonsToPane() {
    pane.getChildren().addAll(buttons);
  }

  /**
   * Method to set the button styles for all buttons contained within the button group. Default
   * CSS classes are listed as public variables for the ButtonGroup class
   * @param styleClass is the string CSS class to set all the buttons to
   */
  public void setButtonStyles(String styleClass) {
    for (ButtonBase btn : buttons) {
      btn.getStyleClass().add(styleClass);
    }
  }

  public void setOnButtonPushed(EventHandler<ActionEvent> event) {
    setOnButtonPushed(event, 0);
  }

  /**
   * Method to assign an action to multiple buttons in the ButtonGroup
   */
  public void setOnButtonsPushed(EventHandler<ActionEvent> event, int... buttonIndices) {
    for (int i : buttonIndices) {
      setOnButtonPushed(event, i);
    }
  }

  /**
   * Method to assign an action to a specific button in the ButtonGroup
   */
  public void setOnButtonPushed(EventHandler<ActionEvent> event, int buttonIndex) {
    ButtonBase btn = buttons.get(buttonIndex);
    btn.setOnAction(event);
  }

  /**
   * Button configuration method to set the characteristics of every button and add them to the
   * list of buttons in the group
   */
  public void addButton(ButtonBase button, String label) {
    button.setAlignment(Pos.CENTER);
    button.setText(label);
    button.setId(label.replaceAll(SPACE_STRING, EMPTY_STRING) + BTN_STRING);
    button.setMinHeight(height);
    button.setPrefWidth(width);
    button.setFont(new Font(textSize));
    buttons.add(button);
  }

  /**
   * Method to retrieve the text for a button from the resources file associated with this group
   * @param label is the string key to retrieve from the resources file
   */
  public String getText(String label) {
    return resources == null ? label : resources.getString(label);
  }
}
