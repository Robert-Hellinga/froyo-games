package ooga.Model.CheckerBoard;

import java.util.ArrayList;
import java.util.List;

public class BlockConfigStructure {
  //just for test
  private List<List<Integer>> blockConfigStructure = new ArrayList<>(){{
     add(List.of(1,0,1,0,1,0,1,0));
     add(List.of(0,1,0,1,0,1,0,1));
     add(List.of(1,0,1,0,1,0,1,0));
     add(List.of(0,0,0,0,0,0,0,0));
     add(List.of(0,0,0,0,0,0,0,0));
     add(List.of(2,0,2,0,2,0,2,0));
     add(List.of(0,2,0,2,0,2,0,2));
     add(List.of(2,0,2,0,2,0,2,0));
  }};

//  private List<List<Integer>> blockConfiguration;

  public List<List<Integer>> getBlockConfigStructure() {
    return blockConfigStructure;
  }
}
