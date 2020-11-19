package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public class OthelloBlock extends Block {

  public OthelloBlock(Integer blockConfig, Coordinate coordinate) {
    super(blockConfig, coordinate);
  }

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
            if (allBlocks.getBlock(extendedNeighbor).isEmpty) {
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
}
