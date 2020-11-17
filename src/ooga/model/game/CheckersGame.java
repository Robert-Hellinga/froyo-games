package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.fileHandler.Database;
import ooga.model.BlockGrid;
import ooga.model.CheckersBlockGrid;
import ooga.model.player.Player;

public class CheckersGame extends Game {

  private CheckersBlockGrid checkBoard;

  public CheckersGame(String gameType, Player playerOne, Player playerTwo, String startPattern){
    super(gameType, playerOne, playerTwo, startPattern);
    checkBoard = new CheckersBlockGrid(gameType, getInitiationBlockConfig(gameType, startPattern), numPlayers);
  }

  @Override
  public void play(Coordinate passInCoordinate) {
    System.out.println("Playing... " + passInCoordinate + " turnsEnabled: " + turnsEnabled +
        " isFinishAround: " + checkBoard.isFinishARound());
    if (turnsEnabled) {

//      System.out.print(1 + " ");
      checkBoard.play(passInCoordinate, getCurrentPlayerIndex());
//      System.out.print(2 + " ");

      if (checkBoard.isFinishARound()) {
        playerTakeTurn();
        checkBoard.resetFinishAround();
      }
//      System.out.print(3 + " ");

      notifyObservers();
      System.out.println(4 + " ");

    }
  }

  @Override
  public BlockGrid getBoard(){
    return checkBoard;
  }
}
