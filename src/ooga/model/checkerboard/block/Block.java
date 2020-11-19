package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public abstract class Block {

  private final int EMPTY = 0;
  private final int PLAYER_1 = 1;
  private final int PLAYER_2 = 2;

  protected int state;
  protected boolean isEmpty;
  protected int playerID;
  protected boolean isChosen;
  protected boolean isPotentialMove;

  protected Coordinate coordinate;

  public Block(Integer blockConfig, Coordinate coordinate) {
    initiateBlockState(blockConfig);
    this.coordinate = coordinate;
  }

  public Block(Block newBlock){
    this(newBlock.getState(), newBlock.getCoordinate());
  }

  public void initiateBlockState(int blockConfig) {
    this.state = blockConfig;
    this.playerID = (blockConfig % 2);
    this.isEmpty = blockConfig == 0;
    this.isChosen = false;
    this.isPotentialMove = false;
  }

  public abstract List<Coordinate> getAvailablePositions(int currentPlayerIndex,
                                                         BlockStructure allBlocks);

  public boolean getIsEmpty() {
    return isEmpty;
  }

  public int getPlayerID() {
    return playerID;
  }

  public void setPlayerID(int player) {
    isEmpty = false;
    this.playerID = (player % 2);
  }

  public void setEmpty(){
    this.playerID = EMPTY;
    this.isEmpty = true;
    this.state = EMPTY;
  }

  public void makePotentialMove() {
    isPotentialMove = true;
  }

  public void unmakePotentialMove() {isPotentialMove = false;}

  public boolean isPotentialMove() {
    return isPotentialMove;
  }

  public void choose() {
    isChosen = true;
  }

  public void unchoose(){
    isChosen = false;
  }

  public int getState(){return state;}

  public boolean isChosen() {
    return isChosen;
  }

  public Coordinate getCoordinate(){return coordinate;}


}
