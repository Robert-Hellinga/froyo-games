package ooga.view;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Util {

  public static int getResourceAsInt(ResourceBundle resource, String key) {
    return Integer.parseInt(resource.getString(key));
  }

  public static String getResourceAsString(ResourceBundle resource, String key) {
    return resource.getString(key);
  }

  public static void setPaneStylesheet(Pane pane, String stylesheet) {
    pane.getStylesheets().add(stylesheet);
  }

  public static RowConstraints getRowConstraints(Priority Vgrow, boolean fillHeight,
      int prefHeight) {
    RowConstraints constraints = new RowConstraints();
    constraints.setVgrow(Vgrow);
    constraints.setPrefHeight(prefHeight);
    return constraints;
  }

  public static ColumnConstraints getColumnConstraints(Priority Hgrow, boolean fillWidth,
      int prefWidth) {
    ColumnConstraints constraints = new ColumnConstraints();
    constraints.setHgrow(Hgrow);
    constraints.setPrefWidth(prefWidth);
    return constraints;
  }

}
