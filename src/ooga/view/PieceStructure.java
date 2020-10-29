package ooga.view;

import java.util.ArrayList;
import java.util.List;

public class PieceStructure{
  private List<List<Piece>> allPieces = new ArrayList<>();

  public PieceStructure(PieceStateStructure allPiecesState){
    initiateAllPieces(allPiecesState);
  }

  private void initiateAllPieces(PieceStateStructure allPiecesState){
    for (int i = 0; i < allPiecesState.getPieceStructureHeight(); i++){
      List<Piece> pieceLine = new ArrayList<>();
      for (int j = 0; j < allPiecesState.getPieceStructureWidth(); j++){
        pieceLine.add(new Piece(allPiecesState.getPieceState(j, i), j, i));
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
