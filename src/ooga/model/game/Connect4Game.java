package ooga.model.game;

import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.Connect4BlockGrid;
import ooga.model.player.Player;

/**
 * This is the Connect4 Game which extends to the abstract class Game
 *
 * @author Yixuan Li, Jincheng He, Nate Mela, Lucas Carter
 */
public class Connect4Game extends Game {

  private final Connect4BlockGrid connect4Board;

  public Connect4Game(String gameType, Player playerOne, Player playerTwo, String startPattern) {
    super(gameType, playerOne, playerTwo, startPattern);
    connect4Board = new Connect4BlockGrid(gameType,
        getInitiationBlockConfig(gameType, startPattern), numPlayers);
  }

  /**
   * @see Game#play(Coordinate)
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
   * @see Game#getBoard()
   */
  @Override
  public BlockGrid getBoard() {
    return connect4Board;
  }

  /**
   * @see Game#getWinningPlayer()
   */
  @Override
  public Player getWinningPlayer() {
    return wonGame ? currentPlayer : null;
  }

  /**
   * @see Game#currentPlayerHavePotentialMoves()
   */
  @Override
  public boolean currentPlayerHavePotentialMoves() {
    return true;
  }
}
