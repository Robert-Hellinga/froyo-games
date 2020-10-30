package ooga.model.checkerboard;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import ooga.exceptions.ClassOrMethodNotFoundException;

public class BlockGrid {

  public enum BlockState {
    CHECKER_PLAYER_1,
    CHECKER_PLAYER_2,
    EMPTY,
  }

  public final static int SIZE = 8;

  private BlockStructure allBlocks;
  private String gameType;

  public BlockGrid(String gameType, BlockConfigStructure allBlockConfig) {
    this.gameType = gameType;
    this.allBlocks = new BlockStructure(this.gameType, allBlockConfig);
  }


  public static Block createBlock(String gameType, int blockConfig)
      throws ClassOrMethodNotFoundException {
    try {
      Class<?> block = Class.forName("ooga.model.checkerboard.block." + gameType + "Block");
      Class<?>[] param = {Integer.class};
      Constructor<?> cons = block.getConstructor(param);
      Object[] paramObject = {blockConfig};
      Object gameBlock = cons.newInstance(paramObject);
      return (Block) gameBlock;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
  }

  public BlockStructure getAllBlocks() {
    return allBlocks;
  }
}
