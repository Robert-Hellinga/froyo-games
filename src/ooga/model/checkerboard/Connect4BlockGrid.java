package ooga.model.checkerboard;

import ooga.Coordinate;

public class Connect4BlockGrid extends BlockGrid{

  public Connect4BlockGrid(String gameType, BlockConfigStructure allBlockConfig, int numPlayers){
    super(gameType, allBlockConfig, numPlayers);
  }

  @Override
  public void setAvailablePosition(int currentPlayerIndex, Coordinate chosenBlock) {

  }

}
