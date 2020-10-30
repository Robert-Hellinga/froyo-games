package ooga.model.checkerboard.block;

import ooga.model.checkerboard.Block;

public class CheckersBlock extends Block {

  public CheckersBlock(Integer blockConfig) {
    super(blockConfig);
  }

  @Override
  public void initiateBlockState(int blockConfig) {
    if (blockConfig == 0) {
      this.isEmpty = true;
    } else {
      this.PlayerID = blockConfig;
    }
  }
}
