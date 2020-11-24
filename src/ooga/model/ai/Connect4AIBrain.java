package ooga.model.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;
import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.Connect4BlockGrid;

/**
 * This is the Connect4AIBrain which implements AIBrain interface, the function is to give the AI
 * decision to play Connect4 Game
 *
 * @author Jincheng He
 * @see ooga.model.ai.AIBrain
 */
public class Connect4AIBrain implements AIBrain {

  private static final List<Integer> PLAYER_INDEX_POLL = new ArrayList<>(List.of(1, 2));
  private static final int DEPTH = 5;
  private static final int WINDOWLENGTH = 4;
  private int AIID;

  /**
   * AIBrain would make a list of decisions based on the blockGrid and who is the currentPlayer.
   *
   * @param connect4Grid       The connect4Grid of the connect4 Game
   * @param currentPlayerIndex An Integer represents the index of the current player
   * @return return a List of Coordinates represents the AI decision
   * @see AIBrain#decideMove(BlockGrid, Integer)
   */
  @Override
  public List<Coordinate> decideMove(BlockGrid connect4Grid, Integer currentPlayerIndex) {
    AIID = currentPlayerIndex;
    List<Coordinate> aiMoves = new ArrayList<>();

    Pair<Coordinate, Float> bestMove = miniMax(connect4Grid, DEPTH, Float.NEGATIVE_INFINITY,
        Float.POSITIVE_INFINITY, true, currentPlayerIndex);

    aiMoves.add(bestMove.getKey());

    return aiMoves;
  }

  /**
   * This method is the miniMax algorithm, this algorithm is how Connect4 AI works
   *
   * @param connect4Grid       The blockGrid of connect4 game
   * @param depth              The remaining AI search depth
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
  private Pair<Coordinate, Float> miniMax(BlockGrid connect4Grid, int depth, float alpha,
      float beta, boolean maximizingPlayer, int currentPlayerIndex) {

    boolean isTerminal = isTerminalNode(connect4Grid);
    if (depth == 0 || isTerminal) {
      if (isTerminal) {
        if (connect4Grid.isWinningMove(AIID)) {
          return new Pair<>(null, Float.POSITIVE_INFINITY);
        } else if (connect4Grid.isWinningMove(AIID)) {
          return new Pair<>(null, Float.NEGATIVE_INFINITY);
        } else {
          return new Pair<>(null, (float) 0);
        }
      } else {
        return new Pair<>(null, scorePositoin(connect4Grid, AIID));
      }
    }

    BlockGrid newConnect4Board = new Connect4BlockGrid(connect4Grid);
    List<Coordinate> potentialMoves = newConnect4Board.getAllBlocks().getBlock(new Coordinate(0, 0))
        .getAvailablePositions(0, newConnect4Board.getAllBlocks());

    return getGoodMove(potentialMoves, newConnect4Board, currentPlayerIndex, depth, alpha, beta,
        maximizingPlayer);
  }

  /**
   * This is a step in miniMax algorithm, this method returns a Pair of Coordinate and a Float
   * value
   *
   * @param potentialMoves     A List of Coordinate represents potential moves
   * @param newConnect4Board   A copied version of Connnect4 Board
   * @param currentPlayerIndex A Integer which represents the index of the current player in the
   *                           player List
   * @param depth              The integer represents the remaining depth to search
   * @param alpha              The alpha parameter which would be used in pruning search
   * @param beta               The beta parameter which would be used in pruning search
   * @param maximizingPlayer   A boolean parameter, it is true when the score of this player should
   *                           be maximized, and it is false when the score of this player should be
   *                           minimized
   * @return return a Pair of Coordinate and a Float value which represents a good move in the
   * current search depth
   */
  private Pair<Coordinate, Float> getGoodMove(List<Coordinate> potentialMoves,
      BlockGrid newConnect4Board, int currentPlayerIndex, int depth, float alpha, float beta,
      boolean maximizingPlayer) {
    Coordinate coordinate_move = new Coordinate(0, 0);

    float value;

    if (maximizingPlayer) {
      value = Float.NEGATIVE_INFINITY;
    } else {
      value = Float.POSITIVE_INFINITY;
    }

    for (Coordinate coor : potentialMoves) {
      int next_open_row = get_next_open_row(newConnect4Board, coor.xCoordinate());
      BlockGrid newnewConnect4Board = new Connect4BlockGrid(newConnect4Board);
      newnewConnect4Board
          .play(new Coordinate(coor.xCoordinate(), next_open_row), currentPlayerIndex);
      Float new_score = miniMax(newnewConnect4Board, depth - 1, alpha, beta, !maximizingPlayer,
          playerTakeTurn(currentPlayerIndex)).getValue();

      if ((maximizingPlayer && (new_score > value)) || (!maximizingPlayer && (new_score < value))) {
        value = new_score;
        coordinate_move = new Coordinate(coor.xCoordinate(), coor.yCoordinate());
      }
      if (maximizingPlayer) {
        alpha = Math.max(alpha, value);
      } else {
        beta = Math.min(beta, value);
      }
      if (alpha >= beta) {
        break;
      }
    }
    return new Pair<Coordinate, Float>(coordinate_move, value);
  }

