package ooga.Model.Player.AIPlayer;

import ooga.Model.Player.Move;
import ooga.Model.Player.Player;

public abstract class AIPlayer extends Player {

  protected abstract Move getAIMove();

  public abstract void move(Move move);
}
