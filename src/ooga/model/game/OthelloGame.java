package ooga.model.game;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.OthelloBlockGrid;
import ooga.model.player.Player;

public class OthelloGame extends Game{

  private OthelloBlockGrid othelloBoard;

  public OthelloGame(String gameType, Player playerOne, Player playerTwo, String startPattern){
    super(gameType, playerOne, playerTwo, startPattern);
    othelloBoard = new OthelloBlockGrid(gameType, getInitiationBlockConfig(gameType, startPattern), numPlayers);
  }

  @Override
  public void play(Coordinate passInCoordinate) {
    othelloBoard.play(passInCoordinate, getCurrentPlayerIndex());
    if (othelloBoard.isFinishARound()){
      playerTakeTurn();
      othelloBoard.setAvailablePosition(getCurrentPlayerIndex(), Coordinate.INVALID_COORDINATE);
      othelloBoard.resetFinishAround();
    }
    notifyObservers();
  }

  @Override
  public BlockGrid getBoard(){
    return othelloBoard;
  }
}
