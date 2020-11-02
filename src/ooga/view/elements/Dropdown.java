package ooga.view.elements;

import java.util.List;
import javafx.beans.binding.StringBinding;
import javafx.scene.control.ComboBox;

public class Dropdown extends ComboBox {

  private static final String DEFAULT_STYLE_CLASS = "ComboBox";

  public Dropdown(String id, Object[] items) {
    setId(id);
    getItems().addAll(items);
    getStyleClass().add(DEFAULT_STYLE_CLASS);
  }

  public void setOnChange() {

  }

}
