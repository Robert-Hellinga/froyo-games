package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.Connect4BlockGrid;

public class Connect4Game extends Game {

  private Connect4BlockGrid connect4Board;

  public Connect4Game(String gameType, String playerName, PlayerMode playerMode) {
    super(gameType, playerName, playerMode);
    connect4Board = new Connect4BlockGrid(gameType, getInitiationBlockConfig(gameType), numPlayers);
  }

  @Override
  public void play(Coordinate passInCoordinate) {
    connect4Board.play(passInCoordinate, getCurrentPlayerIndex());
    if (connect4Board.isFinishARound()){
      playerTakeTurn();
      connect4Board.resetFinishAround();
    }
    notifyObservers();
  }

  @Override
  public BlockGrid getBoard() {
    return connect4Board;
  }
}
