package ooga.view.newversion;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ooga.Coordinate;

public class Piece {

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

  private Circle pieceShape;
  private int state;
  private Coordinate coordinate;
  private boolean pieceClicked = false;

  public Piece(int state, Coordinate coordinate) {
    this.state = state;
    this.coordinate = coordinate;
    initiatePieceShape(coordinate);
    makePieceClickable();
  }

  private void initiatePieceShape(Coordinate coordinate) {
    pieceShape = new Circle();
    pieceShape.setRadius(SIZE);
    pieceShape.setCenterY(SIZE + coordinate.yCoordinate() * 2 * SIZE);
    pieceShape.setCenterX(SIZE + coordinate.xCoordinate() * 2 * SIZE);
    pieceShape.setFill(STATE_COLOR.get(state));
  }

//  public abstract void makePieceCanBeChosen(PieceStructure allPieces, int playerInTurn);

  public Circle getPieceShape() {
    return pieceShape;
  }

  public void setState(int state) {
    this.state = state;
  }


  private void makePieceClickable(){
    pieceShape.setOnMouseClicked(event -> clickPiece());
  }

  private void clickPiece(){
    this.pieceClicked = true;
  }

  public boolean isPieceClicked() {
    return pieceClicked;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void resetClickedStatus(){
    pieceClicked = false;
  }

  //  public boolean getPieceChosen() {
//    return false;
//  }
//
//  public abstract void unChoosePiece();
//
//  public abstract void showAsPotentialMovePos();
//
////  public abstract void showAsUnPotentialMovePos();
//
//  public abstract void makePotentialMovePosClickable(PieceStructure allPieces);
//
//  public abstract void makePotentialMovePosUnClickable();
//
//  public abstract void updateColor();
//
//  public boolean getPieceMoved() {
//    return false;
//  }
//
//  public void unMovedPiece() {
//  }
//
//  public void setPieceChosen(boolean pieceChosen) {
//  }
}
