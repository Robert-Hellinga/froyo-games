package ooga.view.elements;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ooga.view.Util;

public class SplashScreenTitleBox extends HBox {

  private static final String TITLE_IMG_PATH = "resources/img/title.png";

  public SplashScreenTitleBox() {
    getChildren().add(getTitle());
    setAlignment(Pos.CENTER);
  }

  private ImageView getTitle() {
    ImageView img = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TITLE_IMG_PATH)));
    img.setFitWidth(500);
    img.setPreserveRatio(true);
    return img;
  }

}
