package ooga.model.game;

import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.Connect4BlockGrid;
import ooga.model.player.Player;

public class Connect4Game extends Game {

  private final Connect4BlockGrid connect4Board;

  public Connect4Game(String gameType, Player playerOne, Player playerTwo, String startPattern) {
    super(gameType, playerOne, playerTwo, startPattern);
    connect4Board = new Connect4BlockGrid(gameType,
        getInitiationBlockConfig(gameType, startPattern), numPlayers);
  }

  /**
   * play the game
   * @param passInCoordinate the coordinate to be played on
   */

  @Override
  public void play(Coordinate passInCoordinate) {
    connect4Board.play(passInCoordinate, getCurrentPlayerIndex());
    if (connect4Board.isFinishARound()) {
      if (connect4Board.isWinningMove(getCurrentPlayerIndex())) {
        wonGame = true;
      } else {
        updateDatabase();
        playerTakeTurn();
        connect4Board.resetFinishARound();
      }
    }
    notifyObservers();
  }

  /**
   * get the board
   * @return get the whole board
   */

  @Override
  public BlockGrid getBoard() {
    return connect4Board;
  }

  /**
   * get the winning player
   * @return the winning player
   */

  @Override
  public Player getWinningPlayer() {
    return wonGame ? currentPlayer : null;
  }

  /**
   * get whether the current player have potential moves
   * @return whether the current player have potential moves
   */

  @Override
  public boolean currentPlayerHavePotentialMoves() {
    return true;
  }
}
