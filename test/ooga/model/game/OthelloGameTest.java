package ooga.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.controller.FroyoController;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import org.junit.jupiter.api.Test;

public class OthelloGameTest {

  private final String gameType = "Othello";
  private final String startPattern = "test";
  private Game othelloGame;

  private final Player playerOne = new HumanPlayer("Player 1");
  private final Player playerTwo = new HumanPlayer("Player 2");

  @Test
  public void checkPotentialMovesForPlayer1() {
    othelloGame = FroyoController.createGame(gameType, playerOne, playerTwo, startPattern);
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 3, 0));
      add(List.of(0, 1, 2, 3));
      add(List.of(3, 2, 1, 0));
      add(List.of(0, 3, 0, 0));
    }};
    assertEquals(expectedBlockState, othelloGame.getAllBlockStates());
  }

  @Test
  public void checkPotentialMovesForPlayer2() {
    othelloGame = FroyoController.createGame(gameType, playerOne, playerTwo, startPattern);
    Coordinate playCoordinate = new Coordinate(2, 0);
    othelloGame.play(playCoordinate);
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 3, 1, 3));
      add(List.of(0, 1, 1, 0));
      add(List.of(0, 2, 1, 3));
      add(List.of(0, 0, 0, 0));
    }};
    assertEquals(expectedBlockState, othelloGame.getAllBlockStates());
  }

  @Test
  public void checkPlacedPiece() {
    othelloGame = FroyoController.createGame(gameType, playerOne, playerTwo, startPattern);
    Coordinate playCoordinate = new Coordinate(2, 0);
    othelloGame.play(playCoordinate);
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 3, 1, 3));
      add(List.of(0, 1, 1, 0));
      add(List.of(0, 2, 1, 3));
      add(List.of(0, 0, 0, 0));
    }};
    assertEquals(expectedBlockState, othelloGame.getAllBlockStates());
  }
}
