package ooga.model.ai;

import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;

public interface AIBrain {

  /**
   * AIBrain would make a list of decisions based on the blockGrid and who is the currentPlayer.
   *
   * @param blockGrid          The blockGrid of the game
   * @param currentPlayerIndex An Integer represents the index of the current player
   * @return return a List of Coordinates represents the AI decision
   */
  List<Coordinate> decideMove(BlockGrid blockGrid, Integer currentPlayerIndex);
}
