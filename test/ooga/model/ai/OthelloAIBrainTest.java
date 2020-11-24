package ooga.model.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.game.Game;
import ooga.model.game.OthelloGame;
import ooga.model.player.AIPlayer;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OthelloAIBrainTest {

  private final String gameType = "Othello";
  private final String startPattern = "default";
  private final Player playerOne = new HumanPlayer("Player 1");
  private final Player playerTwo = new AIPlayer();
  private Game othelloGame;

  @BeforeEach
  public void initialTestBoard() {
    othelloGame = new OthelloGame(gameType, playerOne, playerTwo, startPattern);
  }

  @Test
  void decideMove() {
    othelloGame.play(new Coordinate(4, 5));
    othelloGame.play(new Coordinate(3, 5));
    othelloGame.play(new Coordinate(2, 6));

    List<List<Integer>> expectedBlockState = new ArrayList<>() {{
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 5, 0, 0));
      add(List.of(0, 0, 0, 2, 1, 5, 0, 0));
      add(List.of(0, 0, 0, 2, 1, 5, 0, 0));
      add(List.of(0, 0, 0, 1, 1, 5, 0, 0));
      add(List.of(0, 0, 1, 5, 0, 5, 0, 0));
      add(List.of(0, 0, 0, 0, 0, 0, 0, 0));
    }};
    assertEquals(expectedBlockState, othelloGame.getAllBlockStates());

    OthelloAIBrain AIBrain = new OthelloAIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(othelloGame.getBoard(), 2);
    Coordinate aiCoordinate = aiMove.get(0);

//    Check whether the AI Move is a valid potential move.
    assertEquals(expectedBlockState.get(aiCoordinate.yCoordinate()).get(aiCoordinate.xCoordinate()),
        5);
    othelloGame.play(aiCoordinate);
    assertNotEquals(expectedBlockState, othelloGame.getAllBlockStates());
  }

  @Test
  void testPlaceMove(){
    othelloGame = new OthelloGame(gameType, playerOne, playerTwo, startPattern);
    othelloGame.play(new Coordinate(2, 3));
    List<List<Integer>> expectedBlockState = othelloGame.getBoard().getAllBlockStates();
    OthelloAIBrain AIBrain = new OthelloAIBrain();
    List<Coordinate> aiMove = AIBrain.decideMove(othelloGame.getBoard(), 2);
    Coordinate aiCoordinate = aiMove.get(0);

    othelloGame.play(aiCoordinate);
    assertNotEquals(expectedBlockState, othelloGame.getAllBlockStates());
  }
}