package ooga.model.game;

import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.CheckersBlockGrid;
import ooga.model.player.Player;

public class CheckersGame extends Game {

  private CheckersBlockGrid checkersBoard;

  public CheckersGame(String gameType, Player playerOne, Player playerTwo, String startPattern) {
    super(gameType, playerOne, playerTwo, startPattern);
    checkersBoard = new CheckersBlockGrid(gameType,
        getInitiationBlockConfig(gameType, startPattern), numPlayers);
  }

  /**
   * play the game according to the give coordinate
   * @param passInCoordinate the given coordinate
   */

  @Override
  public void play(Coordinate passInCoordinate) {
    checkersBoard.play(passInCoordinate, getCurrentPlayerIndex());
    if (checkersBoard.isFinishARound()) {
      if (checkersBoard.isWinningMove(getCurrentPlayerIndex())) {
        wonGame = true;
      } else {
        updateDatabase();
        playerTakeTurn();
        checkersBoard.resetFinishARound();
      }
    }
    notifyObservers();
  }

  /**
   * get the whole block grid
   * @return the whole block grid
   */

  @Override
  public BlockGrid getBoard() {
    return checkersBoard;
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
   * check whether the current player has potential moves to make
   * @return
   */

  @Override
  public boolean currentPlayerHavePotentialMoves() {
    return !checkersBoard.getAllPotentialMoves(getCurrentPlayerIndex()).isEmpty();
  }
}
