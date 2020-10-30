package ooga;

import javafx.scene.Group;
import ooga.controller.GameController.PlayerMode;
import ooga.model.Game;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.checkerboard.BlockGrid;
import ooga.view.CheckersPieceGrid;
import ooga.view.PieceGrid;
import ooga.view.PieceStateStructure;

public class GameController {

  public enum PlayerMode {
    PLAY_WITH_AI,
    PLAY_WITH_FRIEND
  }

  //just for test
  public static final PieceStateStructure initialPieceState = new PieceStateStructure();
  public static final BlockConfigStructure initialBlockConfig = new BlockConfigStructure();

  private PieceGrid pieceGrid;
  private BlockGrid blockGrid;
  private Game game;
  private int playerInTurn;


  public GameController(String gameType, Group root){
//    blockGrid = new BlockGrid(gameType, initialBlockConfig);
    game = new Game(gameType, "Anna", ooga.controller.GameController.PlayerMode.PLAY_WITH_AI);
    pieceGrid = new CheckersPieceGrid(gameType, initialPieceState, root);
  }

  public void update(){
    playerInTurn = game.getCurrentPlayerIndex();
    pieceGrid.updatePieceGrid(playerInTurn);
  }
}
