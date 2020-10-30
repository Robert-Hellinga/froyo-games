package ooga.model.player.aiplayer;

import ooga.model.player.Move;
import ooga.model.player.Player;

public abstract class AIPlayer extends Player {

  public AIPlayer(String name) {
    super(name);
  }

  protected abstract Move getAIMove();

  public abstract void move(Move move);
}
