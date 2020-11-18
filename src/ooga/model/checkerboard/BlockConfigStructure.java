package ooga.model.checkerboard;

import java.util.List;

public class BlockConfigStructure {

  private List<List<Integer>> blockConfigStructure;

  public BlockConfigStructure(List<List<Integer>> allConfig) {
    blockConfigStructure = allConfig;
  }

  public List<List<Integer>> getBlockConfigStructure() {
    return blockConfigStructure;
  }

  public int getBlockConfigStructureHeight() {
    return blockConfigStructure.size();
  }

  public int getBlockConfigStructureWidth() {
    return blockConfigStructure.get(0).size();
  }
}
