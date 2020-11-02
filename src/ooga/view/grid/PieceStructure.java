//package ooga.view.grid;
//
//import java.util.ArrayList;
//import java.util.List;
//import ooga.Coordinate;
//import ooga.controller.GameController;
//
//public class PieceStructure {
//
//  private List<List<Piece>> allPieces = new ArrayList<>();
//
//  public PieceStructure(GameController controller) {
//    initiateAllPieces(controller);
//  }
//
//  private void initiateAllPieces(GameController controller) {
//    List<List<Integer>> initialBlockStates = controller.getAllBlockStates();
//
//    for (int i = 0; i < initialBlockStates.size(); i++) {
//      List<Piece> pieceLine = new ArrayList<>();
//      for (int j = 0; j < initialBlockStates.get(0).size(); j++) {
//        Coordinate position = new Coordinate(j, i);
//        pieceLine.add(
//            new Piece(
//                initialBlockStates.get(i).get(j),
//                position,
//                event -> controller.clickPiece(position)
//            )
//        );
//      }
//      allPieces.add(pieceLine);
//    }
//  }
//
//  public List<List<Piece>> getAllPieces() {
//    return allPieces;
//  }
//
//  public void setAllPieces(List<List<Piece>> allPieces) {
//    this.allPieces = allPieces;
//  }
//
//  public int getPieceStructureHeight() {
//    return allPieces.size();
//  }
//
//  public int getPieceStructureWidth() {
//    return allPieces.get(0).size();
//  }
//
//  /**
//   * If the piece is not found, the returned coordinate is (-1, -1)
//   *
//   * @param piece the given piece
//   * @return the coordinate of the piece in the piece grid
//   */
//  public Coordinate getPieceCoordinate(Piece piece) {
//    for (int i = 0; i < getPieceStructureHeight(); i++) {
//      for (int j = 0; j < getPieceStructureWidth(); j++) {
//        if (allPieces.get(i).get(j).equals(piece)) {
//          return new Coordinate(j, i);
//        }
//      }
//    }
//    return new Coordinate(-1, -1);
//  }
//
//  public Piece getPiece(Coordinate coordinate) {
//    return allPieces.get(coordinate.yCoordinate()).get(coordinate.xCoordinate());
//  }
//
//  @Override
//  public String toString() {
//    return "PieceStructure{" +
//        "allPieces=" + allPieces +
//        '}';
//  }
//}
