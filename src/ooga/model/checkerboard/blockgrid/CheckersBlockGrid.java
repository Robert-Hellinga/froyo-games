package ooga.model.checkerboard.blockgrid;

import ooga.Coordinate;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.checkerboard.blockgrid.BlockGrid;

public class CheckersBlockGrid extends BlockGrid {

  public CheckersBlockGrid(String gameType, BlockConfigStructure allBlockConfig, int numPlayers){
    super(gameType, allBlockConfig, numPlayers);
  }

  @Override
  public void setAvailablePosition(int currentPlayerIndex, Coordinate chosenBlock) {
    if (!chosenBlock.equals(Coordinate.INVALID_COORDINATE)) {
      for (Coordinate coordinate :
          allBlocks.getBlock(chosenBlock).getAvailablePosition(currentPlayerIndex, allBlocks)){
        allBlocks.getBlock(coordinate).getBlockState().makePotentialMove();
      }
    }
  }

  public void removeCheckedPiece(Coordinate newPosition, Coordinate originalPosition) {
    int xMovement = newPosition.xCoordinate() - originalPosition.xCoordinate();
    int yMovement = newPosition.yCoordinate() - originalPosition.yCoordinate();
    if (Math.abs(xMovement) == 2 && Math.abs(yMovement) == 2) {
      Coordinate pieceToRemoveCoordinate = new Coordinate(
          originalPosition.xCoordinate() + xMovement / 2,
          originalPosition.yCoordinate() + yMovement / 2
      );
      allBlocks.getBlock(pieceToRemoveCoordinate).getBlockState().setEmpty(true);
      allBlocks.getBlock(pieceToRemoveCoordinate).getBlockState().setPlayerID(0);
    }
  }

  public void makeBlockKing(Coordinate newCoordinate){
    if (newCoordinate.yCoordinate() == 0
        && allBlocks.getBlock(newCoordinate).getPlayerID() == 2){
      allBlocks.getBlock(newCoordinate).getBlockState().makeKing();
    }
    else if (newCoordinate.yCoordinate() == allBlocks.getBlockStructureHeight() - 1
        && allBlocks.getBlock(newCoordinate).getPlayerID() == 1){
      allBlocks.getBlock(newCoordinate).getBlockState().makeKing();
    }
  }
}
