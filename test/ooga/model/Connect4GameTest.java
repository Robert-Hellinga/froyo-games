package ooga.model;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.model.game.Connect4Game;
import ooga.model.game.Game;
import org.junit.jupiter.api.Test;

class Connect4GameTest {

  private final String gameType = "Connect4";
  private final String playerName = "a";
  private final PlayerMode playerMode = PlayerMode.PLAY_WITH_FRIEND;
  private final String startPattern = "test";
  private Game connect4Game;

//  @Test
//  public void testDropPiece(){
//    connect4Game = new Connect4Game(gameType, playerName, playerMode, startPattern);
//    Coordinate pieceToPress = new Coordinate(0,0);
//    connect4Game.play(pieceToPress);
//    List<List<Integer>> expectedBlockState = new ArrayList<>(){{
//      add(List.of(0,0,0,0));
//      add(List.of(0,0,0,0));
//      add(List.of(1,0,0,0));
//    }};
//    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());
//  }
//
//  @Test
//  public void testDropPieceOnOtherPiece(){
//    connect4Game = new Connect4Game(gameType, playerName, playerMode, startPattern);
//    Coordinate pieceToPress = new Coordinate(0,0);
//    connect4Game.play(pieceToPress);
//    Coordinate pieceToPress2 = new Coordinate(0,0);
//    connect4Game.play(pieceToPress2);
//    List<List<Integer>> expectedBlockState = new ArrayList<>(){{
//      add(List.of(0,0,0,0));
//      add(List.of(2,0,0,0));
//      add(List.of(1,0,0,0));
//    }};
//    assertEquals(expectedBlockState, connect4Game.getAllBlockStates());
//  }
}
