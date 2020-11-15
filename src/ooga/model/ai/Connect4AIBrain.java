package ooga.model.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;
import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;

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
      float beta,
      boolean maximizingPlayer, int currentPlayerIndex) {

    boolean isTerminal = isTerminalNode(connect4Grid);
    if (depth == 0 || isTerminal) {
      if (isTerminal) {
        if (isWinning_Move(connect4Grid, AIID)) {
          return new Pair<>(null, Float.POSITIVE_INFINITY);
        } else if (isWinning_Move(connect4Grid, playerTakeTurn(AIID))) {
          return new Pair<>(null, Float.NEGATIVE_INFINITY);
        } else {
          return new Pair<>(null, (float) 0);
        }
      } else {
        return new Pair<>(null, scorePositoin(connect4Grid, AIID));
      }
    }

    BlockGrid newConnect4Board = connect4Grid.clone();
    List<Coordinate> potentialMoves = newConnect4Board.getAllBlocks().getBlock(new Coordinate(0, 0))
        .getAvailablePosition(0, newConnect4Board.getAllBlocks());

    if (maximizingPlayer) {
      Coordinate coordinate_move = new Coordinate(0, 0);
      float value = Float.NEGATIVE_INFINITY;
      for (Coordinate coor : potentialMoves) {
        int next_open_row = get_next_open_row(newConnect4Board, coor.xCoordinate());
        BlockGrid newnewConnect4Board = newConnect4Board.clone();
        newnewConnect4Board
            .play(new Coordinate(coor.xCoordinate(), next_open_row), currentPlayerIndex);
        Float new_score = miniMax(newnewConnect4Board, depth - 1, alpha, beta, false,
            playerTakeTurn(currentPlayerIndex)).getValue();
        if (new_score > value) {
          value = new_score;
          coordinate_move = new Coordinate(coor.xCoordinate(), coor.yCoordinate());
        }
        alpha = Math.max(alpha, value);
        if (alpha >= beta) {
          break;
        }
      }
      return new Pair<Coordinate, Float>(coordinate_move, value);
    } else {
      Coordinate coordinate_move = new Coordinate(0, 0);
      float value = Float.POSITIVE_INFINITY;
      for (Coordinate coor : potentialMoves) {
        int next_open_row = get_next_open_row(newConnect4Board, coor.xCoordinate());
        BlockGrid newnewConnect4Board = newConnect4Board.clone();
        newnewConnect4Board
            .play(new Coordinate(coor.xCoordinate(), next_open_row), currentPlayerIndex);
        Float new_score = miniMax(newnewConnect4Board, depth - 1, alpha, beta, true,
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
      return new Pair<Coordinate, Float>(coordinate_move, value);
    }
  }


  private boolean isWinning_Move(BlockGrid connect4Grid, int playerID) {
    // Check horizontal loacations for win
    for (int col = 0; col < connect4Grid.getAllBlocks().getBlockStructureWidth() - 3; col++) {
      for (int row = connect4Grid.getAllBlocks().getBlockStructureHeight() - 1;
          row >= 0; row--) {
        if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID() == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 1, row)).getPlayerID()
            == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 2, row)).getPlayerID()
            == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 3, row)).getPlayerID()
            == playerID) {
          return true;
        }
      }
    }

    // Check vertical locations for win
    for (int col = 0; col < connect4Grid.getAllBlocks().getBlockStructureWidth(); col++) {
      for (int row = connect4Grid.getAllBlocks().getBlockStructureHeight() - 1; row >= 3; row--) {
        if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID() == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row - 1)).getPlayerID()
            == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row - 2)).getPlayerID()
            == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row - 3)).getPlayerID()
            == playerID) {
          return true;
        }
      }
    }

    // Check positively sloped diagonals
    for (int col = 0; col < connect4Grid.getAllBlocks().getBlockStructureWidth() - 3; col++) {
      for (int row = connect4Grid.getAllBlocks().getBlockStructureHeight() - 1; row >= 3; row--) {
        if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID() == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 1, row - 1)).getPlayerID()
            == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 2, row - 2)).getPlayerID()
            == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 3, row - 3)).getPlayerID()
            == playerID) {
          return true;
        }
      }
    }

    // Check negatively sloped diagonals
    for (int col = 0; col < connect4Grid.getAllBlocks().getBlockStructureWidth() - 3; col++) {
      for (int row = 0; row < connect4Grid.getAllBlocks().getBlockStructureHeight() - 3; row++) {
        if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID() == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 1, row + 1)).getPlayerID()
            == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 2, row + 2)).getPlayerID()
            == playerID
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 3, row + 3)).getPlayerID()
            == playerID) {
          return true;
        }
      }
    }
    return false;
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
    return isWinning_Move(connect4grid, PLAYER_INDEX_POLL.get(0)) || isWinning_Move(connect4grid,
        PLAYER_INDEX_POLL.get(1)) || connect4grid.getAllBlocks().getBlock(new Coordinate(0, 0))
        .getAvailablePosition(0, connect4grid.getAllBlocks()).size() == 0;
  }


  private float scorePositoin(BlockGrid connect4grid, int playerID) {
    float score = 0;

    // Center column score
    int col = connect4grid.getAllBlocks().getBlockStructureWidth() / 2;
    int centerCount = 0;
    for (int row = 0; row < connect4grid.getAllBlocks().getBlockStructureHeight(); row++) {
      if (connect4grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID()
          == playerID) {
        centerCount++;
      }
    }
    score += centerCount * 3;

    // Horizontal Score
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

    // Vertical Score
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

    // Diagonal Score
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
