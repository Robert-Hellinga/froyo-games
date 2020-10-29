package ooga.view;

public class PieceGrid {
  private PieceStructure allPieces;

  public PieceGrid(PieceStateStructure initiationPiecesState){
    allPieces = new PieceStructure(initiationPiecesState);
  }
}
