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

  public List<List<Integer>> getAllBlockStates() {
    BlockStructure blocks = game.getCheckBoard().getAllBlocks();
    List<List<Integer>> blockState = new ArrayList<>();
    for(int i = 0; i < blocks.getBlockStructureHeight(); i++){
      List<Integer> blockStateLine = new ArrayList<>();
      for(int j = 0; j < blocks.getBlockStructureWidth(); j++){
        Block currentBlock = blocks.getBlock(new Coordinate(j,i));
        blockStateLine.add(currentBlock.getBlockState().getNumericState());
      }
      blockState.add(blockStateLine);
    }
    return blockState;
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
    grid.update();
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
