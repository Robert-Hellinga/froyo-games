package ooga.model.checkerboard;

import ooga.model.checkerboard.BlockGrid.BlockState;

public abstract class Block {
  protected BlockState blockState;

  public Block(int blockConfig){
    initiateBlockState(blockConfig);
  }

  public abstract void initiateBlockState(int blockConfig);
}
