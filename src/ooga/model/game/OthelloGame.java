package ooga.model.game;

import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.OthelloBlockGrid;
import ooga.model.player.Player;

public class OthelloGame extends Game {

  private OthelloBlockGrid othelloBoard;

  public OthelloGame(String gameType, Player playerOne, Player playerTwo, String startPattern) {
    super(gameType, playerOne, playerTwo, startPattern);
    othelloBoard = new OthelloBlockGrid(gameType, getInitiationBlockConfig(gameType, startPattern),
        numPlayers);
  }

  @Override
  public void play(Coordinate passInCoordinate) {
    if (othelloBoard.getAllBlocks().getBlock(passInCoordinate).isPotentialMove()) {
      othelloBoard.play(passInCoordinate, getCurrentPlayerIndex());
      if (othelloBoard.isFinishARound()) {
        if (othelloBoard.isWinningMove(getCurrentPlayerIndex())) {
          wonGame = true;
        } else {
          updateDatabase();
          playerTakeTurn();
//          if (!currentPlayerHavePotentialMoves()){
//            haveNoPotentialMove = true;
//          }
          othelloBoard.setAvailablePositions(getCurrentPlayerIndex(), Coordinate.INVALID_COORDINATE);
          othelloBoard.resetFinishARound();
        }
      }
    }
    notifyObservers();
  }

  @Override
  public boolean currentPlayerHavePotentialMoves() {
    return !othelloBoard.getAllPotentialMoves(getCurrentPlayerIndex()).isEmpty();
  }

  @Override
  public BlockGrid getBoard() {
    return othelloBoard;
  }

  @Override
  public Player getWinningPlayer() {
    if (wonGame) {
      return allPlayers.get(othelloBoard.getWinningPlayerIndex());
    }
    return null;
  }
}
