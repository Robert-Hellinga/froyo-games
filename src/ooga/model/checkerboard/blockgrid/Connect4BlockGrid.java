package ooga.model.checkerboard.blockgrid;

import java.util.List;
import ooga.Coordinate;

public class Connect4BlockGrid extends BlockGrid {

  public Connect4BlockGrid(String gameType, List<List<Integer>> allBlockConfig, int numPlayers) {
    super(gameType, allBlockConfig, numPlayers);
  }

  public Connect4BlockGrid(BlockGrid connect4Grid) {
    super(connect4Grid);
  }

  /**
   * set all the available position for the current player in a connect4 game
   * @param currentPlayerIndex the index of the current player
   * @param chosenBlock the coordinate of the chosen block
   */

  @Override
  public void setAvailablePositions(int currentPlayerIndex, Coordinate chosenBlock) {
    for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
      for (int j = allBlocks.getBlockStructureHeight() - 1; j >= 0; j--) {
        Coordinate coordinate = new Coordinate(i, j);
        if (allBlocks.getBlock(coordinate).getIsEmpty()) {
          allBlocks.getBlock(coordinate).makePotentialMove();
          break;
        }
      }
    }
  }

  /**
   * play the connect4 game
   * @param passInCoordinate the given coordinate
   * @param currentPlayerIndex the index of the current player
   */

  @Override
  public void play(Coordinate passInCoordinate, Integer currentPlayerIndex) {
    int xCoordinate = passInCoordinate.xCoordinate();
    for (int j = allBlocks.getBlockStructureHeight() - 1; j >= 0; j--) {
      Coordinate coordinate = new Coordinate(xCoordinate, j);
      if (allBlocks.getBlock(coordinate).getIsEmpty()) {
        allBlocks.getBlock(coordinate).setPlayerID(currentPlayerIndex);
        finishARound = true;
        break;
      }
    }
    unsetAllBlockPotentials();
  }

  /**
   * check if last move is a winning move in a connect4 game
   * @param playerID the current index of the player
   * @return whether last move is a winning move in a connect4 game
   */

  public boolean isWinningMove(int playerID) {
    // Check horizontal locations for win
    for (int col = 0; col < allBlocks.getBlockStructureWidth() - 3; col++) {
      for (int row = allBlocks.getBlockStructureHeight() - 1;
          row >= 0; row--) {
        if (allBlocks.getBlock(new Coordinate(col, row)).getPlayerID() == playerID
            && allBlocks.getBlock(new Coordinate(col + 1, row)).getPlayerID()
            == playerID
            && allBlocks.getBlock(new Coordinate(col + 2, row)).getPlayerID()
            == playerID
            && allBlocks.getBlock(new Coordinate(col + 3, row)).getPlayerID()
            == playerID) {
          return true;
        }
      }
    }

    // Check vertical locations for win
    for (int col = 0; col < allBlocks.getBlockStructureWidth(); col++) {
      for (int row = allBlocks.getBlockStructureHeight() - 1; row >= 3; row--) {
        if (allBlocks.getBlock(new Coordinate(col, row)).getPlayerID() == playerID
            && allBlocks.getBlock(new Coordinate(col, row - 1)).getPlayerID()
            == playerID
            && allBlocks.getBlock(new Coordinate(col, row - 2)).getPlayerID()
            == playerID
            && allBlocks.getBlock(new Coordinate(col, row - 3)).getPlayerID()
            == playerID) {
          return true;
        }
      }
    }

    // Check positively sloped diagonals
    for (int col = 0; col < allBlocks.getBlockStructureWidth() - 3; col++) {
      for (int row = allBlocks.getBlockStructureHeight() - 1; row >= 3; row--) {
        if (allBlocks.getBlock(new Coordinate(col, row)).getPlayerID() == playerID
            && allBlocks.getBlock(new Coordinate(col + 1, row - 1)).getPlayerID()
            == playerID
            && allBlocks.getBlock(new Coordinate(col + 2, row - 2)).getPlayerID()
            == playerID
            && allBlocks.getBlock(new Coordinate(col + 3, row - 3)).getPlayerID()
            == playerID) {
          return true;
        }
      }
    }

    // Check negatively sloped diagonals
    for (int col = 0; col < allBlocks.getBlockStructureWidth() - 3; col++) {
      for (int row = 0; row < allBlocks.getBlockStructureHeight() - 3; row++) {
        if (allBlocks.getBlock(new Coordinate(col, row)).getPlayerID() == playerID
            && allBlocks.getBlock(new Coordinate(col + 1, row + 1)).getPlayerID()
            == playerID
            && allBlocks.getBlock(new Coordinate(col + 2, row + 2)).getPlayerID()
            == playerID
            && allBlocks.getBlock(new Coordinate(col + 3, row + 3)).getPlayerID()
            == playerID) {
          return true;
        }
      }
    }
    return false;
  }
}
