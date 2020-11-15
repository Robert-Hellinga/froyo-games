package ooga.model.game;

import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.CheckersBlockGrid;
import ooga.model.player.Player;

public class CheckersGame extends Game {

  private CheckersBlockGrid checkersBoard;

  public CheckersGame(String gameType, Player playerOne, Player playerTwo, String startPattern){
    super(gameType, playerOne, playerTwo, startPattern);
    checkersBoard = new CheckersBlockGrid(gameType, getInitiationBlockConfig(gameType, startPattern), numPlayers);
  }

  @Override
  public void play(Coordinate passInCoordinate) {
    checkersBoard.play(passInCoordinate, getCurrentPlayerIndex());
    if (checkersBoard.isFinishARound()){
      playerTakeTurn();
      checkersBoard.resetFinishAround();
    }
    notifyObservers();
  }

  @Override
  public BlockGrid getBoard(){
    return checkersBoard;
  }
}
