package ooga.controller;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.Game;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.block.BlockState;
import ooga.view.grid.PieceGrid;

public class GameController implements GameControllerInterface {

  public enum PlayerMode {
    PLAY_WITH_AI,
    PLAY_WITH_FRIEND
  }

  private PieceGrid pieceGrid;
  private Game game;
  private int playerInTurn;
  private Coordinate pieceChosen;

  public GameController() {
    this("Checkers"); // TODO rework constructors
  }

  public GameController(String gameType) {
    game = new Game(gameType, "Anna", PlayerMode.PLAY_WITH_AI);
  }

  public void setPlayerMode(PlayerMode mode) {

  }

  public void setGameType(String gameType) {

  }

  public void update() {

  }

  @Override
  public void createGame() {

  }

  @Override
  public void clickPiece(Coordinate coordinate, PieceGrid grid) {
    game.play(coordinate);
//    grid.updateGrid();


    // update grid needs observer
    // getting new states needs observer
  }

  @Override
  public void saveGame() {

  }

  @Override
  public void restartGame() {

  }

  @Override
  public void quitGame() {

  }
}
