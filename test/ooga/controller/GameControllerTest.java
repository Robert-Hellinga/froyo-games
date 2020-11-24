package ooga.controller;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.model.game.CheckersGame;
import ooga.model.game.Game;
import ooga.model.player.AIPlayer;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GameControllerTest extends DukeApplicationTest {

  private GameController gameController;
  private Player playerOne = new HumanPlayer("P1");
  private Player playerTwo = new HumanPlayer("P2");
  private Player aiPlayer = new AIPlayer();
  private Game checkersLocal = new CheckersGame("Checkers", playerOne,
      playerTwo, "default");
  private Game checkersAI = new CheckersGame("Checkers", playerOne, aiPlayer, "default");


  @Test
  public void testClickPiece() {
    playerOne.setMyGame(checkersLocal, "Checkers");
    playerTwo.setMyGame(checkersLocal, "Checkers");
    javafxRun(
        () -> gameController = new GameController(checkersLocal, PlayerMode.PLAY_WITH_FRIEND));
    gameController.clickPiece(new Coordinate(0, 0));
  }

  @Test
  public void testClickPieceAI() {
    playerOne.setMyGame(checkersAI, "Checkers");
    aiPlayer.setMyGame(checkersAI, "Checkers");
    javafxRun(() -> gameController = new GameController(checkersAI, PlayerMode.PLAY_WITH_AI));
    gameController.clickPiece(new Coordinate(2, 2));
    gameController.clickPiece(new Coordinate(3, 3));
    gameController.checkForAITurn();
  }

}
