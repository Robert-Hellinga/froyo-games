package ooga.game;

import java.util.Collection;

public class BlockStructure {
  private Collection<Collection<Block>> blockStructure;

  public BlockStructure(Collection<Collection<Block>> allBlocks){
    this.blockStructure = allBlocks;
  }

  public Collection<Collection<Block>> getBlockStructure() {
    return blockStructure;
  }

  public void setBlockStructure(Collection<Collection<Block>> blockStructure) {
    this.blockStructure = blockStructure;
  }
}
