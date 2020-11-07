package ooga.model.checkerboard;

import java.util.ArrayList;
import java.util.List;

public class BlockConfigStructure {

  //just for test of checkers
  private List<List<Integer>> blockConfigStructure = new ArrayList<>() {{
    add(List.of(1, 0, 1, 0, 1, 0, 1, 0));
    add(List.of(0, 1, 0, 1, 0, 1, 0, 1));
    add(List.of(1, 0, 1, 0, 1, 0, 1, 0));
    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
    add(List.of(0, 2, 0, 2, 0, 2, 0, 2));
    add(List.of(2, 0, 2, 0, 2, 0, 2, 0));
    add(List.of(0, 2, 0, 2, 0, 2, 0, 2));
  }};

  //just for test of connect4
//  private List<List<Integer>> blockConfigStructure = new ArrayList<>() {{
//    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
//    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
//    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
//    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
//    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
//    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
//    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
//    add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
//  }};

//  private List<List<Integer>> blockConfiguration;

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
