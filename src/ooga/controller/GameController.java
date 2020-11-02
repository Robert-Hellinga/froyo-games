package ooga.controller;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.Game;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.BlockState;
import ooga.view.grid.PieceGrid;
import ooga.view.grid.PieceStateStructure;

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

  public BlockStructure getAllBlocks() {
    return game.getCheckBoard().getAllBlocks();
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
  public void clickPiece(Coordinate coordinate) {
    game.play(coordinate);
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
