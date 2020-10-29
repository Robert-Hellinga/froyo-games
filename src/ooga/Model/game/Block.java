package ooga.Model.game;

import ooga.Model.game.GridGame.BlockState;

public abstract class Block {
  protected BlockState blockState;

  public Block(int blockConfig){
    initiateBlockState(blockConfig);
  }

  public abstract void initiateBlockState(int blockConfig);
}
