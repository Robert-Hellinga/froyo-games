package ooga.model.checkerboard.blockgrid;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import ooga.Coordinate;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.block.BlockState;

public abstract class BlockGrid{

  protected final int numPlayers;
  protected final BlockStructure allBlocks;
  protected String gameType;

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

  public abstract void setAvailablePosition(int currentPlayerIndex, Coordinate chosenBlock);


  public boolean hasChosenBlock() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).getBlockState().isChosen()) {
          return true;
        }
      }
    }
    return false;
  }

  public Coordinate getChosenBlockCoordianate(){
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).getBlockState().isChosen()) {
          return new Coordinate(i, j);
        }
      }
    }
    return Coordinate.INVALID_COORDINATE;
  }

  public void unChoseAllBlock() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).getBlockState().isChosen()) {
          allBlocks.getBlock(new Coordinate(i, j)).getBlockState().unchoose();
        }
      }
    }
  }

  public void unsetAllBlockPotential(){
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).getBlockState().isPotentialMove()) {
          allBlocks.getBlock(new Coordinate(i, j)).getBlockState().unmakePotentialMove();
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


}