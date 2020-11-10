package ooga.model.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javafx.util.Pair;
import ooga.Coordinate;
import ooga.model.Player;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.blockgrid.BlockGrid;

public class Connect4AIBrain implements AIBrain {

  public static final List<Integer> PLAYER_INDEX_POLL = new ArrayList<>(List.of(1, 2));
  public static final int DEPTH = 2;

  @Override
  public List<Coordinate> decideMove(BlockGrid connect4Grid, Integer currentPlayerIndex) {
    List<Coordinate> aiMoves = new ArrayList<>();

    Pair<Coordinate, Float> bestMove = miniMax(connect4Grid, DEPTH, Float.NEGATIVE_INFINITY,
        Float.POSITIVE_INFINITY, true, currentPlayerIndex);

    aiMoves.add(bestMove.getKey());

    return aiMoves;
  }

  private Pair<Coordinate, Float> miniMax(BlockGrid connect4Grid, int depth, float alpha,
      float beta,
      boolean maximizingPlayer, int currentPlayerIndex) {

    if (depth == 0) {
      return new Pair<Coordinate, Float>(new Coordinate(-1, -1), (float) 0);
    }

    BlockGrid newConnect4Board = connect4Grid.clone();
    ArrayList<Coordinate> bestMove = new ArrayList<>();
    List<Coordinate> potentialMoves = newConnect4Board.getAllBlocks().getBlock(new Coordinate(0, 0))
        .getAvailablePosition(0, newConnect4Board.getAllBlocks());

    if (maximizingPlayer) {
      Coordinate coordinate_move = new Coordinate(0, 0);
      float value = Float.NEGATIVE_INFINITY;
      for (Coordinate coor : potentialMoves) {
        int next_open_row = get_next_open_row(newConnect4Board, coor.xCoordinate());
        newConnect4Board = newConnect4Board.clone();
        newConnect4Board
            .play(new Coordinate(coor.xCoordinate(), next_open_row), currentPlayerIndex);
        Float new_score = miniMax(newConnect4Board, depth - 1, alpha, beta, false,
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
        newConnect4Board = newConnect4Board.clone();
        newConnect4Board
            .play(new Coordinate(coor.xCoordinate(), next_open_row), currentPlayerIndex);
        Float new_score = miniMax(newConnect4Board, depth - 1, alpha, beta, true,
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


  private boolean isWinning_Move(BlockGrid connect4Grid, Player player) {
    // Check horizontal loacations for win
    for (int col = 0; col < connect4Grid.getAllBlocks().getBlockStructureWidth() - 3; col++) {
      for (int row = connect4Grid.getAllBlocks().getBlockStructureHeight() - 1;
          row >= connect4Grid.getAllBlocks().getBlockStructureHeight() - 4; row--) {
        if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID() == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 1, row)).getPlayerID()
            == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 2, row)).getPlayerID()
            == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 3, row)).getPlayerID()
            == player
            .getID()) {
          return true;
        }
      }
    }

    // Check vertical locations for win
    for (int col = 0; col < connect4Grid.getAllBlocks().getBlockStructureWidth(); col++) {
      for (int row = connect4Grid.getAllBlocks().getBlockStructureHeight() - 1; row >= 3; row--) {
        if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID() == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row - 1)).getPlayerID()
            == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row - 2)).getPlayerID()
            == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row - 3)).getPlayerID()
            == player
            .getID()) {
          return true;
        }
      }
    }

    // Check positively sloped diagonals
    for (int col = 0; col < connect4Grid.getAllBlocks().getBlockStructureWidth() - 3; col++) {
      for (int row = connect4Grid.getAllBlocks().getBlockStructureHeight() - 1; row >= 3; row--) {
        if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID() == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 1, row - 1)).getPlayerID()
            == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 2, row - 2)).getPlayerID()
            == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 3, row - 3)).getPlayerID()
            == player
            .getID()) {
          return true;
        }
      }
    }

    // Check negatively sloped diagonals
    for (int col = 0; col < connect4Grid.getAllBlocks().getBlockStructureWidth() - 3; col++) {
      for (int row = 0; row < connect4Grid.getAllBlocks().getBlockStructureHeight() - 3; row++) {
        if (connect4Grid.getAllBlocks().getBlock(new Coordinate(col, row)).getPlayerID() == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 1, row + 1)).getPlayerID()
            == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 2, row + 2)).getPlayerID()
            == player
            .getID()
            && connect4Grid.getAllBlocks().getBlock(new Coordinate(col + 3, row + 3)).getPlayerID()
            == player
            .getID()) {
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

}
