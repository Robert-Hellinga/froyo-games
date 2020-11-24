package ooga.view.elements;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Utility view class for displaying an image and setting its width
 * @author Nate Mela (nrm27)
 */
public class ImageBox extends HBox {

  public ImageBox(String path, int width) {
    getChildren().add(getImage(path, width));
    setAlignment(Pos.CENTER);
  }

  private ImageView getImage(String path, int width) {
    ImageView img = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(path)));
    img.setFitWidth(width);
    img.setPreserveRatio(true);
    return img;
  }

}
