package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

public class Connect4Block extends Block {

  public Connect4Block(Integer blockConfig, Coordinate coordinate) {
    super(blockConfig, coordinate);
  }

  @Override
  public List<Coordinate> getAvailablePositions(int currentPlayerIndex, BlockStructure allBlocks) {
    List<Coordinate> availablePositions = new ArrayList<>();

    for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
      for (int j = allBlocks.getBlockStructureHeight() - 1; j >= 0; j--) {
        Coordinate coor = new Coordinate(i, j);
        if (allBlocks.getBlock(coor).getIsEmpty()) {
          availablePositions.add(coor);
          break;
        }
      }
    }
    return availablePositions;
  }

  @Override
  public void setPlayerID(int player){
    super.setPlayerID(player);
    state = playerID;
  }

}
