package ooga.model.checkerboard.rules;

import ooga.model.checkerboard.BlockGrid;
import ooga.model.player.Move;

public class CheckersRule extends Rule {

  private BlockGrid checkerGrid;

  public CheckersRule(BlockGrid checkerGrid) {
    this.checkerGrid = checkerGrid;
  }

  @Override
  public boolean checkValidMove(Move oldMove, Move newMove) {
    return false;
  }
}
