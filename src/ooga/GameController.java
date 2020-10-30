package ooga;

import javafx.scene.Group;
import ooga.Model.CheckerBoard.BlockConfigStructure;
import ooga.Model.CheckerBoard.BlockGrid;
import ooga.View.PieceGrid;
import ooga.View.PieceStateStructure;

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


  public GameController(String gameType, Group root){
    blockGrid = new BlockGrid(gameType, initialBlockConfig);
    pieceGrid = new PieceGrid(gameType, initialPieceState, root);
  }
}
