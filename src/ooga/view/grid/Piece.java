package ooga.view.grid;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ooga.Coordinate;
import javafx.scene.image.Image;
import ooga.exceptions.ResourcesException;

public class Piece {

  public static final int SIZE = 15;
  //TODO: configure color data to data file
  public static final Map<Integer, Color> FILL_COLOR = new HashMap<>() {{
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
  public static final Map<Integer, Color> STROKE_COLOR = new HashMap<>(){{
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
  public static final String KING_PIECE_IMAGE_BLUE = "img/king_piece_blue.png";
  public static final String KING_PIECE_IMAGE_RED = "img/king_piece_red.png";

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

  public Circle getPieceShape() {
    return pieceShape;
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
    if(state == 6 || state == 8){
      Image kingPieceImage = loadImage(KING_PIECE_IMAGE_BLUE);
      pieceShape.setFill(new ImagePattern(kingPieceImage));
    }
    else if (state == 7 || state == 9){
      Image kingPieceImage = loadImage(KING_PIECE_IMAGE_RED);
      pieceShape.setFill(new ImagePattern(kingPieceImage));
    }
  }

  private Image loadImage(String imageFile) throws ResourcesException {
    try {
      return new Image(imageFile, SIZE, SIZE, false, false);
    }catch (IllegalArgumentException e){
      throw new ResourcesException("file is not found");
    }
  }
}
