package ooga.model.checkerboard.block;

import ooga.model.checkerboard.Block;
import ooga.model.checkerboard.BlockGrid.BlockState;

public class CheckersBlock extends Block {

  public CheckersBlock(Integer blockConfig){
    super(blockConfig);
  }

  @Override
  public void initiateBlockState(int blockConfig) {
    switch (blockConfig){
      case 1 -> blockState = BlockState.CHECKER_PLAYER_1;
      case 2 -> blockState = BlockState.CHECKER_PLAYER_2;
      default -> blockState = BlockState.EMPTY;
    }
  }


}
