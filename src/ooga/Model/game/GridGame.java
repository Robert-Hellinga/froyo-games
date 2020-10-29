package ooga.Model.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ooga.Model.Exception.ClassOrMethodNotFoundException;

public class GridGame {
  public enum BlockState {
    CHECKER_PLAYER_1,
    CHECKER_PLAYER_2,
    EMPTY,
  }
  public final static int SIZE = 8;

  private BlockStructure allBlocks;
  private String gameType;

  public GridGame(String gameType, BlockConfigStructure allBlockConfig){
    this.gameType = gameType;
    this.allBlocks = new BlockStructure(gameType, allBlockConfig);
  }


  public static Block createBlock(String gameType, int blockConfig) throws ClassOrMethodNotFoundException{
    try{
      Class<?> block = Class.forName("ooga.Model.game.block." + gameType + "Block");
      Class<?>[] param = {Integer.class};
      Constructor<?> cons = block.getConstructor(param);
      Object[] paramObject = {blockConfig};
      Object gameBlock = cons.newInstance(paramObject);
      return (Block) gameBlock;
    }
    catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e){
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
  }
}
