package ooga.view.grid;

import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ooga.Coordinate;
import ooga.exceptions.ResourceException;

public class Piece {

  private static final int SIZE = 20;
  private static final int HIGHLIGHT_STROKE_WIDTH = 3;
  private static final Paint GRID_BACKGROUND_COLOR = Color.web("e6e6e6");
  private static final Paint GRID_BACKGROUND_STROKE_COLOR = Color.web("cccccc");
  private static final int GRID_BACKGROUND_STROKE_WIDTH = 2;
  private static final String KING_PIECE_IMAGE_BLUE = "resources/img/king_piece_blue.png";
  private static final String KING_PIECE_IMAGE_RED = "resources/img/king_piece_red.png";
  private static final String RESOURCE_PACKAGE = "resources.ui.";


  private Circle pieceShape;
  private int state;
  private final Coordinate coordinate;

  public Piece(int state, Coordinate coordinate, EventHandler<MouseEvent> value, String gameType) {
    this.state = state;
    this.coordinate = coordinate;
    initiatePieceShape(coordinate, value, gameType);
  }

  private void initiatePieceShape(Coordinate coordinate, EventHandler<MouseEvent> value, String gameType) {
    pieceShape = new Circle();
    pieceShape.setRadius(SIZE);
    pieceShape.setCenterY(SIZE + coordinate.yCoordinate() * 2 * SIZE);
    pieceShape.setCenterX(SIZE + coordinate.xCoordinate() * 2 * SIZE);
    updateColor(gameType);
    pieceShape.setOnMouseClicked(value);
  }

  public StackPane getPieceShape() {
    StackPane pane = new StackPane();
    Rectangle rectangle = new Rectangle(2 * SIZE, 2 * SIZE, GRID_BACKGROUND_COLOR);
    rectangle.setStrokeWidth(GRID_BACKGROUND_STROKE_WIDTH);
    rectangle.setStroke(GRID_BACKGROUND_STROKE_COLOR);
    pane.getChildren().addAll(rectangle, pieceShape);
    return pane;
  }

  public void setState(int state) {
    this.state = state;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void updateColor(String gameType) {
    ResourceBundle fillColorBundle = ResourceBundle
            .getBundle(RESOURCE_PACKAGE + gameType + "PieceFillColor");
    ResourceBundle strokeColorBundle = ResourceBundle
            .getBundle(RESOURCE_PACKAGE + gameType + "PieceStrokeColor");
    pieceShape.setFill(getColor(state, fillColorBundle));
    pieceShape.setStroke(getColor(state, strokeColorBundle));
    pieceShape.setStrokeWidth(HIGHLIGHT_STROKE_WIDTH);

    if (strokeColorBundle.getString(Integer.toString(state)).contains("img")) {
      Image kingPieceImage = loadImage(strokeColorBundle.getString(Integer.toString(state)));
      pieceShape.setFill(new ImagePattern(kingPieceImage));
    }
  }

  private Image loadImage(String imageFile) throws ResourceException {
    try {
      return new Image(imageFile, SIZE, SIZE, false, false);
    } catch (IllegalArgumentException e) {
      throw new ResourceException("file is not found");
    }
  }

  private Color getColor(Integer state, ResourceBundle resourceBundle) {
    String rgb = resourceBundle.getString(state.toString());
    String[] rgbValue = rgb.split(",");
    return Color.rgb(
        Integer.parseInt(rgbValue[0]),
        Integer.parseInt(rgbValue[1]),
        Integer.parseInt(rgbValue[2]),
        Double.parseDouble(rgbValue[3])
    );
  }
}
