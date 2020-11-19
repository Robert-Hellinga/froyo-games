package ooga.model.checkerboard.block;

import java.util.List;
import ooga.Coordinate;
import ooga.exceptions.FileException;
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

  public Block(Block newBlock) {
    this(newBlock.getState(), newBlock.getCoordinate());
  }

  public void initiateBlockState(int blockConfig) {
    this.state = blockConfig;
    setPlayerID(blockConfig);
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
    if (player == 0) {
      playerID = 0;
    } else if (player % 2 == 1) {
      playerID = 1;
    } else if (player % 2 == 0) {
      playerID = 2;
    } else {
      throw new FileException("Invalid block state");
    }
  }

  public void setEmpty() {
    this.playerID = EMPTY;
    this.isEmpty = true;
    this.state = EMPTY;
    this.isPotentialMove = false;
    this.isChosen = false;
  }

  public void makePotentialMove() {
    isPotentialMove = true;
  }

  public void unmakePotentialMove() {
    isPotentialMove = false;
    setEmpty();
  }

  public boolean isPotentialMove() {
    return isPotentialMove;
  }

  public void choose() {
    isChosen = true;
  }

  public void unchoose() {
    isChosen = false;
  }

  public int getState() {
    return state;
  }

  public boolean isChosen() {
    return isChosen;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }


}
