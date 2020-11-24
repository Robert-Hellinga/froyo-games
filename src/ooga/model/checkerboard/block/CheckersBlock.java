package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

/**
 * This class extends the abstract class Block, it is the block for checkers game
 *
 * @author Yixuan Li, Jincheng He, Robert Hellinga
 * @see ooga.model.checkerboard.block.Block
 */
public class CheckersBlock extends Block {


  private final int SELECTED = 2;
  private final int KING = 4;
  private final int POTENTIAL_MOVE = 9;

  protected boolean isKing;

  /**
   * The constructor
   *
   * @param blockConfig The input integer which represents the config of the block
   * @param coordinate  The Coordinate parameter which represents the coordinate of the block
   */
  public CheckersBlock(Integer blockConfig, Coordinate coordinate) {
    super(blockConfig, coordinate);
  }

  /**
   * This is the initialization of the block state
   *
   * @param blockConfig The integer which represents the state config of the block state
   * @see Block#initiateBlockState(int)
   */
  @Override
  public void initiateBlockState(int blockConfig) {
    super.initiateBlockState(blockConfig);
    this.isChosen = false;
    this.isPotentialMove = (blockConfig == POTENTIAL_MOVE);
    this.isKing = (blockConfig == KING + playerID);
  }

  /**
   * This is a copy constructor
   *
   * @param newBlock The block to be copied
   */
  public CheckersBlock(Block newBlock) {
    this(newBlock.getState(), newBlock.getCoordinate());
  }


  /**
   * This method is to get the available positions given by the known current player and the whole
   * block structure
   *
   * @param currentPlayerIndex The integer which represents the index of the current player
   * @param allBlocks          The BlockStructure which represents the whole blocks
   * @return return a List of Coordinate which represents those positions which are available
   */
  @Override
  public List<Coordinate> getAvailablePositions(int currentPlayerIndex, BlockStructure allBlocks) {
    List<Coordinate> allAvailablePosition = new ArrayList<>();
    if (currentPlayerIndex == 1) {
      allAvailablePosition.addAll(getPotentialNeighbourMove(currentPlayerIndex, allBlocks, true));
      allAvailablePosition.addAll(getPotentialStepMove(currentPlayerIndex, allBlocks, true));
    } else if (currentPlayerIndex == 2) {
      allAvailablePosition.addAll(getPotentialNeighbourMove(currentPlayerIndex, allBlocks, false));
      allAvailablePosition.addAll(getPotentialStepMove(currentPlayerIndex, allBlocks, false));
    }
    List<Coordinate> furtherAvailablePosition = new ArrayList<>();
    for (Coordinate coordinate : allAvailablePosition) {
      if (Math.abs(coordinate.xCoordinate() - this.coordinate.xCoordinate()) == 2
          && Math.abs(coordinate.yCoordinate() - this.coordinate.yCoordinate()) == 2) {
        List<Coordinate> additionalAvailablePosition = allBlocks.getBlock(coordinate)
            .getAvailablePositions(currentPlayerIndex, allBlocks);
        for (Coordinate additionalMove : additionalAvailablePosition) {
          if (Math.abs(additionalMove.xCoordinate() - coordinate.xCoordinate()) == 2
              && Math.abs(additionalMove.yCoordinate() - coordinate.yCoordinate()) == 2) {
            furtherAvailablePosition.add(additionalMove);
          }
        }
      }
    }
    allAvailablePosition.addAll(furtherAvailablePosition);
    return allAvailablePosition;
  }

  /**
   * This is the helper method which can get the potential neighbour move
   *
   * @param currentPlayerIndex The integer which represents the index of the current player
   * @param allBlocks          The BlockStructure which represents the whole blocks
   * @param isDownDirection    A boolean which is true if it is down direction and false if it is
   *                           the up direction
   * @return return a List of Coordinate which represents those positions which are potential
   * neighbour moves
   */
  private List<Coordinate> getPotentialNeighbourMove(int currentPlayerIndex,
      BlockStructure allBlocks, boolean isDownDirection) {

    List<Coordinate> availableMoves = new ArrayList<>();
    for (Coordinate coor : getNeighbourMove(allBlocks, isDownDirection)) {
      if (allBlocks.getBlock(coor).isEmpty) {
        availableMoves.add(coor);
      }
    }
    if (this.isKing) {
      for (Coordinate coor : getNeighbourMove(allBlocks, !isDownDirection)) {
        if (allBlocks.getBlock(coor).isEmpty) {
          availableMoves.add(coor);
        }
      }
    }
    return availableMoves;
  }

