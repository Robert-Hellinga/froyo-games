package ooga.model.checkerboard;

public abstract class Block {

  //  protected BlockState blockState;
  protected int PlayerID;
  protected boolean isEmpty = false;

  public Block(int blockConfig) {
    initiateBlockState(blockConfig);
  }

  public abstract void initiateBlockState(int blockConfig);
}
