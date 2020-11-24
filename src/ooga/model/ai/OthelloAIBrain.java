package ooga.model.ai;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.OthelloBlockGrid;

/**
 * This is the OthelloAIBrain which implements AIBrain interface, the function is to give the AI
 * decision to play Othello Game
 *
 * @author Jincheng He
 * @see ooga.model.ai.AIBrain
 */
public class OthelloAIBrain implements AIBrain {

  private static final List<Integer> PLAYER_INDEX_POLL = new ArrayList<>(List.of(1, 2));
  private static final int DEPTH = 3;
  private int AIID;


  /**
   * AIBrain would make a list of decisions based on the blockGrid and who is the currentPlayer.
   *
   * @param othelloBoard       The othelloBoard of the othello game
   * @param currentPlayerIndex An Integer represents the index of the current player
   * @return return a List of Coordinates represents the AI decision
   * @see AIBrain#decideMove(BlockGrid, Integer)
   */
  @Override
  public List<Coordinate> decideMove(BlockGrid othelloBoard, Integer currentPlayerIndex) {
    AIID = currentPlayerIndex;

    List<Coordinate> aiMoves = new ArrayList<>();

    List<Coordinate> potentialMoves = getPotentialMoves(othelloBoard, currentPlayerIndex);

    Pair<Coordinate, Float> bestMove = miniMax(othelloBoard, DEPTH, Float.NEGATIVE_INFINITY,
        Float.POSITIVE_INFINITY, true, currentPlayerIndex);

    aiMoves.add(bestMove.getKey());
    return aiMoves;
  }

  /**
   * This method is the miniMax algorithm, this algorithm is how Othello AI works
   *
   * @param othelloGrid        The blockGrid of othello game
   * @param depth              The AI search depth
   * @param alpha              The parameter alpha, this is a parameter for pruning in miniMax
   *                           algorithm
   * @param beta               The parameter beta, this is a parameter for pruning in miniMax
   *                           algorithm
   * @param maximizingPlayer   A boolean parameter, it is true when the score of this player should
   *                           be maximized, and it is false when the score of this player should be
   *                           minimized
   * @param currentPlayerIndex An Integer represents the index of the current player
   * @return return a Pair of Coordinate and a Float value which represents the value of the miniMax
   * algorithm
   */
  private Pair<Coordinate, Float> miniMax(BlockGrid othelloGrid, int depth, float alpha, float beta,
      boolean maximizingPlayer, int currentPlayerIndex) {

    boolean isTerminal = checkBoardIsFull(othelloGrid);
    if (depth == 0 || isTerminal) {
      if (isTerminal) {
        if (evaluateBoardValue(othelloGrid, AIID) > 0) {
          return new Pair<>(null, Float.POSITIVE_INFINITY);
        } else if (evaluateBoardValue(othelloGrid, playerTakeTurn(AIID)) < 0) {
          return new Pair<>(null, Float.NEGATIVE_INFINITY);
        } else {
          return new Pair<>(null, (float) 0);
        }
      } else {
        return new Pair<>(null, evaluateBoardValue(othelloGrid, AIID));
      }
    }
    BlockGrid newOthelloGrid = new OthelloBlockGrid(othelloGrid);
    List<Coordinate> potentialMoves = getPotentialMoves(newOthelloGrid, currentPlayerIndex);

    if (maximizingPlayer) {

      Coordinate coordinate_move = new Coordinate(0, 0);
      float value = Float.NEGATIVE_INFINITY;
      for (Coordinate coor : potentialMoves) {
        BlockGrid newnewOthelloGrid = new OthelloBlockGrid(newOthelloGrid);
        newnewOthelloGrid
            .play(new Coordinate(coor.xCoordinate(), coor.yCoordinate()), currentPlayerIndex);

        Float new_score = miniMax(newnewOthelloGrid, depth - 1, alpha, beta, false,
            playerTakeTurn(currentPlayerIndex)).getValue();
        if (new_score > value) {
          value = new_score;
          coordinate_move = new Coordinate(coor.xCoordinate(), coor.yCoordinate());
        }
        alpha = Math.max(alpha, beta);
        if (alpha >= beta) {
          break;
        }
      }
      return new Pair<>(coordinate_move, value);
    } else {

      Coordinate coordinate_move = new Coordinate(0, 0);
      float value = Float.POSITIVE_INFINITY;
      for (Coordinate coor : potentialMoves) {
        BlockGrid newnewOthelloGrid = new OthelloBlockGrid(newOthelloGrid);
        newnewOthelloGrid
            .play(new Coordinate(coor.xCoordinate(), coor.yCoordinate()), currentPlayerIndex);
        Float new_score = miniMax(newnewOthelloGrid, depth - 1, alpha, beta, true,
            playerTakeTurn(currentPlayerIndex)).getValue();
        if (new_score < value) {
          value = new_score;
          coordinate_move = new Coordinate(coor.xCoordinate(), coor.yCoordinate());
        }
        beta = Math.min(beta, value);
        if (alpha >= beta) {
          break;
        }
      }
      return new Pair<>(coordinate_move, value);
    }
  }

