package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Util {

  private static final String STYLE_SHEET = "resources/style/default.css";

  public static void applyStyleSheet(Pane pane) {
    pane.getStylesheets().add(STYLE_SHEET);
  }


}
