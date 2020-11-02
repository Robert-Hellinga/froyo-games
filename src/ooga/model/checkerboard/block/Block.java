package ooga.model.checkerboard.block;

import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.BlockState.CheckerBlockState;

public abstract class Block {

  //  protected BlockState blockState;
  protected int PlayerID;
  protected boolean isEmpty = false;
  protected Coordinate coordinate;
  protected List<BlockState> blockStates;

  public Block(int blockConfig, Coordinate coordinate) {
    initiateBlockState(blockConfig);
    this.coordinate = coordinate;
  }

  public abstract void initiateBlockState(int blockConfig);

  public abstract List<Coordinate> getAvailablePosition(int currentPlayerIndex,
      BlockStructure allBlocks);

  public boolean getIsEmpty() {
    return isEmpty;
  }

  public int getPlayerID() {
    return PlayerID;
  }

  public void setPlayerID(int playerID) {
    PlayerID = playerID;
  }

  public void setEmpty(boolean isEmpty) {
    this.isEmpty = isEmpty;
  }

  public List<BlockState> getState() {
    return blockStates;
  }

  public void addState(BlockState blockState) {
    blockStates.add(blockState);
  }
}
