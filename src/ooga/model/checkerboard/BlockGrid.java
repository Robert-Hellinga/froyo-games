package ooga.model.checkerboard;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import ooga.Coordinate;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.block.BlockState;

public class BlockGrid {

  private final int numPlayers;

  private final BlockStructure allBlocks;
  private String gameType;

  public BlockGrid(String gameType, BlockConfigStructure allBlockConfig, int numPlayers) {
    this.gameType = gameType;
    this.allBlocks = new BlockStructure(this.gameType, allBlockConfig);
    this.numPlayers = numPlayers;
  }


  public static Block createBlock(String gameType, int blockConfig, Coordinate coordinate)
      throws ClassOrMethodNotFoundException {
    try {
      Class<?> block = Class.forName("ooga.model.checkerboard.block." + gameType + "Block");
      Class<?>[] param = {Integer.class, Coordinate.class};
      Constructor<?> cons = block.getConstructor(param);
      Object[] paramObject = {blockConfig, coordinate};
      Object gameBlock = cons.newInstance(paramObject);
      return (Block) gameBlock;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      e.printStackTrace();
      throw new ClassOrMethodNotFoundException("class or method is not found");

    }
  }

  public BlockStructure getAllBlocks() {
    return allBlocks;
  }

  public void setChosenBlock(Coordinate chosenBlock) {
//    this.chosenBlock = chosenBlock;
    allBlocks.getBlock(chosenBlock).getBlockState().choose();
  }

  //this method is only for checkers game
  public void setAvailablePosition(int currentPlayerIndex, Coordinate chosenBlock) {
    if (!chosenBlock.equals(Coordinate.INVALID_COORDINATE)) {
      for (Coordinate coordinate :
          allBlocks.getBlock(chosenBlock).getAvailablePosition(currentPlayerIndex, allBlocks)){
        allBlocks.getBlock(coordinate).getBlockState().makePotentialMove();
      }
    }
  }


  //this method is only for checkers game
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

  public boolean hasChosenBlock() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(j, i)).getBlockState().isChosen()) {
          return true;
        }
      }
    }
    return false;
  }

  public Coordinate getChosenBlockCoordianate(){
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(j, i)).getBlockState().isChosen()) {
          return new Coordinate(j, i);
        }
      }
    }
    return Coordinate.INVALID_COORDINATE;
  }

  public void unChoseAllBlock() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(j, i)).getBlockState().isChosen()) {
          allBlocks.getBlock(new Coordinate(j, i)).getBlockState().unchoose();
        }
      }
    }
  }

  public void unsetAllBlockPotential(){
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(j, i)).getBlockState().isPotentialMove()) {
          allBlocks.getBlock(new Coordinate(j, i)).getBlockState().unmakePotentialMove();
        }
      }
    }
  }

  public void moveBlock(Coordinate originalCoordiante, Coordinate newCoordinate){
    allBlocks.getBlock(newCoordinate).setBlockState(
        allBlocks.getBlock(originalCoordiante).getBlockState().clone()
    );
    allBlocks.getBlock(newCoordinate).getBlockState().setChosen(false);
    allBlocks.getBlock(originalCoordiante).initiateBlockState(0);
//    allBlocks.getBlock(originalCoordiante).setEmpty(true);
  }

  public void makeBlockKing(Coordinate newCoordinate){
    if (newCoordinate.yCoordinate() == 0
        && allBlocks.getBlock(newCoordinate).getPlayerID() == 2){
      allBlocks.getBlock(newCoordinate).getBlockState().makeKing();
    }
    else if (newCoordinate.yCoordinate() == allBlocks.getBlockStructureHeight()
      && allBlocks.getBlock(newCoordinate).getPlayerID() == 1){
      allBlocks.getBlock(newCoordinate).getBlockState().makeKing();
    }
  }
}
