package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

/**
 * @author Yixuan Li, Robert Hellinga
 */
public class OthelloBlock extends Block {

  private final int POTENTIAL_MOVE = 3;


  /**
   * This is the constructor
   *
   * @param blockConfig The input integer which represents the config of the block
   * @param coordinate  The Coordinate parameter which represents the coordinate of the block
   */
  public OthelloBlock(Integer blockConfig, Coordinate coordinate) {
    super(blockConfig, coordinate);
  }

  /**
   * @see Block#initiateBlockState(int)
   */
  @Override
  public void initiateBlockState(int blockConfig) {
    this.state = blockConfig;
    setPlayerID(blockConfig);
    this.isEmpty = blockConfig == 0;
    this.isChosen = false;
  }

  /**
   * @see Block#getAvailablePositions(int, BlockStructure)
   */
  @Override
  public List<Coordinate> getAvailablePositions(int currentPlayerIndex, BlockStructure allBlocks) {
    List<Coordinate> availablePositions = new ArrayList<>();
    if (this.getPlayerID() == currentPlayerIndex) {
      for (Coordinate neighbors : getValidNeighbor(allBlocks, coordinate)) {
        if (!allBlocks.getBlock(neighbors).isEmpty
            && allBlocks.getBlock(neighbors).getPlayerID() != currentPlayerIndex) {
          int xIncrement = neighbors.xCoordinate() - coordinate.xCoordinate();
          int yIncrement = neighbors.yCoordinate() - coordinate.yCoordinate();
          Coordinate extendedNeighbor = new Coordinate(neighbors.xCoordinate() + xIncrement,
              neighbors.yCoordinate() + yIncrement);
          while (true) {
            if (extendedNeighbor.xCoordinate() < 0 || extendedNeighbor.yCoordinate() < 0
                || extendedNeighbor.xCoordinate() >= allBlocks
                .getBlockStructureWidth() || extendedNeighbor.yCoordinate() >= allBlocks
                .getBlockStructureHeight()) {
              break;
            }
            if (allBlocks.getBlock(extendedNeighbor).isEmpty || allBlocks
                .getBlock(extendedNeighbor).isPotentialMove) {
              availablePositions.add(extendedNeighbor);
              break;
            } else if (allBlocks.getBlock(extendedNeighbor).getPlayerID()
                == currentPlayerIndex) {
              break;
            }
            extendedNeighbor = new Coordinate(extendedNeighbor.xCoordinate() + xIncrement,
                extendedNeighbor.yCoordinate() + yIncrement);

          }
        }
      }
    }
    return availablePositions;
  }


  /**
   * This method is to get the valid neighbor
   *
   * @param allBlocks  The BlockStructure which represents the whole blocks
   * @param coordinate The input coordinate
   * @return return a List of Coordinate which represents those positions which are the valid
   * neighbor
   */
  public static List<Coordinate> getValidNeighbor(BlockStructure allBlocks, Coordinate coordinate) {
    List<Coordinate> validNeighbors = new ArrayList<>();
    if (coordinate.xCoordinate() - 1 >= 0) {
      validNeighbors.add(new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate()));
      if (coordinate.yCoordinate() - 1 >= 0) {
        validNeighbors
            .add(new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate() - 1));
        validNeighbors.add(new Coordinate(coordinate.xCoordinate(), coordinate.yCoordinate() - 1));
      }
      if (coordinate.yCoordinate() + 1 < allBlocks.getBlockStructureHeight()) {
        validNeighbors
            .add(new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate() + 1));
        validNeighbors.add(new Coordinate(coordinate.xCoordinate(), coordinate.yCoordinate() + 1));
      }
    }
    if (coordinate.xCoordinate() + 1 < allBlocks.getBlockStructureWidth()) {
      validNeighbors.add(new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate()));
      if (coordinate.yCoordinate() - 1 >= 0) {
        validNeighbors
            .add(new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate() - 1));
      }
      if (coordinate.yCoordinate() + 1 < allBlocks.getBlockStructureHeight()) {
        validNeighbors
            .add(new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate() + 1));
      }
    }
    return validNeighbors;
  }

  /**
   * @see Block#setPlayerID(int)
   */
  @Override
  public void setPlayerID(int player) {
    if (player == POTENTIAL_MOVE) {
      isPotentialMove = true;
    } else {
      super.setPlayerID(player);
      isPotentialMove = false;
      state = playerID;
    }

  }

  /**
   * @see Block#makePotentialMove()
   */
  @Override
  public void makePotentialMove() {
    super.makePotentialMove();
    state = POTENTIAL_MOVE;
  }
}
