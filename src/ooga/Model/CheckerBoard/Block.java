package ooga.Model.CheckerBoard;

import ooga.Model.CheckerBoard.BlockGrid.BlockState;

public abstract class Block {
  protected BlockState blockState;

  public Block(int blockConfig){
    initiateBlockState(blockConfig);
  }

  public abstract void initiateBlockState(int blockConfig);
}
