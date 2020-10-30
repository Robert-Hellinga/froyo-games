package ooga.model.checkerboard;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import ooga.Coordinate;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.checkerboard.block.Block;

public class BlockGrid {

  /*public enum BlockState {
    CHECKER_PLAYER_1,
    CHECKER_PLAYER_2,
    EMPTY,
  }*/

  private final int numPlayers;

  private final BlockStructure allBlocks;
  private String gameType;
  private Coordinate chosenBlock;

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
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
  }

  public BlockStructure getAllBlocks() {
    return allBlocks;
  }

  public void setChosenBlock(Coordinate chosenBlock){
    this.chosenBlock = chosenBlock;
  }

  //this method is only for checkers game
  public List<Coordinate> getAvailablePosition(int currentPlayerIndex){
    return allBlocks.getBlock(chosenBlock).getAvailablePosition(currentPlayerIndex, allBlocks);
  }
}
