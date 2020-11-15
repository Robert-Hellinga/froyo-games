package ooga.model.game;

import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.Connect4BlockGrid;
import ooga.model.player.Player;

public class Connect4Game extends Game {

  private Connect4BlockGrid connect4Board;

  public Connect4Game(String gameType, Player playerOne, Player playerTwo, String startPattern) {
    super(gameType, playerOne, playerTwo, startPattern);
    connect4Board = new Connect4BlockGrid(gameType, getInitiationBlockConfig(gameType, startPattern), numPlayers);
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
