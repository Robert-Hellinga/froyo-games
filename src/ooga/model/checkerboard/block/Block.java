package ooga.model.checkerboard.block;

import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public abstract class Block {

  protected BlockState blockState = new BlockState();
  protected int state;
  protected boolean isEmpty;
  protected int playerID;

  protected Coordinate coordinate;

  public Block(Integer blockConfig, Coordinate coordinate) {
    initiateBlockState(blockConfig);
    this.coordinate = coordinate;
  }

  public void initiateBlockState(int blockConfig) {
    this.isEmpty = blockConfig == 0;
    this.playerID = blockConfig;
    //TODO: Move to appropriate class
    this.blockState.isChosen = false;
    this.blockState.isPotentialMove = false;
    this.blockState.isKing = false;
  }

  public abstract List<Coordinate> getAvailablePosition(int currentPlayerIndex,
      BlockStructure allBlocks);

  public boolean getIsEmpty() {
    return isEmpty;
  }

  public int getPlayerID() {
    return playerID;
  }

  public void setPlayerID(int player) {
    isEmpty = false;
    this.playerID = player;
  }

  public void setEmpty(){
    this.playerID = 0;
    this.isEmpty = true;
    this.state = 0;
  }

  public BlockState getBlockState() {
    return blockState;
  }

  public void setBlockState(BlockState blockState) {
    this.blockState = blockState;
  }
}
