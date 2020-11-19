package ooga.model.checkerboard.blockgrid;

import ooga.Coordinate;

import java.util.List;

public class Connect4BlockGrid extends BlockGrid {

  public Connect4BlockGrid(String gameType, List<List<Integer>> allBlockConfig, int numPlayers) {
    super(gameType, allBlockConfig, numPlayers);
  }

  public Connect4BlockGrid(BlockGrid connect4Grid) {
    super(connect4Grid);
  }

  @Override
  public void setAvailablePosition(int currentPlayerIndex, Coordinate chosenBlock) {
    for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
      for (int j = allBlocks.getBlockStructureHeight() - 1; j >= 0; j--) {
        Coordinate coordinate = new Coordinate(i, j);
        if (allBlocks.getBlock(coordinate).getIsEmpty()) {
          allBlocks.getBlock(coordinate).getBlockState().makePotentialMove();
          break;
        }
      }
    }
  }

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
