package ooga.view.elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.binding.StringBinding;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.view.Styleable;

public class Title extends Label {

  private static final String TITLE_IMG_PATH = "img/title.png";

  public Title() {
//    super(new Image(new FileInputStream(TITLE_IMG_PATH)));
    super("TEST");

  }
}
