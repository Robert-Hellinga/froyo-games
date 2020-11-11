package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.model.BlockGrid;
import ooga.model.CheckersBlockGrid;

public class CheckersGame extends Game {

  private CheckersBlockGrid checkBoard;

  public CheckersGame(String gameType, String playerName, PlayerMode playerMode, String startPattern){
    super(gameType, playerName, playerMode, startPattern);
    checkBoard = new CheckersBlockGrid(gameType, getInitiationBlockConfig(gameType, startPattern), numPlayers);
  }

  @Override
  public void play(Coordinate passInCoordinate) {
    checkBoard.play(passInCoordinate, getCurrentPlayerIndex());
    if (checkBoard.isFinishARound()){
      playerTakeTurn();
      checkBoard.resetFinishAround();
    }
    notifyObservers();
  }

  @Override
  public BlockGrid getBoard(){
    return checkBoard;
  }
}
