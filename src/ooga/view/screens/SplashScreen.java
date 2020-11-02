package ooga.view.screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import ooga.view.Styleable;
import ooga.view.Util;

public class SplashScreen extends GridPane implements Styleable {

  private static final String DEFAULT_STYLE_SHEET = "style/default.css";
  private static final String TITLE_IMG_PATH = "img/title.png";
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;

  private ResourceBundle resourceBundle;

  public SplashScreen(ResourceBundle resourceBundle) {
    super();
    this.resourceBundle = resourceBundle;
    setWidth(WIDTH);
    setHeight(HEIGHT);
    setGridLinesVisible(true);
    add(makeTitleBox(), 0, 0);
    getColumnConstraints().add(Util.getColumnConstraints(Priority.NEVER, true, WIDTH));
    getRowConstraints().add(Util.getRowConstraints(Priority.NEVER, false, 150));
  }

  private HBox makeTitleBox() {
    HBox titleBox = new HBox();
    titleBox.getChildren().add(getTitle());
    titleBox.setAlignment(Pos.CENTER);
    Util.configureElement(getTitle(), Pos.CENTER);
    return titleBox;
  }

  private ImageView getTitle() {
    ImageView img = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TITLE_IMG_PATH)));
    img.setFitWidth(400);
    img.setPreserveRatio(true);
    return img;
  }

  @Override
  public void setStyleSheet(String stylesheet) {
    getStylesheets().clear();
    Util.setPaneStylesheet(this, stylesheet);
  }

  @Override
  public String getStyleSheet() {
    return getStylesheets().get(0);
  }
}
