
package ooga.view.pieces;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ooga.Coordinate;
import ooga.view.PieceStructure;

public abstract class Piece {

  public static final int SIZE = 15;
  //TODO: configure color data to data file
  public static final Map<Integer, Color> STATE_COLOR = new HashMap<>() {{
    put(0, Color.color(0, 0, 0, 0));
    put(1, Color.rgb(0, 188, 255));
    put(2, Color.rgb(183, 29, 29));
  }};
  public static final Color CHOSEN_STROKE_COLOR = Color.YELLOW;
  public static final Color UNCHOSEN_COLOR = Color.rgb(0, 0, 0, 0);
  public static final Color POTENTIAL_COLOR = Color.rgb(39, 255, 0, 0.33);
  public static final Color POTENTIAL_STROKE_COLOR = Color.rgb(39, 255, 0);
  public static final int EMPTY_STATE = 0;

  protected Circle pieceShape;
  protected int state;
  protected Coordinate coordinate;

  public Piece(int state, Coordinate coordinate) {
    this.state = state;
    this.coordinate = coordinate;
    initiatePieceShape(coordinate);
  }

  private void initiatePieceShape(Coordinate coordinate) {
    pieceShape = new Circle();
    pieceShape.setRadius(SIZE);
    pieceShape.setCenterY(SIZE + coordinate.yCoordinate() * 2 * SIZE);
    pieceShape.setCenterX(SIZE + coordinate.xCoordinate() * 2 * SIZE);
    pieceShape.setFill(STATE_COLOR.get(state));
  }

  public abstract void makePieceCanBeChosen(PieceStructure allPieces, int playerInTurn);

  public Circle getPieceShape() {
    return pieceShape;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getState() {
    return state;
  }

  public boolean getPieceChosen() {
    return false;
  }

  public abstract void unChoosePiece();

  public abstract void showAsPotentialMovePos();

//  public abstract void showAsUnPotentialMovePos();

  public abstract void makePotentialMovePosClickable(PieceStructure allPieces);

  public abstract void makePotentialMovePosUnClickable();

  public abstract void updateColor();

  public boolean getPieceMoved(){return false;}

  public void unMovedPiece(){}
}
