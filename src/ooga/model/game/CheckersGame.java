package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.CheckersBlockGrid;

public class CheckersGame extends Game {

  private CheckersBlockGrid checkBoard;

  public CheckersGame(String gameType, String playerName, PlayerMode playerMode){
    super(gameType, playerName, playerMode);
    checkBoard = new CheckersBlockGrid(gameType, getInitiationBlockConfig(gameType), numPlayers);
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
  public void aiPlay() {

  }

  @Override
  public BlockGrid getBoard(){
    return checkBoard;
  }
}
