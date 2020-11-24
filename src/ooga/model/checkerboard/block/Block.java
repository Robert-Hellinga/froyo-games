package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ooga.Coordinate;
import ooga.exceptions.FileException;
import ooga.model.checkerboard.BlockStructure;

/**
 * This is the abstract Block class, which is extended by CheckersBlock, Connect4Block,
 * OthelloBlock
 *
 * @author Yixuan Li, Jincheng He, Robert Hellinga
 */
public abstract class Block {

  protected final int EMPTY = 0;
  private final int PLAYER_1 = 1;
  private final int PLAYER_2 = 2;

  protected int state;
  protected boolean isEmpty;
  protected int playerID;
  protected boolean isChosen;
  protected boolean isPotentialMove;

  protected Coordinate coordinate;

  /**
   * The constructor
   *
   * @param blockConfig The integer parameter which represents the blockConfig
   * @param coordinate  The Coordinate parameter which represents the coordinate of the block
   */
  public Block(Integer blockConfig, Coordinate coordinate) {
    initiateBlockState(blockConfig);
    this.coordinate = coordinate;
  }

  /**
   * This is a copy constructor
   *
   * @param newBlock The block to be copied
   */
  public Block(Block newBlock) {
    this(newBlock.getState(), newBlock.getCoordinate());
  }

  /**
   * This is the initialization of the block state
   *
   * @param blockConfig The integer which represents the state config of the block state
   */
  public void initiateBlockState(int blockConfig) {
    this.state = blockConfig;
    setPlayerID(blockConfig);
    this.isEmpty = blockConfig == 0;
    this.isChosen = false;
  }

  /**
   * This method is to get the available positions given by the known current player and the whole
   * block structure
   *
   * @param currentPlayerIndex The integer which represents the index of the current player
   * @param allBlocks          The BlockStructure which represents the whole blocks
   * @return return a List of Coordinate which represents those positions which are available
   */
  public abstract List<Coordinate> getAvailablePositions(int currentPlayerIndex,
      BlockStructure allBlocks);

  /**
   * This method is to check whether this block is empty
   *
   * @return return true is the block is empty and false the vice versa
   */
  public boolean getIsEmpty() {
    return isEmpty;
  }

  /**
   * This method is to get the player ID of this block
   *
   * @return return the integer which represents the player ID
   */
  public int getPlayerID() {
    return playerID;
  }

  /**
   * This method is to set the input integer parameter to be the player ID of this block
   *
   * @param player The integer which represents the player ID
   */
  public void setPlayerID(int player) {
    isEmpty = false;
    if (player == EMPTY) {
      playerID = 0;
    } else if (player % 2 == 1) {
      playerID = 1;
    } else if (player % 2 == 0) {
      playerID = 2;
    } else {
      throw new FileException("Invalid block state");
    }
  }

  /**
   * This method is to set the block to be empty
   */
  public void setEmpty() {
    this.playerID = EMPTY;
    this.isEmpty = true;
    this.state = EMPTY;
    this.isPotentialMove = false;
    this.isChosen = false;
  }

  /**
   * This method is to set the block to be the potential block which can be as a move
   */
  public void makePotentialMove() {
    isPotentialMove = true;
  }

  /**
   * This method is to set the block not to be the potential block which can be as a move
   */
  public void unmakePotentialMove() {
    isPotentialMove = false;
    setEmpty();
  }

  /**
   * This method is to check whether the block is the potential which can be as a move
   *
   * @return return true if the block is potential for move and false if it is not
   */
  public boolean isPotentialMove() {
    return isPotentialMove;
  }

  /**
   * This method is to set the block to be set chosen
   */
  public void choose() {
    isChosen = true;
  }

  /**
   * This method is to set the block not to be set chosen
   */
  public void unchoose() {
    isChosen = false;
  }

  /**
   * This method is to get the state of the block
   *
   * @return return the state of the block
   */
  public int getState() {
    return state;
  }

  /**
   * This method is to check whether the block is chosen
   *
   * @return return true if the block is chosen and false if it is not
   */
  public boolean isChosen() {
    return isChosen;
  }

  /**
   * This method is to get the coordinate of the block
   *
   * @return return the coordinate of the block
   */
  public Coordinate getCoordinate() {
    return coordinate;
  }


}
