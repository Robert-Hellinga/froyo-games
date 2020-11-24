package ooga.model.game;

import ooga.Coordinate;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.OthelloBlockGrid;
import ooga.model.player.Player;

/**
 * @author Yixuan Li, Jincheng He, Nate Mela, Robert Hellinga
 */
public class OthelloGame extends Game {

  private OthelloBlockGrid othelloBoard;

  /**
   * This is the constructor
   *
   * @param gameType     The String represents the type of the game
   * @param playerOne    Player class of player one
   * @param playerTwo    Player class of player two
   * @param startPattern String represents the start pattern
   */
  public OthelloGame(String gameType, Player playerOne, Player playerTwo, String startPattern) {
    super(gameType, playerOne, playerTwo, startPattern);
    othelloBoard = new OthelloBlockGrid(gameType, getInitiationBlockConfig(gameType, startPattern),
        numPlayers);
  }

  /**
   * @see Game#play(Coordinate)
   */
  @Override
  public void play(Coordinate passInCoordinate) {
    if (othelloBoard.getAllBlocks().getBlock(passInCoordinate).isPotentialMove()) {
      othelloBoard.play(passInCoordinate, getCurrentPlayerIndex());
      if (othelloBoard.isFinishARound()) {
        if (othelloBoard.isWinningMove(getCurrentPlayerIndex())) {
          wonGame = true;
        } else {
          updateDatabase();
          playerTakeTurn();
        }
      }
    }
    notifyObservers();
  }

  /**
   * @see Game#currentPlayerHavePotentialMoves()
   */
  @Override
  public boolean currentPlayerHavePotentialMoves() {
    return !othelloBoard.getAllPotentialMoves(getCurrentPlayerIndex()).isEmpty();
  }

  /**
   * @see Game#getBoard()
   */
  @Override
  public BlockGrid getBoard() {
    return othelloBoard;
  }

  /**
   * @see Game#getWinningPlayer()
   */
  @Override
  public Player getWinningPlayer() {
    return wonGame ? allPlayers.get(othelloBoard.getWinningPlayerIndex()) : null;
  }

  /**
   * @see Game#playerTakeTurn()
   */
  @Override
  public void playerTakeTurn() {
    int currentPlayerIndex = allPlayers.indexOf(currentPlayer);
    if (currentPlayerIndex == allPlayers.size() - 1) {
      currentPlayer = allPlayers.get(0);
    } else {
      currentPlayer = allPlayers.get(currentPlayerIndex + 1);
    }
    othelloBoard.setAvailablePositions(getCurrentPlayerIndex(), Coordinate.INVALID_COORDINATE);
    othelloBoard.resetFinishARound();
  }
}
