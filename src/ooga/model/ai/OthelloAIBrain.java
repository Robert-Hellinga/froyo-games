package ooga.model.ai;

import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import ooga.Coordinate;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.OthelloBlockGrid;

public class OthelloAIBrain implements AIBrain {

  private static final List<Integer> PLAYER_INDEX_POLL = new ArrayList<>(List.of(1, 2));
  private static final int DEPTH = 5;
  private int AIID;


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

  private List<Coordinate> getPotentialMoves(BlockGrid othelloBoard, Integer currentPlayerIndex) {
    List<Coordinate> potentialMove = new ArrayList<>();
    for (int i = 0; i < othelloBoard.getAllBlocks().getBlockStructureWidth(); i++) {
      for (int j = 0; j < othelloBoard.getAllBlocks().getBlockStructureHeight(); j++) {
        Coordinate coordinate = new Coordinate(i, j);
        potentialMove.addAll(othelloBoard.getAllBlocks().getBlock(coordinate)
            .getAvailablePosition(currentPlayerIndex, othelloBoard.getAllBlocks()));
      }
    }
    return potentialMove;
  }

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


  private int playerTakeTurn(Integer currentPlayerIndex) {
    int index = PLAYER_INDEX_POLL.indexOf(currentPlayerIndex);
    if (index == PLAYER_INDEX_POLL.size() - 1) {
      return PLAYER_INDEX_POLL.get(0);
    } else {
      return PLAYER_INDEX_POLL.get(index + 1);
    }
  }
}
