package ooga.view.piecegrid;

import java.util.List;
import javafx.scene.Group;
import ooga.Coordinate;
import ooga.view.PieceStateStructure;
import ooga.view.pieces.Piece;

public class CheckersPieceGrid extends PieceGrid {

  public CheckersPieceGrid(String gameType, PieceStateStructure initiationPiecesState, Group root) {
    super(gameType, initiationPiecesState, root);
  }


  @Override
  public Coordinate getChosenPieceCoordinate() {
    for (List<Piece> pieceList : allPieces.getAllPieces()) {
      for (Piece piece : pieceList) {
        if (piece.getPieceChosen()) {
          return allPieces.getPieceCoordinate(piece);
        }
      }
    }
    return new Coordinate(-1, -1);
  }

  public void updatePieceCenBeChosenOrNotStatus(int playerInTurn) {
    for (List<Piece> pieceList : allPieces.getAllPieces()) {
      for (Piece piece : pieceList) {
        piece.makePieceCanBeChosen(allPieces, playerInTurn);
      }
    }
  }

  public void updateNonemptyColor(){
    for (List<Piece> pieceList : allPieces.getAllPieces()) {
      for (Piece piece : pieceList) {
        if (piece.getState() != 0 && !piece.getPieceChosen()) {
          piece.updateColor();
        }
      }
    }
  }

  @Override
  public void showPotentialMoveAndMakeClickable(List<Coordinate> potentialCoordinate) {
    for (List<Piece> pieceList : allPieces.getAllPieces()) {
      for (Piece piece : pieceList) {
        if (piece.getState() == 0) {
          if (potentialCoordinate.contains(allPieces.getPieceCoordinate(piece))) {
            piece.showAsPotentialMovePos();
            piece.makePotentialMovePosClickable(allPieces);
          } else {
            piece.updateColor();
            piece.makePotentialMovePosUnClickable();
          }
        }
      }
    }
  }
}
