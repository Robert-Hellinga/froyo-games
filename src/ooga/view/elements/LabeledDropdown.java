package ooga.view.elements;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

public class LabeledDropdown extends VBox {

  private static final int LABEL_SPACING = 10;
  private static final int SELECTOR_WIDTH = 120;
  private static final Font LABEL_FONT = new Font(16);

  private ComboBox<Object> selector;

  public LabeledDropdown(String text, Object... values) {
    selector = new ComboBox<>();
    selector.getItems().addAll(values);
    selector.setValue(values[0]);
    selector.setPrefWidth(SELECTOR_WIDTH);

    setAlignment(Pos.CENTER);
    setSpacing(LABEL_SPACING);

    Label label = new Label(text);
    label.setFont(LABEL_FONT);

    getChildren().addAll(label, selector);
  }

  public Object getValue() {
    return selector.getValue();
  }

  public void setConverter(StringConverter<Object> converter) {
    selector.setConverter(converter);
  }
}


