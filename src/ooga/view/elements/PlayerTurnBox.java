package ooga.view.elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ooga.view.GameObserver;

public class PlayerTurnBox extends HBox implements GameObserver {

  private static final String STYLE_CLASS = "turnbox";
  private static final int CIRCLE_RADIUS = 15;

  public PlayerTurnBox() {
    getStyleClass().add(STYLE_CLASS);

    Label text = new Label("Player");

    getChildren().addAll(text, makePlayerCircle());
  }

  private Circle makePlayerCircle() {
    Circle circle = new Circle(CIRCLE_RADIUS);
    circle.setFill(Color.rgb(0, 188, 255));
    return circle;
  }

  @Override
  public void update() {

  }
}
