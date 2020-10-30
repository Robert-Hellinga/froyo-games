package ooga.view;

import java.util.ArrayList;
import java.util.List;
import ooga.view.pieces.Piece;

public class PieceStructure{
  private List<List<Piece>> allPieces = new ArrayList<>();

  public PieceStructure(String gameType, PieceStateStructure allPiecesState){
    initiateAllPieces(allPiecesState, gameType);
  }

  private void initiateAllPieces(PieceStateStructure allPiecesState, String gameType){
    for (int i = 0; i < allPiecesState.getPieceStructureHeight(); i++){
      List<Piece> pieceLine = new ArrayList<>();
      for (int j = 0; j < allPiecesState.getPieceStructureWidth(); j++){
        pieceLine.add(PieceGrid.createPiece(gameType, allPiecesState.getPieceState(j, i), j, i));
      }
      allPieces.add(pieceLine);
    }
  }

  public List<List<Piece>> getAllPieces() {
    return allPieces;
  }

  public void setAllPieces(List<List<Piece>> allPieces) {
    this.allPieces = allPieces;
  }
}
