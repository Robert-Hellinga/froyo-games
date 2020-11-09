package ooga.view.elements;

import java.util.List;
import java.util.Locale;
import javafx.beans.binding.StringBinding;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class LabeledDropdown extends VBox {

//  private static final String DEFAULT_STYLE_CLASS = "ComboBox";

  private ComboBox selector;
  private Label label;
  public LabeledDropdown(String label, Object defaultValue, Object... values) {

    selector = new ComboBox<>();

//    getStyleClass().add(DEFAULT_STYLE_CLASS);

    selector.getItems().addAll(values);
    selector.setValue(defaultValue);

    this.label = new Label(label);
    getChildren().addAll(this.label, selector);
  }

  public Object getValue() {
    return selector.getValue();
  }

  public void setOnChange() {

  }
}


