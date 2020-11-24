package ooga.model.checkerboard.blockgrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.OthelloBlock;
import ooga.model.game.Game;

public class OthelloBlockGrid extends BlockGrid {

  public OthelloBlockGrid(String gameType, List<List<Integer>> allBlockConfig, int numPlayers) {
    super(gameType, allBlockConfig, numPlayers);
    setAvailablePositions(1, Coordinate.INVALID_COORDINATE);
  }

  public OthelloBlockGrid(BlockGrid othelloGrid) {
    super(othelloGrid);
  }

  /**
   * set all the available position for the current player in a othello game
   * @param currentPlayerIndex the index of the current player
   * @param chosenBlock the coordinate of the chosen block
   */

  @Override
  public void setAvailablePositions(int currentPlayerIndex, Coordinate chosenBlock) {
    for (Coordinate coordinate : getAllPotentialMoves(currentPlayerIndex)) {
      allBlocks.getBlock(coordinate).makePotentialMove();
    }
  }

  /**
   * play the othello game
   * @param passInCoordinate the given coordinate
   * @param currentPlayerIndex the index of the current player
   */

  @Override
  public void play(Coordinate passInCoordinate, Integer currentPlayerIndex) {
    if (allBlocks.getBlock(passInCoordinate).isPotentialMove()) {
      allBlocks.getBlock(passInCoordinate).setPlayerID(currentPlayerIndex);
      flipPiece(passInCoordinate, currentPlayerIndex);
    }
    unsetAllBlockPotentials();
  }

  /**
   * flip the pieces in the othello game
   * @param passInCoordinate the coordinate that is chosen
   * @param currentPlayerIndex index of the current player
   */

  private void flipPiece(Coordinate passInCoordinate, int currentPlayerIndex) {
    for (Coordinate neighbors : OthelloBlock.getValidNeighbor(allBlocks, passInCoordinate)) {
      if (!allBlocks.getBlock(neighbors).getIsEmpty()
          && allBlocks.getBlock(neighbors).getPlayerID() != currentPlayerIndex) {
        int xIncrement = neighbors.xCoordinate() - passInCoordinate.xCoordinate();
        int yIncrement = neighbors.yCoordinate() - passInCoordinate.yCoordinate();
        Coordinate extendedNeighbor = new Coordinate(neighbors.xCoordinate() + xIncrement,
            neighbors.yCoordinate() + yIncrement);
        while (true) {
          if (extendedNeighbor.xCoordinate() < 0 || extendedNeighbor.yCoordinate() < 0
              || extendedNeighbor.xCoordinate() >= allBlocks
              .getBlockStructureWidth() || extendedNeighbor.yCoordinate() >= allBlocks
              .getBlockStructureHeight()) {
            break;
          } else if (!allBlocks.getBlock(extendedNeighbor).getIsEmpty()) {
            if (allBlocks.getBlock(extendedNeighbor).getPlayerID()
                == currentPlayerIndex) {
              changePieceSeriesState(passInCoordinate, extendedNeighbor, currentPlayerIndex,
                  allBlocks);
              finishARound = true;
              break;
            }
          } else {
            break;
          }
          extendedNeighbor = new Coordinate(extendedNeighbor.xCoordinate() + xIncrement,
              extendedNeighbor.yCoordinate() + yIncrement);
        }
      }
    }
  }

  /**
   * change the state of a series of piece at once
   * @param startCoordinate the starting coordinate of the series of pieces
   * @param endCoordinate the ending coordinate of the series of pieces
   * @param targetPlayerID the target player ID
   * @param allBlocks all the blocks
   */

  private void changePieceSeriesState(Coordinate startCoordinate, Coordinate endCoordinate,
      int targetPlayerID, BlockStructure allBlocks) {
    int xIncrement = 0;
    int yIncrement = 0;
    if (endCoordinate.xCoordinate() != startCoordinate.xCoordinate()) {
      xIncrement = (endCoordinate.xCoordinate() - startCoordinate.xCoordinate()) / Math
          .abs((endCoordinate.xCoordinate() - startCoordinate.xCoordinate()));
    }
    if (endCoordinate.yCoordinate() != startCoordinate.yCoordinate()) {
      yIncrement = (endCoordinate.yCoordinate() - startCoordinate.yCoordinate()) / Math
          .abs((endCoordinate.yCoordinate() - startCoordinate.yCoordinate()));
    }

    Coordinate interCoordinate = new Coordinate(startCoordinate.xCoordinate() + xIncrement,
        startCoordinate.yCoordinate() + yIncrement);
    for (int i = 0; i < Math
        .max(Math.abs((endCoordinate.xCoordinate() - startCoordinate.xCoordinate())),
            Math.abs((endCoordinate.yCoordinate() - startCoordinate.yCoordinate()))); i++) {
      allBlocks.getBlock(interCoordinate).setPlayerID(targetPlayerID);
      interCoordinate = new Coordinate(interCoordinate.xCoordinate() + xIncrement,
          interCoordinate.yCoordinate() + yIncrement);
    }
  }

  /**
   * check whether the last move is a winning move
   * @param playerID the current index of the player
   * @return whether the last move is a winning move
   */

  @Override
  public boolean isWinningMove(int playerID) {
    boolean haveEmptyBlock = false;
    for (int i = 0; i < allBlocks.getBlockStructureHeight(); i++) {
      for (int j = 0; j < allBlocks.getBlockStructureWidth(); j++) {
        Coordinate coordinate = new Coordinate(j, i);
        if (allBlocks.getBlock(coordinate).getIsEmpty()) {
          haveEmptyBlock = true;
        }
      }
    }
    return !haveEmptyBlock;
  }

  /**
   * get the index of the winning player
   * @return the index of the winning player
   */

  public int getWinningPlayerIndex() {
    List<Integer> pieceCounter = new ArrayList<>();
    for (int i = 0; i < allBlocks.getBlockStructureHeight(); i++) {
      for (int j = 0; j < allBlocks.getBlockStructureWidth(); j++) {
        Coordinate coordinate = new Coordinate(j, i);
        if (!allBlocks.getBlock(coordinate).getIsEmpty()) {
          int playerIndex = allBlocks.getBlock(coordinate).getPlayerID();
          pieceCounter.set(playerIndex-1, pieceCounter.get(playerIndex) + 1);
        }

      }
    }
    return pieceCounter.indexOf(Collections.max(pieceCounter)) + 1;
  }
}
