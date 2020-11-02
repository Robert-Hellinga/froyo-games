package ooga.model.checkerboard.block;

import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public abstract class Block {

  protected BlockState blockState;
  protected Coordinate coordinate;

  public Block(int blockConfig, Coordinate coordinate) {
    initiateBlockState(blockConfig);
    this.coordinate = coordinate;
  }

  public void initiateBlockState(int blockConfig) {
    if (blockConfig == 0) {
      this.isEmpty = true;
    } else {
      this.PlayerID = blockConfig;
    }
  }

  public abstract List<Coordinate> getAvailablePosition(int currentPlayerIndex,
      BlockStructure allBlocks);

  public boolean getIsEmpty() {
    return this.blockState.isEmpty;
  }

  public int getPlayerID() {
    return this.blockState.PlayerID;
  }

  public void setPlayerID(int playerID) {
    this.blockState.PlayerID = playerID;
  }

  public void setEmpty(boolean isEmpty) {
    this.blockState.isEmpty = isEmpty;
  }
}
