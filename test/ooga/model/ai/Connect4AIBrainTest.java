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
  void decideMove() {
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
    System.out.println(connect4Game.getAllBlockStates());
    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());

    // Let AI take turn
    Connect4AIBrain AIBrain = new Connect4AIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(connect4Game.getBoard(), 2);
    // AI must take the 4th column, otherwise AI will lose
    assertEquals(3, aiMove.get(0).xCoordinate());
  }
}