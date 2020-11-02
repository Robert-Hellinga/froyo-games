package ooga.model.checkerboard;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.block.Block;

public class BlockStructure {

  private List<List<Block>> blockStructure = new ArrayList<>();

  public BlockStructure(String gameType, BlockConfigStructure allBlockConfig) {
    initiateBlockStructure(allBlockConfig, gameType);
  }

  private void initiateBlockStructure(BlockConfigStructure allBlockConfig, String gameType) {
    for (int i = 0; i < allBlockConfig.getBlockConfigStructureHeight(); i++) {
      List<Block> blockLine = new ArrayList<>();
      for (int j = 0; j < allBlockConfig.getBlockConfigStructureWidth(); j++) {
        Integer cellConfig = allBlockConfig.getBlockConfigStructure().get(i).get(j);
        blockLine.add(BlockGrid.createBlock(gameType, cellConfig, new Coordinate(j, i)));
      }
      blockStructure.add(blockLine);
    }
  }

  public List<List<Block>> getBlockStructure() {
    return blockStructure;
  }

  public void setBlockStructure(List<List<Block>> blockStructure) {
    this.blockStructure = blockStructure;
  }

  public Block getBlock(Coordinate blockCoordinate) {
    return blockStructure.get(blockCoordinate.yCoordinate()).get(blockCoordinate.xCoordinate());
  }

  public int getBlockStructureHeight() {
    return blockStructure.size();
  }

  public int getBlockStructureWidth() {
    return blockStructure.get(0).size();
  }
}
