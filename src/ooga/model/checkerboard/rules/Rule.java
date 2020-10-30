package ooga.model.checkerboard.rules;

import ooga.model.player.Move;

public abstract class Rule {

  public abstract boolean checkValidMove(Move oldMove, Move newMove);

}
