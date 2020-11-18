package ooga.model.checkerboard.block;

import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public abstract class Block {

  protected BlockState blockState;
  protected Coordinate coordinate;

  public Block(Integer blockConfig, Coordinate coordinate) {
    blockState = new BlockState();
    initiateBlockState(blockConfig);
    this.coordinate = coordinate;
  }

  public void initiateBlockState(int blockConfig) {
    this.blockState.isEmpty = blockConfig == 0;
    this.blockState.PlayerID = blockConfig;
    this.blockState.isChosen = false;
    this.blockState.isPotentialMove = false;
    this.blockState.isKing = false;
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
    this.blockState.setEmpty(false);
    this.blockState.PlayerID = playerID;
  }

  public void setEmpty(boolean isEmpty) {
    this.blockState.PlayerID = 0;
    this.blockState.isEmpty = isEmpty;
  }

  public BlockState getBlockState() {
    return blockState;
  }

  public void setBlockState(BlockState blockState) {
    this.blockState = blockState;
  }
}
