package ooga.View;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Piece {
  public final int SIZE = 15;
  //TODO: configure color data to data file
  public final Map<Integer, Color> STATE_COLOR = new HashMap<>(){{
    put(0, Color.color(0,0,0,0));
    put(1, Color.color(0, 188, 255));
    put(2, Color.color(183, 29, 29));
  }};

  protected Circle pieceShape;
  protected int state;
  protected int xCoordinate;
  protected int yCoordinate;

  public Piece(int state, int xCoordinate, int yCoordinate){
    this.state = state;
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    initiatePieceShape(xCoordinate, yCoordinate);
  }

  private void initiatePieceShape(int xCoordinate, int yCoordinate){
    pieceShape = new Circle();
    pieceShape.setRadius(SIZE);
    pieceShape.setCenterY(SIZE + yCoordinate * 2 * SIZE);
    pieceShape.setCenterX(SIZE + xCoordinate * 2 * SIZE);
    pieceShape.setFill(STATE_COLOR.get(state));
  }

  protected abstract void makePieceClickable();
}
