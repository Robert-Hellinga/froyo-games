package ooga.model.checkerboard.blockgrid;

import ooga.Coordinate;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.OthelloBlock;

public class OthelloBlockGrid extends BlockGrid {

  public OthelloBlockGrid(String gameType, BlockConfigStructure allBlockConfig, int numPlayers) {
    super(gameType, allBlockConfig, numPlayers);
    setAvailablePosition(1, Coordinate.INVALID_COORDINATE);
  }

  @Override
  public void setAvailablePosition(int currentPlayerIndex, Coordinate chosenBlock) {
    for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
      for (int j = allBlocks.getBlockStructureHeight() - 1; j >= 0; j--) {
        Coordinate coordinate = new Coordinate(i, j);
        for (Coordinate potentialMove : allBlocks.getBlock(coordinate)
            .getAvailablePosition(currentPlayerIndex, allBlocks)) {
          allBlocks.getBlock(potentialMove).getBlockState().makePotentialMove();
        }
      }
    }
  }

  @Override
  public void play(Coordinate passInCoordinate, Integer currentPlayerIndex) {
    if (allBlocks.getBlock(passInCoordinate).getBlockState().isPotentialMove()) {
      allBlocks.getBlock(passInCoordinate).setPlayerID(currentPlayerIndex);
      flipPiece(passInCoordinate, currentPlayerIndex);
    }
    unsetAllBlockPotential();
  }

  @Override
  public BlockGrid clone() {
    BlockGrid blockGrid = new OthelloBlockGrid(gameType, allBlocks.getBlockConfigStructure(),
        numPlayers);
    for (int i = 0; i < allBlocks.getBlockStructureHeight(); i++) {
      for (int j = 0; j < allBlocks.getBlockStructureWidth(); j++) {
        blockGrid.getAllBlocks().getBlock(new Coordinate(j, i)).setBlockState(
            allBlocks.getBlock(new Coordinate(j, i)).getBlockState().clone()
        );
      }
    }
    return blockGrid;
  }

  private void flipPiece(Coordinate passInCoordinate, int currentPlayerIndex) {
    for (Coordinate neighbors : OthelloBlock.getValidNeighbor(allBlocks, passInCoordinate)) {
      if (!allBlocks.getBlock(neighbors).getBlockState().isEmpty()
          && allBlocks.getBlock(neighbors).getBlockState().getPlayerID() != currentPlayerIndex) {
        int xIncrement = neighbors.xCoordinate() - passInCoordinate.xCoordinate();
        int yIncrement = neighbors.yCoordinate() - passInCoordinate.yCoordinate();
        Coordinate extendedNeighbor = new Coordinate(neighbors.xCoordinate() + xIncrement,
            neighbors.yCoordinate() + yIncrement);
        while (true) {
          if (extendedNeighbor.xCoordinate() < 0 || extendedNeighbor.yCoordinate() < 0 || extendedNeighbor.xCoordinate() >= allBlocks
              .getBlockStructureWidth() || extendedNeighbor.yCoordinate() >= allBlocks
              .getBlockStructureHeight()){
            break;
          }
          if (!allBlocks.getBlock(extendedNeighbor).getBlockState().isEmpty()) {
            if (allBlocks.getBlock(extendedNeighbor).getBlockState().getPlayerID() == currentPlayerIndex) {
              changePieceSeriesState(passInCoordinate, extendedNeighbor, currentPlayerIndex, allBlocks);
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
}
