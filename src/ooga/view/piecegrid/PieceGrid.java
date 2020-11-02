package ooga.view.piecegrid;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javafx.scene.Group;
import ooga.Coordinate;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.view.PieceStateStructure;
import ooga.view.PieceStructure;
import ooga.view.pieces.Piece;

public abstract class PieceGrid {

  protected PieceStructure allPieces;
  protected String gameType;
  protected Group root;

  public PieceGrid(String gameType, PieceStateStructure initiationPiecesState, Group root) {
    this.gameType = gameType;
    allPieces = new PieceStructure(this.gameType, initiationPiecesState);
    this.root = root;
    setAllPiecesToRoot();
  }

  public static Piece createPiece(String gameType, int state, Coordinate coordinate) {
    try {
      Class<?> piece = Class.forName("ooga.view.pieces." + gameType + "Piece");
      Class<?>[] param = {Integer.class, Coordinate.class};
      Constructor<?> cons = piece.getConstructor(param);
      Object[] paramObject = {state, coordinate};
      Object gamePiece = cons.newInstance(paramObject);
      return (Piece) gamePiece;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
  }

  public abstract void updatePieceCenBeChosenOrNotStatus(int playerInTurn);

  public abstract Coordinate getChosenPieceCoordinate();

  private void setAllPiecesToRoot() {
    for (List<Piece> piecesLine : allPieces.getAllPieces()) {
      for (Piece piece : piecesLine) {
        root.getChildren().add(piece.getPieceShape());
      }
    }
  }

  public abstract void showPotentialMoveAndMakeClickable(List<Coordinate> potentialCoordinate);

  public abstract void updateAllColor();

  public abstract PieceStateStructure getCurrentPieceState();

  public abstract void setPieceState(PieceStateStructure pieceState);

  public boolean checkIfPieceIsMoved(){return false;}

  public void resetPieceMovedChecker(){}

  public PieceStructure getAllPieces(){
    return allPieces;
  }
}
