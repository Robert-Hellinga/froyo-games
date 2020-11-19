package ooga.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Connect4GameTest {

  private final String gameType = "Connect4";
  private final String startPattern = "test";
  private final Player playerOne = new HumanPlayer("Player 1");
  private final Player playerTwo = new HumanPlayer("Player 2");
  private Game connect4Game;

  @BeforeEach
  public void initialTestBoard() {
    connect4Game = new Connect4Game(gameType, playerOne, playerTwo, startPattern);
  }

  @Test
  public void testDropPiece() {
    Coordinate pieceToPress = new Coordinate(0, 0);
    connect4Game.play(pieceToPress);
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0));
      add(List.of(0, 0, 0, 0));
      add(List.of(1, 0, 0, 0));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());
  }

  @Test public void testHorizontalPieces(){
    connect4Game.play(new Coordinate(0, 2));
    connect4Game.play(new Coordinate( 0, 3));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {
      {
        add(List.of(0, 0, 0, 0));
        add(List.of(0, 0, 0, 0));
        add(List.of(0, 0, 1, 2));
      }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());
  }

  @Test
  public void testDropPieceOnOtherPiece() {
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(0, 0));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0));
      add(List.of(2, 0, 0, 0));
      add(List.of(1, 0, 0, 0));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());
  }

  @Test
  public void testSeriesOfDroppingPieces() {
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(0, 1));
    connect4Game.play(new Coordinate(0, 1));
    connect4Game.play(new Coordinate(2, 2));
    connect4Game.play(new Coordinate(2, 2));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(1, 0, 0, 0));
      add(List.of(2, 0, 1, 0));
      add(List.of(1, 0, 2, 0));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());
  }

  @Test
  public void testOtherSeriesOfDroppingPieces() {
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(0, 1));
    connect4Game.play(new Coordinate(0, 1));
    connect4Game.play(new Coordinate(2, 2));
    connect4Game.play(new Coordinate(2, 2));

    connect4Game.play(new Coordinate(2, 2));
    connect4Game.play(new Coordinate(3, 2));
    connect4Game.play(new Coordinate(3, 2));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(1, 0, 2, 0));
      add(List.of(2, 0, 1, 2));
      add(List.of(1, 0, 2, 1));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());
  }
}
