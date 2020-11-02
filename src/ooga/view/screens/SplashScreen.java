package ooga.view.screens;

import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import ooga.view.Util;
import ooga.view.elements.Title;

public class SplashScreen extends GridPane {

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
    getRowConstraints().add(Util.getRowConstraints(Priority.NEVER, false, 100));
  }

  private HBox makeTitleBox() {
    HBox titleBox = new HBox();
    Title title = new Title();

    titleBox.getChildren().add(title);
    titleBox.setAlignment(Pos.CENTER);
    Util.configureElement(title, Pos.CENTER);
    return titleBox;
  }
}
