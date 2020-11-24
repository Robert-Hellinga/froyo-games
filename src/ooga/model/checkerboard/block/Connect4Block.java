package ooga.model.checkerboard.block;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;

/**
 * This class extends the abstract class Block, it is the block for Connect4 game
 *
 * @author Yixuan Li, Jincheng He, Robert Hellinga
 */
public class Connect4Block extends Block {

  /**
   * This is the constructor
   *
   * @param blockConfig The input integer which represents the config of the block
   * @param coordinate  The Coordinate parameter which represents the coordinate of the block
   */
  public Connect4Block(Integer blockConfig, Coordinate coordinate) {
    super(blockConfig, coordinate);
  }

  /**
   * @see Block#getAvailablePositions(int, BlockStructure)
   */
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

  /**
   * @see Block#setPlayerID(int)
   */
  @Override
  public void setPlayerID(int player) {
    super.setPlayerID(player);
    state = playerID;
  }
}
