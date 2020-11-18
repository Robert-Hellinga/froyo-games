package ooga.model.game;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.controller.FroyoController;
import ooga.controller.GameController.PlayerMode;
import ooga.model.game.Game;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import org.junit.jupiter.api.Test;

class CheckersGameTest{

  private final String gameType = "Checkers";
  private final String startPattern = "test";
  private Game checkersGame;

  private final Player playerOne = new HumanPlayer("Player 1");
  private final Player playerTwo = new HumanPlayer("Player 2");

  @Test
  public void testChooseAPiece(){
    checkersGame = FroyoController.createGame(gameType, playerOne, playerTwo, startPattern);
    Coordinate pieceToChoose = new Coordinate(0,0);
    checkersGame.play(pieceToChoose);
    assertTrue(checkersGame.getBoard().getAllBlocks().getBlock(pieceToChoose).getBlockState().isChosen());
  }

  @Test
  public void testChooseAPieceAndMoveIt(){
    checkersGame = FroyoController.createGame(gameType, playerOne, playerTwo, startPattern);
    Coordinate pieceToChoose = new Coordinate(2,0);
    Coordinate pieceToMoveTo = new Coordinate(3,1);
    checkersGame.play(pieceToChoose);
    checkersGame.play(pieceToMoveTo);
    List<List<Integer>> expectedBlockState = new ArrayList<>(){{
      add(List.of(1,0,0,0));
      add(List.of(0,2,0,1));
      add(List.of(0,0,0,0));
    }};
    assertEquals(expectedBlockState, checkersGame.getAllBlockStates());
  }

  @Test
  public void testChooseAPieceAndCrossAnotherPiece(){
    checkersGame = FroyoController.createGame(gameType, playerOne, playerTwo, startPattern);
    Coordinate pieceToChoose = new Coordinate(0,0);
    Coordinate pieceToMoveTo = new Coordinate(2,2);
    checkersGame.play(pieceToChoose);
    checkersGame.play(pieceToMoveTo);
    List<List<Integer>> expectedBlockState = new ArrayList<>(){{
      add(List.of(0,0,1,0));
      add(List.of(0,0,0,0));
      add(List.of(0,0,6,0));
    }};
    assertEquals(expectedBlockState, checkersGame.getAllBlockStates());
  }

  @Test
  public void testAPieceBecomeKingWhenItReachesOtherEnd(){
    checkersGame = FroyoController.createGame(gameType, playerOne, playerTwo, startPattern);
    Coordinate pieceToChoose = new Coordinate(0,0);
    Coordinate pieceToMoveTo = new Coordinate(2,2);
    checkersGame.play(pieceToChoose);
    checkersGame.play(pieceToMoveTo);
    assertTrue(checkersGame.getBoard().getAllBlocks().getBlock(pieceToMoveTo).getBlockState().isKing());
  }

  @Test
  public void testIfKingPieceCanMoveBackward(){
    checkersGame = FroyoController.createGame(gameType, playerOne, playerTwo, startPattern);
    Coordinate pieceToChoose = new Coordinate(0,0);
    Coordinate pieceToKing = new Coordinate(2,2);
    Coordinate pieceKingMoveTo = new Coordinate(1,1);
    checkersGame.play(pieceToChoose);
    checkersGame.play(pieceToKing);
    checkersGame.playerTakeTurn();
    checkersGame.play(pieceToKing);
    checkersGame.play(pieceKingMoveTo);
    List<List<Integer>> expectedBlockState = new ArrayList<>(){{
      add(List.of(0,0,1,0));
      add(List.of(0,6,0,0));
      add(List.of(0,0,0,0));
    }};
    assertEquals(expectedBlockState, checkersGame.getAllBlockStates());
  }

}
