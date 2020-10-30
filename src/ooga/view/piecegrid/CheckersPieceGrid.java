package ooga.view.piecegrid;

import java.util.List;
import javafx.scene.Group;
import ooga.Coordinate;
import ooga.view.PieceStateStructure;
import ooga.view.pieces.Piece;

public class CheckersPieceGrid extends PieceGrid {

  public CheckersPieceGrid(String gameType, PieceStateStructure initiationPiecesState, Group root){
    super(gameType, initiationPiecesState, root);
  }


  @Override
  public Coordinate getChosenPieceCoordinate() {
    for (List<Piece> pieceList : allPieces.getAllPieces()) {
      for(Piece piece : pieceList){
        if (piece.getPieceChosen()){
          return allPieces.getPieceCoordinate(piece);
        }
      }
    }
    return new Coordinate(-1, -1);
  }

  public void updatePieceClickableStatus(int playerInTurn){
    for (List<Piece> pieceList : allPieces.getAllPieces()) {
      for(Piece piece : pieceList){
        piece.makePieceClickable(allPieces, playerInTurn);
      }
    }
  }

  @Override
  public void showPotentialMove(List<Coordinate> potentialCoordinate) {
    for (Coordinate coordinate : potentialCoordinate){
      allPieces.getPiece(coordinate).showAsPotentialMovePos();
    }
  }
}
