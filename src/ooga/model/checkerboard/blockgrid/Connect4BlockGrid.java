package ooga.model.checkerboard.blockgrid;

import ooga.Coordinate;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.checkerboard.blockgrid.BlockGrid;

public class Connect4BlockGrid extends BlockGrid {

  public Connect4BlockGrid(String gameType, BlockConfigStructure allBlockConfig, int numPlayers) {
    super(gameType, allBlockConfig, numPlayers);
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
//    if (!chosenBlock.equals(Coordinate.INVALID_COORDINATE)) {
//      for (Coordinate coordinate : allBlocks.getBlock(chosenBlock)
//          .getAvailablePosition(currentPlayerIndex, allBlocks)) {
//        allBlocks.getBlock(coordinate).getBlockState().makePotentialMove();
//      }
//    }
  }

  public void addBlock(int currentPlayerIndex, Coordinate chosenBlock) {
    allBlocks.getBlock(chosenBlock).setPlayerID(currentPlayerIndex);
  }
}