  /**
   * This is the method which evaluate the value of the given board
   *
   * @param blockGrid          A given othello block grid
   * @param currentPlayerIndex A integer which represents the index of a given player
   * @return return a float value which represents the evaluation value
   */
  private float evaluateBoardValue(BlockGrid blockGrid, int currentPlayerIndex) {
    float currentPlayerCount = 0;
    float otherPlayerCount = 0;
    for (int i = 0; i < blockGrid.getAllBlocks().getBlockStructureWidth(); i++) {
      for (int j = 0; j < blockGrid.getAllBlocks().getBlockStructureHeight(); j++) {
        int blockPlayerID = blockGrid.getAllBlocks().getBlock(new Coordinate(i, j)).getPlayerID();
        if (blockPlayerID == currentPlayerIndex) {
          currentPlayerCount++;
        } else if (blockPlayerID != 0) {
          otherPlayerCount++;
        }
      }
    }
    return currentPlayerCount - otherPlayerCount;
  }

  /**
   * This method gives a List of Coordinate which represents the potential moves
   *
   * @param othelloBoard       A given othello glock grid
   * @param currentPlayerIndex A integer which represents the index
   * @return return a List of Coordinates which represents the potential moves
   */
  private List<Coordinate> getPotentialMoves(BlockGrid othelloBoard, Integer currentPlayerIndex) {
    List<Coordinate> potentialMove = new ArrayList<>();
    for (int i = 0; i < othelloBoard.getAllBlocks().getBlockStructureWidth(); i++) {
      for (int j = 0; j < othelloBoard.getAllBlocks().getBlockStructureHeight(); j++) {
        Coordinate coordinate = new Coordinate(i, j);
        potentialMove.addAll(othelloBoard.getAllBlocks().getBlock(coordinate)
            .getAvailablePositions(currentPlayerIndex, othelloBoard.getAllBlocks()));
      }
    }
    return potentialMove;
  }

  /**
   * This method would check whether the board is full
   *
   * @param blockGrid A given block grid
   * @return return true if the given board is full and false the vice versa
   */
  private boolean checkBoardIsFull(BlockGrid blockGrid) {
    for (int i = 0; i < blockGrid.getAllBlocks().getBlockStructureWidth(); i++) {
      for (int j = 0; j < blockGrid.getAllBlocks().getBlockStructureHeight(); j++) {
        if (blockGrid.getAllBlocks().getBlock(new Coordinate(i, j)).getIsEmpty()) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * Change the current player to be another one
   *
   * @param currentPlayerIndex A integer which represents the index of the current player
   * @return return a integer which represents the index of another player
   */
  private int playerTakeTurn(Integer currentPlayerIndex) {
    int index = PLAYER_INDEX_POLL.indexOf(currentPlayerIndex);
    if (index == PLAYER_INDEX_POLL.size() - 1) {
      return PLAYER_INDEX_POLL.get(0);
    } else {
      return PLAYER_INDEX_POLL.get(index + 1);
    }
  }
}
