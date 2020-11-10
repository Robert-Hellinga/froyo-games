package ooga.model.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import ooga.Coordinate;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.blockgrid.BlockGrid;

public class CheckersAIBrain implements AIBrain {

  public static final int SEARCH_LAYER = 4;
  public static final List<Integer> PLAYER_INDEX_POLL = new ArrayList<>(List.of(1, 2));


  @Override
  public List<Coordinate> decideMove(BlockGrid checkBoard, Integer currentPlayerIndex) {
    Vector<Coordinate> bestMove = miniMax(SEARCH_LAYER, checkBoard, currentPlayerIndex, true);
    return new ArrayList<>(bestMove);
  }

  public Vector<Coordinate> miniMax(Integer searchLayer, BlockGrid checkBoard,
      Integer currentPlayerIndex, boolean maxPlayer) {
    if (searchLayer == 0) {
      return new Vector<>();
    }

    BlockGrid newCheckBoard = checkBoard.clone();

    Vector<Coordinate> bestMove = new Vector<>();
    if (maxPlayer) {
      float value = Float.NEGATIVE_INFINITY;
      for (int i = 0; i < newCheckBoard.getAllBlocks().getBlockStructureHeight(); i++) {
        for (int j = 0; j < newCheckBoard.getAllBlocks().getBlockStructureWidth(); j++) {
          Coordinate chosenCoordinate = new Coordinate(j, i);
          if (newCheckBoard.getAllBlocks().getBlock(chosenCoordinate).getBlockState()
              .getPlayerID()
              == currentPlayerIndex) {
            List<Coordinate> potentialMoves = newCheckBoard.getAllBlocks()
                .getBlock(new Coordinate(j, i)).getAvailablePosition(currentPlayerIndex,
                    newCheckBoard.getAllBlocks());
            for (Coordinate potentialCoordinate : potentialMoves) {
              BlockGrid newGrid = newCheckBoard.clone();
              Vector<Coordinate> moves = new Vector<>(
                  List.of(chosenCoordinate, potentialCoordinate));

              if (evaluateMove(
                  applyMove(newGrid, currentPlayerIndex,
                      miniMax(searchLayer - 1, applyMove(newGrid, currentPlayerIndex, moves),
                          playerTakeTurn(currentPlayerIndex), false)
                  ),
                  currentPlayerIndex) > value) {
                bestMove = new Vector<>(List.of(chosenCoordinate, potentialCoordinate));
              }
            }
          }
        }
      }
    }
    else{
      float value = Float.POSITIVE_INFINITY;
      for (int i = 0; i < newCheckBoard.getAllBlocks().getBlockStructureHeight(); i++) {
        for (int j = 0; j < newCheckBoard.getAllBlocks().getBlockStructureWidth(); j++) {
          Coordinate chosenCoordinate = new Coordinate(j, i);
          if (newCheckBoard.getAllBlocks().getBlock(chosenCoordinate).getBlockState()
              .getPlayerID()
              == currentPlayerIndex) {
            List<Coordinate> potentialMoves = newCheckBoard.getAllBlocks()
                .getBlock(new Coordinate(j, i)).getAvailablePosition(currentPlayerIndex,
                    newCheckBoard.getAllBlocks());
            for (Coordinate potentialCoordinate : potentialMoves) {
              BlockGrid newGrid = newCheckBoard.clone();
              Vector<Coordinate> moves = new Vector<>(
                  List.of(chosenCoordinate, potentialCoordinate));

              if (evaluateMove(
                  applyMove(newGrid, currentPlayerIndex,
                      miniMax(searchLayer - 1, applyMove(newGrid, currentPlayerIndex, moves),
                          playerTakeTurn(currentPlayerIndex), true)
                  ),
                  currentPlayerIndex) < value) {
                bestMove = new Vector<>(List.of(chosenCoordinate, potentialCoordinate));
              }
            }
          }
        }
      }
    }
    return bestMove;
  }

  private BlockGrid applyMove(BlockGrid checkBoard, int currentPlayerIndex,
      Vector<Coordinate> moves) {
    for (Coordinate move : moves) {
      checkBoard.play(move, currentPlayerIndex);
    }
    return checkBoard;
  }


  private float evaluateMove(BlockGrid checkBoard, int currentPlayerIndex) {
    float score = 0;
    for (List<Block> blockList : checkBoard.getAllBlocks().getBlockStructure()) {
      for (Block block : blockList) {
        if (block.getPlayerID() == currentPlayerIndex) {
          score += 1;
        } else if (block.getPlayerID() != currentPlayerIndex && PLAYER_INDEX_POLL
            .contains(block.getPlayerID())) {
          score -= 1;
        }
      }
    }
    return score;
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
