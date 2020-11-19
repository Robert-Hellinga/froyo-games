package ooga.model.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;
import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.Connect4BlockGrid;

public class Connect4AIBrain implements AIBrain {

  private static final List<Integer> PLAYER_INDEX_POLL = new ArrayList<>(List.of(1, 2));
  private static final int DEPTH = 5;
  private static final int WINDOWLENGTH = 4;
  private int AIID;

  @Override
  public List<Coordinate> decideMove(BlockGrid connect4Grid, Integer currentPlayerIndex) {
    AIID = currentPlayerIndex;
    List<Coordinate> aiMoves = new ArrayList<>();

    Pair<Coordinate, Float> bestMove = miniMax(connect4Grid, DEPTH, Float.NEGATIVE_INFINITY,
        Float.POSITIVE_INFINITY, true, currentPlayerIndex);

    aiMoves.add(bestMove.getKey());

    return aiMoves;
  }

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

  private int get_next_open_row(BlockGrid connect4Grid, int col) {
    for (int row = connect4Grid.getAllBlocks().getBlockStructureHeight() - 1; row >= 0; row--) {
      if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getIsEmpty()) {
        return row;
      }
    }
    return -1;
  }


  private int playerTakeTurn(Integer currentPlayerIndex) {
    int index = PLAYER_INDEX_POLL.indexOf(currentPlayerIndex);
    if (index == PLAYER_INDEX_POLL.size() - 1) {
      return PLAYER_INDEX_POLL.get(0);
    } else {
      return PLAYER_INDEX_POLL.get(index + 1);
    }
  }

  private boolean isTerminalNode(BlockGrid connect4grid) {
    return connect4grid.isWinningMove(PLAYER_INDEX_POLL.get(0)) || connect4grid.isWinningMove(
        PLAYER_INDEX_POLL.get(1)) || connect4grid.getAllBlocks().getBlock(new Coordinate(0, 0))
        .getAvailablePositions(0, connect4grid.getAllBlocks()).size() == 0;
  }


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
