package ooga.model.ai;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import ooga.Coordinate;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.blockgrid.BlockGrid;

public class OthelloAIBrain implements AIBrain {

  private static final List<Integer> PLAYER_INDEX_POLL = new ArrayList<>(List.of(1, 2));
  private static final int DEPTH = 5;


  @Override
  public List<Coordinate> decideMove(BlockGrid othelloBoard, Integer currentPlayerIndex) {

    List<Coordinate> aiMoves = new ArrayList<>();

    List<Coordinate> potentialMoves = getPotentialMoves(othelloBoard, currentPlayerIndex);

    return aiMoves;
  }

  private Pair<Coordinate, Float> miniMax(BlockGrid othelloGrid, int depth, float alpha, float beta,
      boolean maximizingPlayer, int currentPlayerIndex) {

  }

  private List<Coordinate> getPotentialMoves(BlockGrid othelloBoard, Integer currentPlayerIndex) {
    List<Coordinate> potentialMove = new ArrayList<>();
    for (int i = 0; i < othelloBoard.getAllBlocks().getBlockStructureWidth(); i++) {
      for (int j = 0; j < othelloBoard.getAllBlocks().getBlockStructureHeight(); j++) {
        Coordinate coor = new Coordinate(i, j);
        if (othelloBoard.getAllBlocks().getBlock(coor).getBlockState().isPotentialMove()) {
          potentialMove.add(coor);
        }
      }
    }
    return potentialMove;
  }


  private int playerTakeTurn(Integer currentPlayerIndex) {
    int index = PLAYER_INDEX_POLL.indexOf(currentPlayerIndex);
    if (index == PLAYER_INDEX_POLL.size() - 1) {
      return PLAYER_INDEX_POLL.get(0);
    } else {
      return PLAYER_INDEX_POLL.get(index + 1);
    }
  }
}
