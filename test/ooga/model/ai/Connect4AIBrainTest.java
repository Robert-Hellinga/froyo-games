package ooga.model.ai;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.game.Connect4Game;
import ooga.model.game.Game;
import ooga.model.player.AIPlayer;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Connect4AIBrainTest {

  private final String gameType = "Connect4";
  private final String startPattern = "default";
  private final Player playerOne = new HumanPlayer("Player 1");
  private final Player playerTwo = new AIPlayer();
  private Game connect4Game;

  @BeforeEach
  public void initialTestBoard() {
    connect4Game = new Connect4Game(gameType, playerOne, playerTwo, startPattern);
  }


  @Test
  void decideLosingMove() {
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(7, 0));
    connect4Game.play(new Coordinate(1, 0));
    connect4Game.play(new Coordinate(6, 0));
    connect4Game.play(new Coordinate(2, 0));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(1, 1, 1, 0, 0, 0, 2, 2));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());

    // Let AI take turn
    Connect4AIBrain AIBrain = new Connect4AIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(connect4Game.getBoard(), 2);
    // AI must take the 4th column, otherwise AI will lose
    assertEquals(3, aiMove.get(0).xCoordinate());
  }


  @Test
  void decideWinningMove() {
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(7, 0));
    connect4Game.play(new Coordinate(1, 0));
    connect4Game.play(new Coordinate(6, 0));
    connect4Game.play(new Coordinate(2, 0));
    connect4Game.play(new Coordinate(5, 0));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(1, 1, 1, 0, 0, 2, 2, 2));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());

    // Let AI take turn
    Connect4AIBrain AIBrain = new Connect4AIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(connect4Game.getBoard(), 2);
    // AI must take the 5th column, in this way, AI must win, which makes sense
    assertEquals(4, aiMove.get(0).xCoordinate());
  }


  @Test
  void decideVerticalLosingMove() {
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(7, 0));
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(7, 0));
    connect4Game.play(new Coordinate(0, 0));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(1, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(1, 0, 0, 0, 0, 0, 0, 2));
      add(List.of(1, 0, 0, 0, 0, 0, 0, 2));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());

    // Let AI take turn
    Connect4AIBrain AIBrain = new Connect4AIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(connect4Game.getBoard(), 2);
    // AI must take the 1th column, otherwise AI will lose
    assertEquals(0, aiMove.get(0).xCoordinate());
  }


  @Test
  void decideVerticalWinningMove() {
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(7, 0));
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(7, 0));
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(7, 0));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(1, 0, 0, 0, 0, 0, 0, 2));
      add(List.of(1, 0, 0, 0, 0, 0, 0, 2));
      add(List.of(1, 0, 0, 0, 0, 0, 0, 2));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());

    // Let AI take turn
    Connect4AIBrain AIBrain = new Connect4AIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(connect4Game.getBoard(), 2);
    // AI must take the 8th column, in this way, AI must win, which makes sense
    assertEquals(7, aiMove.get(0).xCoordinate());
  }


  @Test
  void decidePositiveSlopeLosingMove() {
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(5, 0));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 1, 0, 0));
      add(List.of(0, 0, 0, 0, 1, 2, 0, 0));
      add(List.of(0, 0, 0, 1, 2, 1, 0, 0));
      add(List.of(0, 0, 0, 2, 2, 1, 0, 0));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());

    // Let AI take turn
    Connect4AIBrain AIBrain = new Connect4AIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(connect4Game.getBoard(), 2);
    // AI must take the 3th column, otherwise AI will lose
    assertEquals(2, aiMove.get(0).xCoordinate());
  }


  @Test
  void decidePositiveSlopeWinningMove() {
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(0, 0));
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(5, 0));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 2, 0, 0));
      add(List.of(0, 0, 0, 0, 2, 1, 0, 0));
      add(List.of(0, 0, 0, 2, 1, 1, 0, 0));
      add(List.of(2, 0, 0, 1, 1, 2, 0, 0));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());

    // Let AI take turn
    Connect4AIBrain AIBrain = new Connect4AIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(connect4Game.getBoard(), 2);
    // AI must take the 3th column, in this way, AI must win, which makes sense
    assertEquals(2, aiMove.get(0).xCoordinate());
  }


  @Test
  void decideNegativeSlopeLosingMove() {
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(3, 0));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 1, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 2, 1, 0, 0, 0));
      add(List.of(0, 0, 0, 1, 2, 1, 0, 0));
      add(List.of(0, 0, 0, 1, 2, 2, 0, 0));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());

    // Let AI take turn
    Connect4AIBrain AIBrain = new Connect4AIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(connect4Game.getBoard(), 2);
    // AI must take the 7th column, otherwise AI will lose
    assertEquals(6, aiMove.get(0).xCoordinate());
  }


  @Test
  void decideNegativeSlopeWinningMove() {
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(5, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(7, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(4, 0));
    connect4Game.play(new Coordinate(3, 0));
    connect4Game.play(new Coordinate(3, 0));
    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 2, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 1, 2, 0, 0, 0));
      add(List.of(0, 0, 0, 1, 1, 2, 0, 0));
      add(List.of(0, 0, 0, 2, 1, 1, 0, 2));
    }};
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());

    // Let AI take turn
    Connect4AIBrain AIBrain = new Connect4AIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(connect4Game.getBoard(), 2);
    // AI must take the 7th column, in this way, AI must win, which makes sense
    assertEquals(6, aiMove.get(0).xCoordinate());
  }
}