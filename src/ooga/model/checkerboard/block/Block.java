package ooga.model.checkerboard.block;

import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public abstract class Block {

  //  protected BlockState blockState;
  protected int PlayerID;
  protected boolean isEmpty = false;
  protected Coordinate coordinate;

  public Block(int blockConfig, Coordinate coordinate) {
    initiateBlockState(blockConfig);
    this.coordinate = coordinate;
  }

  public abstract void initiateBlockState(int blockConfig);

  public abstract List<Coordinate> getAvailablePosition(int currentPlayerIndex, BlockStructure allBlocks);

  public boolean getIsEmpty(){
    return isEmpty;
  }
}
