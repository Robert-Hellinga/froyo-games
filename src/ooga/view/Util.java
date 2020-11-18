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

  public static RowConstraints getRowConstraints(Priority Vgrow, int prefHeight) {
    RowConstraints constraints = new RowConstraints();
    constraints.setVgrow(Vgrow);
    constraints.setPrefHeight(prefHeight);
    return constraints;
  }

  public static ColumnConstraints getColumnConstraints(Priority Hgrow, int prefWidth) {
    ColumnConstraints constraints = new ColumnConstraints();
    constraints.setHgrow(Hgrow);
    constraints.setPrefWidth(prefWidth);
    return constraints;
  }

}
