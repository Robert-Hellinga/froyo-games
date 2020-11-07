package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;

public class Connect4Game extends Game {

  public Connect4Game(String gameType, String playerName, PlayerMode playerMode){
    super(gameType, playerName, playerMode);
  }

  @Override
  public void play(Coordinate passInCoordinate) {

  }
}
