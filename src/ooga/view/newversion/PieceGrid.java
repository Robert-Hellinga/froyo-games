package ooga.view.newversion;

import java.util.List;
import javafx.scene.Group;
import ooga.Coordinate;
import ooga.view.newversion.Piece;

public class PieceGrid {

  private PieceStructure allPieces;
  private Group root;

  public PieceGrid(PieceStateStructure initiationPiecesState, Group root) {
    allPieces = new PieceStructure(initiationPiecesState);
    this.root = root;
    setAllPiecesToRoot();
  }

  private void setAllPiecesToRoot() {
    for (List<Piece> piecesLine : allPieces.getAllPieces()) {
      for (Piece piece : piecesLine) {
        root.getChildren().add(piece.getPieceShape());
      }
    }
  }

  public boolean checkIfPieceIfClicked(){
    for(List<Piece> pieces : allPieces.getAllPieces()){
      for(Piece piece : pieces){
        if (piece.isPieceClicked()){
          return true;
        }
      }
    }
    return false;
  }

  public Coordinate getClickedPieceCoordinate(){
    for(List<Piece> pieces : allPieces.getAllPieces()){
      for(Piece piece : pieces){
        if (piece.isPieceClicked()){
          return piece.getCoordinate();
        }
      }
    }
    return Coordinate.INVALID_COORDINATE;
  }


  public PieceStructure getAllPieces() {
    return allPieces;
  }

  public void updatePieceState(PieceStateStructure allPiecesState){
    for(int i = 0; i < allPieces.getPieceStructureHeight(); i++){
      for(int j = 0; j < allPieces.getPieceStructureWidth(); j++){
        Coordinate coordinate = new Coordinate(j, i);
        allPieces.getPiece(coordinate).setState(allPiecesState.getPieceState(coordinate));
      }
    }
  }

  public void resetPiecesClickedStatus(){
    for(List<Piece> pieces : allPieces.getAllPieces()){
      for(Piece piece : pieces){
        piece.resetClickedStatus();
      }
    }
  }

}
