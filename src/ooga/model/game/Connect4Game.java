package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.model.checkerboard.BlockGrid;
import ooga.model.checkerboard.Connect4BlockGrid;

public class Connect4Game extends Game {

  private Connect4BlockGrid checkBoard;

  public Connect4Game(String gameType, String playerName, PlayerMode playerMode){
    super(gameType, playerName, playerMode);
  }

  @Override
  public void play(Coordinate passInCoordinate) {

  }

  @Override
  public BlockGrid getCheckBoard(){
    return checkBoard;
  }
}
