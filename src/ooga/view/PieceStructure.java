package ooga.view;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.view.elements.piecegrid.PieceGrid;
import ooga.view.elements.pieces.Piece;

public class PieceStructure {

  private List<List<Piece>> allPieces = new ArrayList<>();

  public PieceStructure(String gameType, PieceStateStructure allPiecesState) {
    initiateAllPieces(allPiecesState, gameType);
  }

  private void initiateAllPieces(PieceStateStructure allPiecesState, String gameType) {
    for (int i = 0; i < allPiecesState.getPieceStateStructureHeight(); i++) {
      List<Piece> pieceLine = new ArrayList<>();
      for (int j = 0; j < allPiecesState.getPieceStateStructureWidth(); j++) {
        pieceLine.add(PieceGrid.createPiece(gameType, allPiecesState.getPieceState(new Coordinate(j, i)),
            new Coordinate(j, i)));
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

  public int getPieceStructureHeight() {
    return allPieces.size();
  }

  public int getPieceStructureWidth() {
    return allPieces.get(0).size();
  }

  /**
   * If the piece is not found, the returned coordinate is (-1, -1)
   *
   * @param piece the given piece
   * @return the coordinate of the piece in the piece grid
   */
  public Coordinate getPieceCoordinate(Piece piece) {
    for (int i = 0; i < getPieceStructureHeight(); i++) {
      for (int j = 0; j < getPieceStructureWidth(); j++) {
        if (allPieces.get(i).get(j).equals(piece)) {
          return new Coordinate(j, i);
        }
      }
    }
    return new Coordinate(-1, -1);
  }

  public Piece getPiece(Coordinate coordinate) {
    return allPieces.get(coordinate.yCoordinate()).get(coordinate.xCoordinate());
  }

  public PieceStateStructure getAllPieceState(){
    List<List<Integer>> pieceStateStructure = new ArrayList<>();
    for (List<Piece> pieceList : allPieces){
      List<Integer> pieceStateList = new ArrayList<>();
      for (Piece piece : pieceList){
        pieceStateList.add(piece.getState());
      }
      pieceStateStructure.add(pieceStateList);
    }
    return new PieceStateStructure(pieceStateStructure);
  }
}
