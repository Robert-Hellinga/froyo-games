package ooga.Model.game.block;

import ooga.Model.game.Block;
import ooga.Model.game.GridGame.BlockState;

public class CheckersBlock extends Block {

  public CheckersBlock(int blockConfig){
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
