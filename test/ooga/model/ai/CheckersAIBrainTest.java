package ooga.model.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.game.CheckersGame;
import ooga.model.game.Game;
import ooga.model.player.AIPlayer;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckersAIBrainTest {

  private final String gameType = "Checkers";
  private final String startPattern = "default";
  private final Player playerOne = new HumanPlayer("Player 1");
  private final Player playerTwo = new AIPlayer();
  private Game checkersGame;

  @BeforeEach
  public void initialTestBoard() {
    checkersGame = new CheckersGame(gameType, playerOne, playerTwo, startPattern);
  }

  @Test
  void decideMove_1() {
    checkersGame.play(new Coordinate(4, 2));
    checkersGame.play(new Coordinate(3, 3));
    checkersGame.play(new Coordinate(1, 5));
    checkersGame.play(new Coordinate(2, 4));
    checkersGame.play(new Coordinate(3, 3));
    checkersGame.play(new Coordinate(1, 5));

    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(1, 0, 1, 0, 1, 0, 1, 0));
      add(List.of(0, 1, 0, 1, 0, 1, 0, 1));
      add(List.of(1, 0, 1, 0, 0, 0, 1, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 1, 0, 2, 0, 2, 0, 2));
      add(List.of(2, 0, 2, 0, 2, 0, 2, 0));
      add(List.of(0, 2, 0, 2, 0, 2, 0, 2));
    }};
    assertEquals(expectedBlockState, checkersGame.getAllBlockStates());

    CheckersAIBrain AIBrain = new CheckersAIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(checkersGame.getBoard(), 2);
    checkersGame.play(aiMove.get(0));
    assertTrue(checkersGame.getBoard().getAllPotentialMoves(2).contains(aiMove.get(1)));
    checkersGame.play(aiMove.get(1));
    assertNotEquals(expectedBlockState, checkersGame.getAllBlockStates());
  }

  @Test
  void decideMove_2() {
    checkersGame.play(new Coordinate(4, 2));
    checkersGame.play(new Coordinate(3, 3));
    checkersGame.play(new Coordinate(3, 5));
    checkersGame.play(new Coordinate(4, 4));
    checkersGame.play(new Coordinate(6, 2));
    checkersGame.play(new Coordinate(7, 3));
    checkersGame.play(new Coordinate(5, 5));
    checkersGame.play(new Coordinate(6, 4));
    checkersGame.play(new Coordinate(5, 1));
    checkersGame.play(new Coordinate(6, 2));
    checkersGame.play(new Coordinate(6, 6));
    checkersGame.play(new Coordinate(5, 5));
    checkersGame.play(new Coordinate(0, 2));
    checkersGame.play(new Coordinate(1, 3));
    checkersGame.play(new Coordinate(5, 7));
    checkersGame.play(new Coordinate(6, 6));
    checkersGame.play(new Coordinate(3, 3));
    checkersGame.play(new Coordinate(6, 2));
    checkersGame.play(new Coordinate(5, 3));
    checkersGame.play(new Coordinate(1, 5));
    checkersGame.play(new Coordinate(0, 4));
    checkersGame.play(new Coordinate(5, 3));
    checkersGame.play(new Coordinate(5, 7));

    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(1, 0, 1, 0, 1, 0, 1, 0));
      add(List.of(0, 1, 0, 1, 0, 0, 0, 1));
      add(List.of(0, 0, 1, 0, 0, 0, 0, 0));
      add(List.of(0, 1, 0, 1, 0, 0, 0, 1));
      add(List.of(2, 0, 0, 0, 0, 0, 2, 0));
      add(List.of(0, 0, 0, 0, 0, 2, 0, 2));
      add(List.of(2, 0, 2, 0, 0, 0, 2, 0));
      add(List.of(0, 2, 0, 2, 0, 5, 0, 2));
    }};
    assertEquals(expectedBlockState, checkersGame.getAllBlockStates());

    CheckersAIBrain AIBrain = new CheckersAIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(checkersGame.getBoard(), 2);
    checkersGame.play(aiMove.get(0));
    assertTrue(checkersGame.getBoard().getAllPotentialMoves(2).contains(aiMove.get(1)));
    checkersGame.play(aiMove.get(1));
    assertNotEquals(expectedBlockState, checkersGame.getAllBlockStates());
  }
}
