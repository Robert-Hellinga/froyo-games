package ooga.model.checkerboard;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

  public void setChosenBlock(Coordinate chosenBlock) {
    this.chosenBlock = chosenBlock;
  }

  //this method is only for checkers game
  public List<Coordinate> getAvailablePosition(int currentPlayerIndex) {
    if (chosenBlock.equals(Coordinate.INVALID_COORDINATE)) {
      return new ArrayList<>();
    }
    return allBlocks.getBlock(chosenBlock).getAvailablePosition(currentPlayerIndex, allBlocks);
  }

  //this method is only for checkers game
  public void removeCheckedPiece(Coordinate newPosition, Coordinate originalPosition){
    int xMovement = newPosition.xCoordinate() - originalPosition.xCoordinate();
    int yMovement = newPosition.yCoordinate() - originalPosition.yCoordinate();
    if (Math.abs(xMovement) == 2 && Math.abs(yMovement) == 2 ){
      Coordinate pieceToRemoveCoordinate = new Coordinate(
          originalPosition.xCoordinate() + xMovement / 2,
          originalPosition.yCoordinate() + yMovement / 2
      );
      allBlocks.getBlock(pieceToRemoveCoordinate).setEmpty(true);
      allBlocks.getBlock(pieceToRemoveCoordinate).setPlayerID(0);
    }
  }
}
