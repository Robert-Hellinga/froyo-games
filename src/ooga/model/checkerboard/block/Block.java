package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public abstract class Block {

  protected BlockState blockState = new BlockState();
  protected int state;
  protected boolean isEmpty;
  protected int playerID;
  protected boolean isChosen;
  protected boolean isPotentialMove;

  protected Coordinate coordinate;

  public Block(Integer blockConfig, Coordinate coordinate) {
    initiateBlockState(blockConfig);
    this.coordinate = coordinate;
  }

  public void initiateBlockState(int blockConfig) {
    this.state = blockConfig;
    setPlayerID(blockConfig);
    this.isEmpty = blockConfig == 0;
    this.blockState.isChosen = false;
    this.blockState.isPotentialMove = false;
  }

  public abstract List<Coordinate> getAvailablePositions(int currentPlayerIndex,
                                                         BlockStructure allBlocks);

  public boolean getIsEmpty() {
    return isEmpty;
  }

  public int getPlayerID() {
    return playerID;
  }

  public void setPlayerID(int player) {
    isEmpty = false;
    this.playerID = (player % 2);
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

  public void setPotentialMove(boolean potentialMove) {
    isPotentialMove = potentialMove;
  }
}
