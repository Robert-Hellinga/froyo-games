package ooga.view.elements;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LabeledDropdown extends VBox {

  private static final int LABEL_SPACING = 10;
  private static final int SELECTOR_WIDTH = 120;
  private static final Font LABEL_FONT = new Font(16);
  private static final String SPACE_STRING = "\\s+";
  private static final String EMPTY_STRING = "";
  private static final String DROPDOWN_ID_SUFFIX = "Dropdown";
  private static final String LABELED_DROPDOWN_ID_SUFFIX = "LabeledDropdown";


  private ComboBox selector;

  public LabeledDropdown(String text, Object... values) {
    selector = new ComboBox<>();
    selector.getItems().addAll(values);
    selector.setValue(values[0]);
    selector.setPrefWidth(SELECTOR_WIDTH);
    selector.setId(text.replaceAll(SPACE_STRING, EMPTY_STRING) + DROPDOWN_ID_SUFFIX);

    setId(text.replaceAll(SPACE_STRING, EMPTY_STRING) + LABELED_DROPDOWN_ID_SUFFIX);
    setAlignment(Pos.CENTER);
    setSpacing(LABEL_SPACING);

    Label label = new Label(text);
    label.setFont(LABEL_FONT);

    getChildren().addAll(label, selector);
  }

  public int getSelectedIndex() {
    return selector.getSelectionModel().getSelectedIndex();
  }

  public Object getValue() {
    return selector.getValue();
  }
}


