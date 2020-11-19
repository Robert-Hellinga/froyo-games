package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public class CheckersBlock extends Block {


  private final int SELECTED = 2;
  private final int KING = 4;
  private final int POTENTIAL_MOVE = 9;

  protected boolean isKing;

  public CheckersBlock(Integer blockConfig, Coordinate coordinate) {

    super(blockConfig, coordinate);
    this.isChosen = false;
    this.isPotentialMove = false;
    this.isKing = false;
    ;
  }

  public CheckersBlock(Block newBlock) {
    super(newBlock.getState(), newBlock.getCoordinate());
    this.isEmpty = newBlock.getIsEmpty();
    this.playerID = newBlock.getPlayerID();
  }


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

  @Override
  public void setEmpty() {
    super.setEmpty();
    isKing = false;
  }

  @Override
  public void makePotentialMove() {
    super.makePotentialMove();
    state = POTENTIAL_MOVE;
  }

  public boolean isKing() {
    return isKing;
  }

  public void makeKing() {
    isKing = true;
    state = state + KING;
  }


}
