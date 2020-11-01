package ooga;

import javafx.scene.Group;
import ooga.model.Game;
import ooga.view.piecegrid.CheckersPieceGrid;
import ooga.view.piecegrid.PieceGrid;
import ooga.view.PieceStateStructure;

public class GameController {

  public enum PlayerMode {
    PLAY_WITH_AI,
    PLAY_WITH_FRIEND
  }


  //just for test
  public static final PieceStateStructure initialPieceState = new PieceStateStructure();

  private PieceGrid pieceGrid;
  private Game game;
  private int playerInTurn;
  private Coordinate pieceChosen;


  public GameController(String gameType, Group root) {
    game = new Game(gameType, "Anna", ooga.controller.GameController.PlayerMode.PLAY_WITH_AI);
    pieceGrid = new CheckersPieceGrid(gameType, initialPieceState, root);
  }

  public void update() {
    playerInTurn = game.getCurrentPlayerIndex();
    pieceGrid.updatePieceCenBeChosenOrNotStatus(playerInTurn);
    pieceChosen = pieceGrid.getChosenPieceCoordinate();
    game.updatePieceChosen(pieceChosen);
    pieceGrid.showPotentialMoveAndMakeClickable(game.getPotentialMovePos());
    pieceGrid.updateNonemptyColor();
  }
}