  /**
   * This method is to find which row is open for play given the known column
   *
   * @param connect4Grid The connect4Grid of the game
   * @param col          The column number
   * @return return an integer which represents the row which is open to play
   */
  private int get_next_open_row(BlockGrid connect4Grid, int col) {
    for (int row = connect4Grid.getAllBlocks().getBlockStructureHeight() - 1; row >= 0; row--) {
      if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getIsEmpty()) {
        return row;
      }
    }
    return -1;
  }


  /**
   * Change the current Player in each searching depth
   *
   * @param currentPlayerIndex The integer which represents the index of the current player
   * @return return an Integer of another player
   */
  private int playerTakeTurn(Integer currentPlayerIndex) {
    int index = PLAYER_INDEX_POLL.indexOf(currentPlayerIndex);
    if (index == PLAYER_INDEX_POLL.size() - 1) {
      return PLAYER_INDEX_POLL.get(0);
    } else {
      return PLAYER_INDEX_POLL.get(index + 1);
    }
  }

  /**
   * This method is to determine whether the game would be terminated given a known connect4 block
   * grid
   *
   * @param connect4grid The given connect4 block grid
   * @return return true if the game would be terminated and false the vice versa
   */
  private boolean isTerminalNode(BlockGrid connect4grid) {
    return connect4grid.isWinningMove(PLAYER_INDEX_POLL.get(0)) || connect4grid.isWinningMove(
        PLAYER_INDEX_POLL.get(1)) || connect4grid.getAllBlocks().getBlock(new Coordinate(0, 0))
        .getAvailablePositions(0, connect4grid.getAllBlocks()).size() == 0;
  }


  /**
   * This method is to evaluate the score of a given board
   *
   * @param connect4grid The given connect4 board
   * @param playerID     The integer which represents the player ID
   * @return return a float which represents the evaluation score of the given connect4 board
   */
  private float scorePositoin(BlockGrid connect4grid, int playerID) {
    float score = 0;

    // Center column score
    score += centerCount(connect4grid, playerID) * 3;

    // Horizontal Score
    score += horizontalScore(connect4grid, playerID);

    // Vertical Score
    score += verticalScore(connect4grid, playerID);

    // Diagonal Score
    score += diagonalScore(connect4grid, playerID);

    return score;
  }

  /**
   * This is a help function which is used in scorePosition method
   *
   * @param connect4grid The given connect4 board
   * @param playerID     The integer which represents the player ID
   * @return return a integer of this part of the score
   */
  private int centerCount(BlockGrid connect4grid, int playerID) {
    // Center column score
    int col = connect4grid.getAllBlocks().getBlockStructureWidth() / 2;
    int centerCount = 0;
    for (int row = 0; row < connect4grid.getAllBlocks().getBlockStructureHeight(); row++) {
      if (connect4grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID()
          == playerID) {
        centerCount++;
      }
    }
    return centerCount;
  }

  /**
   * This is a help function which is used in scorePosition method
   *
   * @param connect4grid The given connect4 board
   * @param playerID     The integer which represents the player ID
   * @return return a integer of this part of the score
   */
  private int horizontalScore(BlockGrid connect4grid, int playerID) {
    int score = 0;
    for (int r = connect4grid.getAllBlocks().getBlockStructureHeight() - 1; r >= 0; r--) {
      for (int c = 0; c < connect4grid.getAllBlocks().getBlockStructureWidth() - 3; c++) {
        ArrayList<Integer> window = new ArrayList<>();

        for (int c_ = c; c_ < c + WINDOWLENGTH; c_++) {
          if (!connect4grid.getAllBlocks().getBlock(new Coordinate(c_, r)).getIsEmpty()) {
            window.add(connect4grid.getAllBlocks().getBlock(new Coordinate(c_, r)).getPlayerID());
          } else {
            window.add(0);
          }
        }
        score += evaluateWindow(window, playerID);
      }
    }
    return score;
  }

  /**
   * This is a help function which is used in scorePosition method
   *
   * @param connect4grid The given connect4 board
   * @param playerID     The integer which represents the player ID
   * @return return a integer of this part of the score
   */
  private int verticalScore(BlockGrid connect4grid, int playerID) {
    int score = 0;
    for (int c = 0; c < connect4grid.getAllBlocks().getBlockStructureWidth(); c++) {
      for (int r = connect4grid.getAllBlocks().getBlockStructureHeight() - 1; r >= 3; r--) {
        ArrayList<Integer> window = new ArrayList<>();

        for (int r_ = r; r_ > r - WINDOWLENGTH; r_--) {
          if (!connect4grid.getAllBlocks().getBlock(new Coordinate(c, r_)).getIsEmpty()) {
            window.add(connect4grid.getAllBlocks().getBlock(new Coordinate(c, r_)).getPlayerID());
          } else {
            window.add(0);
          }
        }
        score += evaluateWindow(window, playerID);
      }
    }
    return score;
  }

  /**
   * This is a help function which is used in scorePosition method
   *
   * @param connect4grid The given connect4 board
   * @param playerID     The integer which represents the player ID
   * @return return a integer of this part of the score
   */
  private int diagonalScore(BlockGrid connect4grid, int playerID) {
    int score = 0;
    for (int r = connect4grid.getAllBlocks().getBlockStructureHeight() - 1; r >= 3; r--) {
      for (int c = 0; c < connect4grid.getAllBlocks().getBlockStructureWidth() - 3; c++) {

        ArrayList<Integer> windowPositive = new ArrayList<>();
        ArrayList<Integer> windowNegative = new ArrayList<>();

        for (int i = 0; i < WINDOWLENGTH; i++) {
          windowPositive.add(
              connect4grid.getAllBlocks().getBlock(new Coordinate(c + i, r - i)).getPlayerID());
          windowNegative.add(
              connect4grid.getAllBlocks().getBlock(new Coordinate(c + i, r - 3 + i)).getPlayerID());

          score += evaluateWindow(windowPositive, playerID);
          score += evaluateWindow(windowNegative, playerID);
        }
      }
    }
    return score;
  }


  /**
   * This is a helper method which would be used in other score evaluation methods such as
   * horizontalScore, verticalScore, diagonalScore
   *
   * @param window   A List of Integer which represents the window
   * @param playerID The integer which represents the player ID
   * @return return a float value which represents this evaluation score
   */
  private float evaluateWindow(List<Integer> window, int playerID) {
    float score = 0;
    int thisPlayerCount = Collections.frequency(window, playerID);
    int emptyCount = Collections.frequency(window, 0);
    int otherPlayerCount = window.size() - thisPlayerCount - emptyCount;
    if (thisPlayerCount == 4) {
      score += 100;
    } else if (thisPlayerCount == 3 && emptyCount == 1) {
      score += 5;
    } else if (thisPlayerCount == 2 && emptyCount == 2) {
      score += 2;
    }
    if (otherPlayerCount == 3 && emptyCount == 1) {
      score -= 4;
    }
    return score;
  }
}
