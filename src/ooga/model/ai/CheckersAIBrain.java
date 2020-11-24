package ooga.model.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import ooga.Coordinate;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.CheckersBlockGrid;
import ooga.model.game.CheckersGame;

/**
 * This it he CheckersAIBrain which implements AIBrain interface, the function is to give the AI
 * decision to play Checkers Game
 *
 * @author Yixuan Li
 * @see ooga.model.ai.AIBrain
 */
public class CheckersAIBrain implements AIBrain {

  public static final int SEARCH_LAYER = 2;

  /**
   * AIBrain would make a list of decisions based on the blockGrid and who is the currentPlayer.
   *
   * @param checkBoard         The checker block grid of the checkers game
   * @param currentPlayerIndex An Integer represents the index of the current player
   * @return return a List of Coordinates represents the AI decision
   */
  @Override
  public List<Coordinate> decideMove(BlockGrid checkBoard, Integer currentPlayerIndex) {
    Vector<Coordinate> bestMove = miniMax(SEARCH_LAYER, checkBoard, currentPlayerIndex, true);
    return new ArrayList<>(bestMove);
  }

  /**
   * This method is the miniMax algorithm, this algorithm is how Connect4 AI works
   *
   * @param searchLayer        An integer represents the remaining search depth
   * @param checkBoard         The given checkers block grid
   * @param currentPlayerIndex An Integer represents the index of the current player
   * @param maxPlayer          A boolean parameter, it is true when the score of this player should
   *                           be maximized, and it is false when the score of this player should be
   *                           minimized
   * @return return a Vector of Coordinates which is the decision that miniMax algorithm give
   */
  public Vector<Coordinate> miniMax(Integer searchLayer, BlockGrid checkBoard,
      Integer currentPlayerIndex, boolean maxPlayer) {
    if (searchLayer == 0) {
      return new Vector<>();
    }

    BlockGrid newCheckBoard = new CheckersBlockGrid(checkBoard);

    Vector<Coordinate> bestMove = new Vector<>();
    if (maxPlayer) {
      float value = Float.NEGATIVE_INFINITY;
      for (int i = 0; i < newCheckBoard.getAllBlocks().getBlockStructureHeight(); i++) {
        for (int j = 0; j < newCheckBoard.getAllBlocks().getBlockStructureWidth(); j++) {
          Coordinate chosenCoordinate = new Coordinate(j, i);
          if (newCheckBoard.getAllBlocks().getBlock(chosenCoordinate)
              .getPlayerID()
              == currentPlayerIndex) {
            List<Coordinate> potentialMoves = newCheckBoard.getAllBlocks()
                .getBlock(new Coordinate(j, i)).getAvailablePositions(currentPlayerIndex,
                    newCheckBoard.getAllBlocks());
            for (Coordinate potentialCoordinate : potentialMoves) {
              BlockGrid newGrid = new CheckersBlockGrid(newCheckBoard);
              Vector<Coordinate> moves = new Vector<>(
                  List.of(chosenCoordinate, potentialCoordinate));

              if (evaluateMove(
                  applyMove(newGrid, currentPlayerIndex,
                      miniMax(searchLayer - 1, applyMove(newGrid, currentPlayerIndex, moves),
                          CheckersBlockGrid
                              .playerTakeTurn(currentPlayerIndex, CheckersGame.PLAYER_INDEX_POLL),
                          false)
                  ),
                  currentPlayerIndex) > value) {
                bestMove = new Vector<>(List.of(chosenCoordinate, potentialCoordinate));
              }
            }
          }
        }
      }
    } else {
      float value = Float.POSITIVE_INFINITY;
      for (int i = 0; i < newCheckBoard.getAllBlocks().getBlockStructureHeight(); i++) {
        for (int j = 0; j < newCheckBoard.getAllBlocks().getBlockStructureWidth(); j++) {
          Coordinate chosenCoordinate = new Coordinate(j, i);
          if (newCheckBoard.getAllBlocks().getBlock(chosenCoordinate)
              .getPlayerID()
              == currentPlayerIndex) {
            List<Coordinate> potentialMoves = newCheckBoard.getAllBlocks()
                .getBlock(new Coordinate(j, i)).getAvailablePositions(currentPlayerIndex,
                    newCheckBoard.getAllBlocks());
            for (Coordinate potentialCoordinate : potentialMoves) {
              BlockGrid newGrid = new CheckersBlockGrid(newCheckBoard);
              Vector<Coordinate> moves = new Vector<>(
                  List.of(chosenCoordinate, potentialCoordinate));

              if (evaluateMove(
                  applyMove(newGrid, currentPlayerIndex,
                      miniMax(searchLayer - 1, applyMove(newGrid, currentPlayerIndex, moves),
                          CheckersBlockGrid
                              .playerTakeTurn(currentPlayerIndex, CheckersGame.PLAYER_INDEX_POLL),
                          true)
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
        } else if (block.getPlayerID() != currentPlayerIndex && CheckersGame.PLAYER_INDEX_POLL
            .contains(block.getPlayerID())) {
          score -= 1;
        }
      }
    }
    return score;
  }

}
