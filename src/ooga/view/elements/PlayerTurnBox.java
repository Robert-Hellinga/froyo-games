package ooga.view.elements;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ooga.view.ModelObserver;

public class PlayerTurnBox extends HBox implements ModelObserver {

  private static final String STYLE_CLASS = "turnbox";
  private static final int CIRCLE_RADIUS = 20;
  private final ResourceBundle fillColorBundle = ResourceBundle
      .getBundle("resources.ui.PieceFillColor");


  public PlayerTurnBox(String playerName, int colorKey) {
    getStyleClass().add(STYLE_CLASS);

    Label text = new Label(playerName);
    text.setFont(new Font(20));

    getChildren().addAll(text, makePlayerCircle(colorKey));
  }

  private Circle makePlayerCircle(int colorKey) {
    Circle circle = new Circle(CIRCLE_RADIUS);
    circle.setFill(getColor(colorKey));
    return circle;
  }

  private Color getColor(Integer colorKey) {
    String rgb = fillColorBundle.getString(colorKey.toString());
    String[] rgbValue = rgb.split(",");
    return Color.rgb(
        Integer.parseInt(rgbValue[0]),
        Integer.parseInt(rgbValue[1]),
        Integer.parseInt(rgbValue[2]),
        Double.parseDouble(rgbValue[3])
    );
  }

  @Override
  public void update() {

  }
}
