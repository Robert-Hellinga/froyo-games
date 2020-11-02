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
        Coordinate leftBottom = new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate() + 1);
        if (allBlocks.getBlock(leftBottom).getIsEmpty()) {
          allAvailablePosition.add(leftBottom);
        }
        else if (coordinate.xCoordinate() - 2 >= 0 && coordinate.yCoordinate() + 2 < allBlocks
            .getBlockStructureHeight()){
          Coordinate leftStep = new Coordinate(coordinate.xCoordinate() - 2,
              coordinate.yCoordinate() + 2);
          if (allBlocks.getBlock(leftBottom).getPlayerID() == 2 && allBlocks.getBlock(leftStep).getIsEmpty()){
            allAvailablePosition.add(leftStep);
          }
        }
      }
      if (coordinate.xCoordinate() + 1 < allBlocks.getBlockStructureWidth()
          && coordinate.yCoordinate() + 1 < allBlocks.getBlockStructureHeight()) {
        Coordinate rightBottom = new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate() + 1);
        if (allBlocks.getBlock(rightBottom).getIsEmpty()) {
          allAvailablePosition.add(rightBottom);
        }
        else if (coordinate.xCoordinate() + 2 < allBlocks.getBlockStructureWidth() && coordinate.yCoordinate() + 2 < allBlocks
            .getBlockStructureHeight()){
          Coordinate rightStep = new Coordinate(coordinate.xCoordinate() + 2,
              coordinate.yCoordinate() + 2);
          if (allBlocks.getBlock(rightBottom).getPlayerID() == 2 && allBlocks.getBlock(rightStep).getIsEmpty()){
            allAvailablePosition.add(rightStep);
          }
        }
      }
    } else if (currentPlayerIndex == 2) {
      if (coordinate.xCoordinate() - 1 >= 0 && coordinate.yCoordinate() - 1 >= 0) {
        Coordinate leftTop = new Coordinate(coordinate.xCoordinate() - 1, coordinate.yCoordinate() - 1);
        if (allBlocks.getBlock(leftTop).getIsEmpty()) {
          allAvailablePosition.add(leftTop);
        }
        else if (coordinate.xCoordinate() - 2 >= 0 && coordinate.yCoordinate() - 2 >= 0){
          Coordinate leftStep = new Coordinate(coordinate.xCoordinate() - 2,
              coordinate.yCoordinate() - 2);
          if (allBlocks.getBlock(leftTop).getPlayerID() == 1 && allBlocks.getBlock(leftStep).getIsEmpty()){
            allAvailablePosition.add(leftStep);
          }
        }
      }
      if (coordinate.xCoordinate() + 1 < allBlocks.getBlockStructureWidth()
          && coordinate.yCoordinate() - 1 >= 0) {
        Coordinate rightTop = new Coordinate(coordinate.xCoordinate() + 1, coordinate.yCoordinate() - 1);
        if (allBlocks.getBlock(rightTop).getIsEmpty()) {
          allAvailablePosition.add(rightTop);
        }
        else if (coordinate.xCoordinate() + 2 < allBlocks.getBlockStructureWidth()
            && coordinate.yCoordinate() - 2 >= 0){
          Coordinate rightStep = new Coordinate(coordinate.xCoordinate() + 2,
              coordinate.yCoordinate() - 2);
          if (allBlocks.getBlock(rightTop).getPlayerID() == 1 && allBlocks.getBlock(rightStep).getIsEmpty()){
            allAvailablePosition.add(rightStep);
          }
        }
      }
    }
    return allAvailablePosition;
  }
}
