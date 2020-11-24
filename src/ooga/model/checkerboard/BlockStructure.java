package ooga.model.checkerboard;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.blockgrid.BlockGrid;

public class BlockStructure {

  private List<List<Block>> blockStructure;

  public BlockStructure(String gameType, List<List<Integer>> allBlockConfig) {
    blockStructure = new ArrayList<>();
    initiateBlockStructure(allBlockConfig, gameType);
  }

  private void initiateBlockStructure(List<List<Integer>> allBlockConfig, String gameType) {
    for (int i = 0; i < allBlockConfig.size(); i++) {
      List<Block> blockLine = new ArrayList<>();
      for (int j = 0; j < allBlockConfig.get(i).size(); j++) {
        Integer cellConfig = allBlockConfig.get(i).get(j);
        blockLine.add(BlockGrid.createBlock(gameType, cellConfig, new Coordinate(j, i)));
      }
      blockStructure.add(blockLine);
    }
  }

  /**
   * get all the blocks
   * @return all the blocks
   */

  public List<List<Block>> getBlockStructure() {
    return blockStructure;
  }

  /**
   * set all the block
   * @param blockStructure all the new blocks
   */

  public void setBlockStructure(List<List<Block>> blockStructure) {
    this.blockStructure = blockStructure;
  }

  /**
   * get the block at the given coordinate
   * @param blockCoordinate the coordinate of the block
   * @return the block at that coordinate
   */

  public Block getBlock(Coordinate blockCoordinate) {
    return blockStructure.get(blockCoordinate.yCoordinate()).get(blockCoordinate.xCoordinate());
  }

  /**
   * get the height of the block grid
   * @return the height of the block grid
   */

  public int getBlockStructureHeight() {
    return blockStructure.size();
  }

  /**
   * get the width of the block grid
   * @return the width of the block grid
   */

  public int getBlockStructureWidth() {
    return blockStructure.get(0).size();
  }

  /**
   * get the all the block configurations
   * @return all the block configurations
   */

  public List<List<Integer>> getBlockConfigStructure() {
    List<List<Integer>> allBlockConfig = new ArrayList<>();
    for (List<Block> blockList : blockStructure) {
      List<Integer> configList = new ArrayList<>();
      for (Block block : blockList) {
        configList.add(block.getState());
      }
      allBlockConfig.add(configList);
    }
    return allBlockConfig;
  }
}
