package ooga.view.grid;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ooga.Coordinate;
import javafx.scene.image.Image;
import ooga.exceptions.ResourceException;

public class Piece {

  private static final int SIZE = 20;
  private static final int HIGHLIGHT_STROKE_WIDTH = 3;
  private static final Paint GRID_BACKGROUND_COLOR = Color.web("e6e6e6");
  private static final Paint GRID_BACKGROUND_STROKE_COLOR = Color.web("cccccc");
  private static final int GRID_BACKGROUND_STROKE_WIDTH = 2;

  //TODO: configure color data to data file
  private static final Map<Integer, Color> FILL_COLOR = new HashMap<>() {{
    put(0, Color.color(0, 0, 0, 0));
    put(1, Color.rgb(0, 188, 255));
    put(2, Color.rgb(183, 29, 29));
    put(3, Color.rgb(0, 188, 255));
    put(4, Color.rgb(183, 29, 29));
    put(5, Color.rgb(39, 255, 0, 0.33));
    put(6, Color.color(0,0,0,0));
    put(7, Color.color(0,0,0,0));
    put(8, Color.color(0,0,0,0));
    put(9, Color.color(0,0,0,0));
  }};
  private static final Map<Integer, Color> STROKE_COLOR = new HashMap<>(){{
    put(0, Color.color(0, 0, 0, 0));
    put(1, Color.color(0, 0, 0, 0));
    put(2, Color.color(0, 0, 0, 0));
    put(3, Color.YELLOW);
    put(4, Color.YELLOW);
    put(5, Color.rgb(39, 255, 0));
    put(6, Color.color(0, 0, 0, 0));
    put(7, Color.color(0, 0, 0, 0));
    put(8, Color.YELLOW);
    put(9, Color.YELLOW);
  }};
  private static final String KING_PIECE_IMAGE_BLUE = "resources/img/king_piece_blue.png";
  private static final String KING_PIECE_IMAGE_RED = "resources/img/king_piece_red.png";

  private Circle pieceShape;
  private int state;
  private Coordinate coordinate;

  public Piece(int state, Coordinate coordinate, EventHandler<MouseEvent> value) {
    this.state = state;
    this.coordinate = coordinate;
    initiatePieceShape(coordinate, value);
  }

  private void initiatePieceShape(Coordinate coordinate, EventHandler<MouseEvent> value) {
    pieceShape = new Circle();
    pieceShape.setRadius(SIZE);
    pieceShape.setCenterY(SIZE + coordinate.yCoordinate() * 2 * SIZE);
    pieceShape.setCenterX(SIZE + coordinate.xCoordinate() * 2 * SIZE);
    updateColor();
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

  public void updateColor(){
    pieceShape.setFill(FILL_COLOR.get(state));
    pieceShape.setStroke(STROKE_COLOR.get(state));
    pieceShape.setStrokeWidth(HIGHLIGHT_STROKE_WIDTH);

    if(state == 6 || state == 8){
      Image kingPieceImage = loadImage(KING_PIECE_IMAGE_BLUE);
      pieceShape.setFill(new ImagePattern(kingPieceImage));
    }
    else if (state == 7 || state == 9){
      Image kingPieceImage = loadImage(KING_PIECE_IMAGE_RED);
      pieceShape.setFill(new ImagePattern(kingPieceImage));
    }
  }

  private Image loadImage(String imageFile) throws ResourceException {
    try {
      return new Image(imageFile, SIZE, SIZE, false, false);
    }catch (IllegalArgumentException e){
      throw new ResourceException("file is not found");
    }
  }
}
