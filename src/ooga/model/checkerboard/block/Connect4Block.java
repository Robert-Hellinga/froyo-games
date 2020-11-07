package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public class Connect4Block extends Block{

  public Connect4Block(Integer blockConfig, Coordinate coordinate){
    super(blockConfig, coordinate);
  }

  @Override
  public List<Coordinate> getAvailablePosition(int currentPlayerIndex, BlockStructure allBlocks) {
    List<Coordinate> availablePositions = new ArrayList<>();
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++){
        for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++){
          Coordinate coordinate = new Coordinate(allBlocks.getBlockStructureHeight()-1-j, i);
          if (allBlocks.getBlock(coordinate).getIsEmpty()){
            availablePositions.add(coordinate);
            break;
          }
        }
      }
    return availablePositions;
  }
}
