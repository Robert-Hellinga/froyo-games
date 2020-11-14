package ooga.view.elements;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ooga.view.ModelObserver;

public class PlayerTurnBox extends HBox implements ModelObserver {

  private static final String STYLE_CLASS = "turnbox";
  private static final int CIRCLE_RADIUS = 20;

  public PlayerTurnBox() {
    getStyleClass().add(STYLE_CLASS);

    Label text = new Label("Player");
    text.setFont(new Font(20));

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
