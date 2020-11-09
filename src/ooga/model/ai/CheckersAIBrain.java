package ooga.model.ai;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;

public class CheckersAIBrain implements AIBrain{

  @Override
  public List<Coordinate> decideMove(BlockGrid checkBoard, Integer currentPlayerIndex){
    List<Coordinate> aiMoves = new ArrayList<>();

    if (currentPlayerIndex == 1){

    }
    else if (currentPlayerIndex == 2){

    }

    return aiMoves;
  }
}
