package ooga.view.elements;

import javafx.beans.binding.StringBinding;
import javafx.scene.control.Label;
import ooga.view.Styleable;

public class Title extends Label {

  private static final String DEFAULT_STYLE_CLASS = "Title";

  public Title(String text) {
    super(text);
    getStyleClass().add(DEFAULT_STYLE_CLASS);
  }

}
