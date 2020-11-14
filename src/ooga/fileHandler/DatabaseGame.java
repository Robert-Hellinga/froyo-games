package ooga.fileHandler;

import java.util.List;

public class DatabaseGame {

  // Game stores: current turn, and board state
  // Each game is added to database under the name of the person who created it

  public String turn;
  public String boardState;

  public DatabaseGame(String turn, String boardState) {

    this.turn = turn;
    this.boardState = boardState;
  }
}
