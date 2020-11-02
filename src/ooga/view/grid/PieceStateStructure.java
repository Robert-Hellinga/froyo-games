//package ooga.view.grid;
//
//import java.util.List;
//import ooga.Coordinate;
//
//public class PieceStateStructure {
//
//  //just for test
////  private List<List<Integer>> pieceStateStructure = new ArrayList<>() {{
////    add(List.of(1, 0, 1, 0, 1, 0, 1, 0));
////    add(List.of(0, 1, 0, 1, 0, 1, 0, 1));
////    add(List.of(1, 0, 1, 0, 1, 0, 1, 0));
////    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
////    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
////    add(List.of(2, 0, 2, 0, 2, 0, 2, 0));
////    add(List.of(0, 2, 0, 2, 0, 2, 0, 2));
////    add(List.of(2, 0, 2, 0, 2, 0, 2, 0));
////  }};
//
//  public PieceStateStructure(List<List<Integer>> pieceStateStructure){
//    this.pieceStateStructure = pieceStateStructure;
//  }
//
//  private List<List<Integer>> pieceStateStructure;
//
//  public List<List<Integer>> getPieceStateStructure() {
//    return pieceStateStructure;
//  }
//
//  public int getPieceStateStructureHeight() {
//    return pieceStateStructure.size();
//  }
//
//  public int getPieceStateStructureWidth() {
//    return pieceStateStructure.get(0).size();
//  }
//
//  public int getPieceState(Coordinate coordinate) {
//    return pieceStateStructure.get(coordinate.yCoordinate()).get(coordinate.xCoordinate());
//  }
//
//  @Override
//  public String toString() {
//    return "PieceStateStructure{" +
//        "pieceStateStructure=" + pieceStateStructure +
//        '}';
//  }
//}
