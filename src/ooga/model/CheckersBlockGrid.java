package ooga.model;

import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockConfigStructure;

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

  @Override
  public void play(Coordinate passInCoordinate, Integer currentPlayerIndex){
    if (hasChosenBlock()) {
      if (allBlocks.getBlock(passInCoordinate).getPlayerID()
          == currentPlayerIndex) {
        unChoseAllBlock();
        unsetAllBlockPotential();
        getAllBlocks().getBlock(passInCoordinate).getBlockState().choose();
        setAvailablePosition(currentPlayerIndex, passInCoordinate);
      } else if (allBlocks.getBlock(passInCoordinate).getBlockState()
          .isPotentialMove()) {
        removeCheckedPiece(passInCoordinate, getChosenBlockCoordianate());
        moveBlock(getChosenBlockCoordianate(), passInCoordinate);
        makeBlockKing(passInCoordinate);
        unChoseAllBlock();
        unsetAllBlockPotential();
        getAllBlocks().getBlock(passInCoordinate).setPlayerID(currentPlayerIndex);
        finishARound = true;
      }
    } else {
      if (!allBlocks.getBlock(passInCoordinate).getIsEmpty()
          && allBlocks.getBlock(passInCoordinate).getPlayerID()
          == currentPlayerIndex) {
        allBlocks.getBlock(passInCoordinate).getBlockState().choose();
        setAvailablePosition(currentPlayerIndex, passInCoordinate);
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
      crossPiece(pieceToRemoveCoordinate);
    }
    if (Math.abs(xMovement) > 2 || Math.abs(yMovement) > 2){
      for (int xIndicator: List.of(-2, 2)){
        boolean breakLoop = false;
        for (int yIndicator: List.of(-2, 2)){
          if (newPosition.xCoordinate() + xIndicator > 0 && newPosition.xCoordinate() + xIndicator < allBlocks
              .getBlockStructureWidth() - 1 && newPosition.yCoordinate() + yIndicator > 0 && newPosition.yCoordinate() + yIndicator < allBlocks
              .getBlockStructureHeight() - 1) {
            Coordinate interCoordinate = new Coordinate(newPosition.xCoordinate() + xIndicator,
                newPosition.yCoordinate() + yIndicator);
            if (allBlocks.getBlock(interCoordinate).getBlockState().isPotentialMove()){
              removeCheckedPiece(interCoordinate, originalPosition);
              crossPiece(new Coordinate(newPosition.xCoordinate() + xIndicator/2,
                  newPosition.yCoordinate() + yIndicator/2));
              breakLoop = true;
              break;
            }
          }
        }
        if (breakLoop){
          break;
        }
      }
    }
  }

  private void crossPiece(Coordinate pieceToRemoveCoordinate){
    allBlocks.getBlock(pieceToRemoveCoordinate).getBlockState().setEmpty(true);
    allBlocks.getBlock(pieceToRemoveCoordinate).getBlockState().setPlayerID(0);
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

  @Override
  public BlockGrid clone() {
    BlockGrid blockGrid = new CheckersBlockGrid(gameType, allBlocks.getBlockConfigStructure(), numPlayers);
    for (int i = 0; i < allBlocks.getBlockStructureHeight(); i++){
      for (int j = 0; j < allBlocks.getBlockStructureWidth(); j++){
        blockGrid.getAllBlocks().getBlock(new Coordinate(j,i)).setBlockState(
            allBlocks.getBlock(new Coordinate(j,i)).getBlockState().clone()
        );
      }
    }
    return blockGrid;
  }
}
