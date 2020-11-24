package ooga.view.elements;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Class that contains view element for the player turn boxes in the GameScreen
 * @author Nate Mela (nrm27)
 */
public class PlayerTurnBox extends HBox {

  private static final String STYLE_CLASS = "turnbox";
  private static final int CIRCLE_RADIUS = 20;
  private final ResourceBundle fillColorBundle;


  public PlayerTurnBox(String playerName, int colorKey, String gameType) {
    getStyleClass().add(STYLE_CLASS);

    Label text = new Label(playerName);
    text.setFont(new Font(20));
    fillColorBundle = ResourceBundle
        .getBundle("resources.ui.game_pieces." + gameType + "PieceFillColor");

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


}
