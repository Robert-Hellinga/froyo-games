package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.CornerRadii;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public class CheckersBlock extends Block {

  public CheckersBlock(Integer blockConfig, Coordinate coordinate) {
    super(blockConfig, coordinate);
  }

  @Override
  public void initiateBlockState(int blockConfig) {
    if (blockConfig == 0) {
      this.isEmpty = true;
    } else {
      this.PlayerID = blockConfig;
    }
  }

  @Override
  public List<Coordinate> getAvailablePosition(int currentPlayerIndex, BlockStructure allBlocks) {
    List<Coordinate> allAvailablePosition = new ArrayList<>();
    if (currentPlayerIndex == 1) {
      if (coordinate.xCoordinate() - 1 >= 0 && coordinate.yCoordinate() + 1 < allBlocks
          .getBlockStructureHeight()) {
        if (allBlocks.getBlock(
            new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate() + 1)
        ).isEmpty) {
          allAvailablePosition.add(
              new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate() + 1)
          );
        }
      }
      if (coordinate.xCoordinate() + 1 < allBlocks.getBlockStructureWidth()
          && coordinate.yCoordinate() + 1 < allBlocks.getBlockStructureHeight()) {
        if (allBlocks.getBlock(
            new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate() + 1)
        ).isEmpty) {
          allAvailablePosition.add(
              new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate() + 1)
          );
        }
      }
    } else if (currentPlayerIndex == 2) {
      if (coordinate.xCoordinate() - 1 >= 0 && coordinate.yCoordinate() - 1 >= 0) {
        if (allBlocks.getBlock(
            new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate() - 1)
        ).isEmpty) {
          allAvailablePosition.add(
              new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate() - 1)
          );
        }
      }
      if (coordinate.xCoordinate() + 1 < allBlocks.getBlockStructureWidth()
          && coordinate.yCoordinate() - 1 >= 0) {
        if (allBlocks.getBlock(
            new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate() - 1)
        ).isEmpty) {
          allAvailablePosition.add(
              new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate() - 1)
          );
        }
      }
    }
    return allAvailablePosition;
  }
}
