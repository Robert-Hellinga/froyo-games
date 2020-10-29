package ooga.View;

import java.util.List;

public class PieceStateStructure {
  private List<List<Integer>> pieceStateStructure;

  public List<List<Integer>> getPieceStateStructure() {
    return pieceStateStructure;
  }

  public int getPieceStructureHeight(){
    return pieceStateStructure.size();
  }

  public int getPieceStructureWidth(){
    return pieceStateStructure.get(0).size();
  }

  public int getPieceState(int x, int y){
    return pieceStateStructure.get(y).get(x);
  }
}
