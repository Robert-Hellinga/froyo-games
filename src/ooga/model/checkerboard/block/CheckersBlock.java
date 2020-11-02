package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public class CheckersBlock extends Block {


  public CheckersBlock(Integer blockConfig, Coordinate coordinate) {
    super(blockConfig, coordinate);
  }


  @Override
  public List<Coordinate> getAvailablePosition(int currentPlayerIndex, BlockStructure allBlocks) {
    List<Coordinate> allAvailablePosition = new ArrayList<>();
    if (currentPlayerIndex == 1) {
      allAvailablePosition.addAll(getPotentialNeighbourMove(currentPlayerIndex, allBlocks, true));
      allAvailablePosition.addAll(getPotentialStepMove(currentPlayerIndex, allBlocks, true));
    } else if (currentPlayerIndex == 2) {
      allAvailablePosition.addAll(getPotentialNeighbourMove(currentPlayerIndex, allBlocks, false));
      allAvailablePosition.addAll(getPotentialStepMove(currentPlayerIndex, allBlocks, false));
    }
    return allAvailablePosition;
  }

  private List<Coordinate> getPotentialNeighbourMove(int currentPlayerIndex,
      BlockStructure allBlocks, boolean isDownDirection) {

    List<Coordinate> availableMoves = new ArrayList<>();
    for (Coordinate coor : getNeighbourMove(allBlocks, isDownDirection)) {
      if (allBlocks.getBlock(coor).blockState.isEmpty) {
        availableMoves.add(coor);
      }
    }
    return availableMoves;
  }

  private List<Coordinate> getPotentialStepMove(int currentPlayerIndex, BlockStructure allBlocks,
      boolean isDownDirection) {
    List<Coordinate> tmpNeighbourMoves = new ArrayList<>();
    List<Coordinate> availableMoves = new ArrayList<>();
    for (Coordinate coor : getNeighbourMove(allBlocks, isDownDirection)) {
      if (!allBlocks.getBlock(coor).blockState.isEmpty
          && allBlocks.getBlock(coor).getPlayerID() != currentPlayerIndex) {
        tmpNeighbourMoves.add(coor);
      }
    }
    for (Coordinate coor : tmpNeighbourMoves) {
      Coordinate stepCoordinate = new Coordinate(coor.xCoordinate() * 2 - coordinate.xCoordinate(),
          coor.yCoordinate() * 2 - coordinate.yCoordinate());
      if (checkInGrid(stepCoordinate, allBlocks) && allBlocks
          .getBlock(stepCoordinate).blockState.isEmpty) {
        availableMoves.add(stepCoordinate);
      }
    }
    return availableMoves;
  }

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

  private boolean checkInGrid(Coordinate coor, BlockStructure allBlocks) {
    return coor.xCoordinate() >= 0 && coor.xCoordinate() < allBlocks.getBlockStructureWidth()
        && coor.yCoordinate() >= 0 && coor.yCoordinate() < allBlocks.getBlockStructureHeight();
  }
}
