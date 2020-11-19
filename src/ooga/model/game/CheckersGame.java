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

  @Override
  public BlockGrid getBoard() {
    return checkersBoard;
  }

  @Override
  public Player getWinningPlayer() {
    if (wonGame) {
      return currentPlayer;
    }
    return null;
  }

  @Override
  public boolean currentPlayerHavePotentialMoves() {
    return !checkersBoard.getAllPotentialMoves(getCurrentPlayerIndex()).isEmpty();
  }
}
