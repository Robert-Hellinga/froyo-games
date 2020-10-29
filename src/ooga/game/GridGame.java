package ooga.game;

import java.util.ArrayList;
import java.util.Collection;

public class GridGame {
  public enum BlockState {
    CHECKER_PLAYER_1,
    CHECKER_PLAYER_2,
    EMPTY,
  }
  public final static int SIZE = 8;

  private BlockStructure allBlocks;
  private String gameType;

  public GridGame(String gameType){
    this.gameType = gameType;
  }

  private void createAllBlock(){
    Collection<Collection<Block>> initialAllBlocks = new ArrayList<>();
    for (int i = 0; i < SIZE; i++){
      for (int j = 0; j < SIZE; j++){

      }
    }
  }
}
