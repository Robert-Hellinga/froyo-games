package ooga.model.checkerboard;

import java.util.ArrayList;
import java.util.List;

public class BlockStructure {

  private List<List<Block>> blockStructure;

  public BlockStructure(String gameType, BlockConfigStructure allBlockConfig) {
    initiateBlockStructure(allBlockConfig, gameType);
  }

  private void initiateBlockStructure(BlockConfigStructure allBlockConfig, String gameType) {
    List<List<Block>> initialAllBlocks = new ArrayList<>();
    for (int i = 0; i < BlockGrid.SIZE; i++) {
      List<Block> blockLine = new ArrayList<>();
      for (int j = 0; j < BlockGrid.SIZE; j++) {
        Integer cellConfig = allBlockConfig.getBlockConfigStructure().get(i).get(j);
        blockLine.add(BlockGrid.createBlock(gameType, cellConfig));
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
