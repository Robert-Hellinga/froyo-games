package ooga.model.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;

public class CheckersAIBrain implements AIBrain {

  public static final int SEARCH_LAYER = 4;

  protected int searchLayer = SEARCH_LAYER;
  protected boolean maxPlayer = true;

  @Override
  public List<Coordinate> decideMove(BlockGrid checkBoard, Integer currentPlayerIndex) {
    List<Coordinate> aiMoves = new ArrayList<>();

    Vector<Coordinate> bestMove = miniMax(searchLayer, checkBoard, currentPlayerIndex);

    return aiMoves;
  }

  public Vector<Coordinate> miniMax(Integer searchLayer, BlockGrid checkBoard, Integer currentPlayerIndex) {
    if (searchLayer == 0) {
      return new Vector<>();
    }

    BlockGrid newCheckBoard = checkBoard.clone();

    Vector<Coordinate> bestMove;
    if (maxPlayer) {
      Float value = Float.NEGATIVE_INFINITY;
      for (int i = 0; i < newCheckBoard.getAllBlocks().getBlockStructureHeight(); i++) {
        for (int j = 0; j < newCheckBoard.getAllBlocks().getBlockStructureWidth(); j++) {
          if (newCheckBoard.getAllBlocks().getBlock(new Coordinate(j, i)).getBlockState()
              .getPlayerID()
              == currentPlayerIndex) {
            List<Coordinate> potentialMoves = newCheckBoard.getAllBlocks()
                .getBlock(new Coordinate(j, i)).getAvailablePosition(currentPlayerIndex,
                    newCheckBoard.getAllBlocks());

          }
        }
      }
    }

    return null; // TODO; delete this line, @Nate added this so program runs
  }

  private void resetSearchCondition() {
    this.searchLayer = SEARCH_LAYER;
    this.maxPlayer = true;
  }
}
