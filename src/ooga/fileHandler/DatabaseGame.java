package ooga.fileHandler;

public class DatabaseGame {

  // Game stores: current turn, and board state
  // Each game is added to database under the name of the person who created it

  public String boardState;
  public String gameType;

  public DatabaseGame(String boardState, String gameType) {

    this.boardState = boardState;
    this.gameType = gameType;
  }
}
