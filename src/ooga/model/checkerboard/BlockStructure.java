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

  public List<List<Block>> getBlockStructure() {
    return blockStructure;
  }

  public void setBlockStructure(List<List<Block>> blockStructure) {
    this.blockStructure = blockStructure;
  }

  public Block getBlock(Coordinate blockCoordinate) {
    return blockStructure.get(blockCoordinate.yCoordinate()).get(blockCoordinate.xCoordinate());
  }

  public void setBlock(Block block){
    blockStructure.get(block.getCoordinate().yCoordinate()).add(block.getCoordinate().xCoordinate(), block);
  }

  public int getBlockStructureHeight() {
    return blockStructure.size();
  }

  public int getBlockStructureWidth() {
    return blockStructure.get(0).size();
  }

  public List<List<Integer>> getBlockConfigStructure() {
    List<List<Integer>> allBlockConfig = new ArrayList<>();
    for (List<Block> blockList : blockStructure) {
      List<Integer> configList = new ArrayList<>();
      for (Block block : blockList) {
        if (block.getIsEmpty()) {
          configList.add(0);
        } else {
          configList.add(block.getPlayerID());
        }
      }
      allBlockConfig.add(configList);
    }
    return allBlockConfig;
  }
}