  /**
   * This is the helper method which can get the potential step move
   *
   * @param currentPlayerIndex The integer which represents the index of the current player
   * @param allBlocks          The BlockStructure which represents the whole blocks
   * @param isDownDirection    A boolean which is true if it is down direction and false if it is
   *                           the up direction
   * @return return a List of Coordinate which represents those positions which are potential step
   * moves
   */
  private List<Coordinate> getPotentialStepMove(int currentPlayerIndex, BlockStructure allBlocks,
      boolean isDownDirection) {
    List<Coordinate> tmpNeighbourMoves = new ArrayList<>();
    List<Coordinate> availableMoves = new ArrayList<>();
    for (Coordinate coor : getNeighbourMove(allBlocks, isDownDirection)) {
      if (!allBlocks.getBlock(coor).isEmpty
          && allBlocks.getBlock(coor).getPlayerID() != currentPlayerIndex) {
        tmpNeighbourMoves.add(coor);
      }
    }
    if (this.isKing) {
      for (Coordinate coor : getNeighbourMove(allBlocks, !isDownDirection)) {
        if (!allBlocks.getBlock(coor).isEmpty
            && allBlocks.getBlock(coor).getPlayerID() != currentPlayerIndex) {
          tmpNeighbourMoves.add(coor);
        }
      }
    }
    for (Coordinate coor : tmpNeighbourMoves) {
      Coordinate stepCoordinate = new Coordinate(coor.xCoordinate() * 2 - coordinate.xCoordinate(),
          coor.yCoordinate() * 2 - coordinate.yCoordinate());
      if (checkInGrid(stepCoordinate, allBlocks) && allBlocks
          .getBlock(stepCoordinate).isEmpty) {
        availableMoves.add(stepCoordinate);
      }
    }
    return availableMoves;
  }

  /**
   * This method is to get the neighbour move no matter it is potential or not
   *
   * @param allBlocks       The BlockStructure which represents the whole blocks
   * @param isDownDirection A boolean which is true if it is down direction and false if it is the
   *                        up direction
   * @return return a List of Coordinate which represents those positions which are neighbour moves
   */
  private List<Coordinate> getNeighbourMove(BlockStructure allBlocks, boolean isDownDirection) {
    List<Coordinate> tmpAvailableMoves = new ArrayList<>();
    List<Coordinate> availableMoves = new ArrayList<>();
    int indicator;
    if (isDownDirection) {
      indicator = 1;
    } else {
      indicator = -1;
    }
    tmpAvailableMoves
        .add(
            new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate() + indicator * 1));
    tmpAvailableMoves
        .add(
            new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate() + indicator * 1));
    for (Coordinate coor : tmpAvailableMoves) {
      if (checkInGrid(coor, allBlocks)) {
        availableMoves.add(coor);
      }
    }
    return availableMoves;
  }

  /**
   * This method is to check whether the input coordinate is inside the grid
   *
   * @param coor      The input coordinate
   * @param allBlocks The BlockStructure which represents the whole blocks
   * @return return true if it is inside the grid and false if it is not
   */
  private boolean checkInGrid(Coordinate coor, BlockStructure allBlocks) {
    return coor.xCoordinate() >= 0 && coor.xCoordinate() < allBlocks.getBlockStructureWidth()
        && coor.yCoordinate() >= 0 && coor.yCoordinate() < allBlocks.getBlockStructureHeight();
  }

  /**
   * @see Block#setEmpty()
   */
  @Override
  public void setEmpty() {
    super.setEmpty();
    isKing = false;
  }

  /**
   * @see Block#makePotentialMove()
   */
  @Override
  public void makePotentialMove() {
    super.makePotentialMove();
    state = POTENTIAL_MOVE;
  }

  /**
   * This method is to check whether this block is king
   *
   * @return return true if it is king and false if it is not
   */
  public boolean isKing() {
    return isKing;
  }

  /**
   * This method is to make the block to be king
   */
  public void makeKing() {
    isKing = true;
    state = KING + playerID;
  }
}
