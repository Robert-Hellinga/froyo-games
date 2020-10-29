package ooga.Model.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlockStructure {
  private List<List<Block>> blockStructure;

  public BlockStructure(String gameType, BlockConfigStructure allBlockConfig){
    initiateBlockStructure(allBlockConfig, gameType);
  }

  private void initiateBlockStructure(BlockConfigStructure allBlockConfig, String gameType){
    List<List<Block>> initialAllBlocks = new ArrayList<>();
    for (int i = 0; i < GridGame.SIZE; i++){
      List<Block> blockLine = new ArrayList<>();
      for (int j = 0; j < GridGame.SIZE; j++){
        Integer cellConfig = allBlockConfig.getBlockConfigStructure().get(i).get(j);
        blockLine.add(GridGame.createBlock(gameType, cellConfig));
      }
      initialAllBlocks.add(blockLine);
    }
  }

  public List<List<Block>> getBlockStructure() {
    return blockStructure;
  }

  public void setBlockStructure(List<List<Block>> blockStructure) {
    this.blockStructure = blockStructure;
  }
}
