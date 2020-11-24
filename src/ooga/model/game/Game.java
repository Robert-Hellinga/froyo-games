package ooga.model.game;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.controller.SocialController;
import ooga.exceptions.FileException;
import ooga.fileHandler.FileReader;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.player.Player;
import ooga.view.ModelObserver;

public abstract class Game {

  public static final List<Integer> PLAYER_INDEX_POLL = new ArrayList<>(List.of(1, 2));

  protected String gameType;
  protected List<Player> allPlayers;
  protected int numPlayers;
  protected Player currentPlayer;
  protected List<ModelObserver> observers;
  protected boolean wonGame;
  protected SocialController socialController;
  protected boolean turnsEnabled;

  public Game(String gameType, Player playerOne, Player playerTwo, String startPattern) {
    this.gameType = gameType;
    socialController = null;
    allPlayers = new ArrayList<>();
    allPlayers.add(playerOne);
    allPlayers.add(playerTwo);
    currentPlayer = playerOne;
    observers = new ArrayList<>();
    this.gameType = gameType;
    wonGame = false;
    turnsEnabled = true;
  }

  /**
   * get initiation block configuration
   * @param gameType the game type
   * @param startPattern the start pattern
   * @return the block configuration
   * @throws FileException
   */

  protected List<List<Integer>> getInitiationBlockConfig(String gameType, String startPattern)
      throws FileException {
    FileReader fileReader = new FileReader(gameType, startPattern);
    return fileReader.readBlockLayout();
  }

  /**
   * set the data base
   * @param socialController social controller
   */

  public void setDatabase(SocialController socialController) {
    this.socialController = socialController;
  }

  /**
   * update the database
   */

  public void updateDatabase() {
    if (socialController != null) {
      socialController.updateGame(false);
    }
  }

  /**
   * player take turn
   */

  public void playerTakeTurn() {
    int currentPlayerIndex = allPlayers.indexOf(currentPlayer);
    if (currentPlayerIndex == allPlayers.size() - 1) {
      currentPlayer = allPlayers.get(0);
    } else {
      currentPlayer = allPlayers.get(currentPlayerIndex + 1);
    }
  }

  /**
   * get the current player
   * @return
   */

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * get the index of the current player
   * @return the index of the current player
   */

  public int getCurrentPlayerIndex() {
    return allPlayers.indexOf(currentPlayer) + 1;
  }

  /**
   * get the block grid
   * @return the block grid
   */

  public abstract BlockGrid getBoard();

  /**
   * play the game
   * @param passInCoordinate the coordinate to be played on
   */

  public abstract void play(Coordinate passInCoordinate);

  /**
   * set all the block state
   * @param stateString state string
   */

  public void setAllBlockStates(String stateString) {
    getBoard().setAllBlockStates(stateString);
  }

  /**
   * get all the block states
   * @return all the block states
   */

  public List<List<Integer>> getAllBlockStates() {
    return getBoard().getAllBlockStates();
  }

  /**
   * get all the block state as string
   * @return all the block state as string
   */

  public String getAllBlockStatesAsString() {
    return getBoard().getAllBlockStatesAsString();
  }

  /**
   * register observer
   * @param observer
   */

  public void registerObserver(ModelObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  /**
   * notify the observer
   */

  public void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update();
    }
  }

  /**
   * get all the players
   * @return all the players
   */

  public List<Player> getAllPlayers() {
    return allPlayers;
  }

  /**
   * get the game type
   * @return the game type
   */

  public String getGameType() {
    return gameType;
  }

  /**
   * is any player winning the game
   * @return whether a player is winning the game
   */

  public boolean isPlayerWonGame() {
    return wonGame;
  }

  /**
   * get the winning player
   * @return the winning player
   */

  public abstract Player getWinningPlayer();

  /**
   * check whether the current player have potential moves
   * @return the current player have potential moves
   */

  public abstract boolean currentPlayerHavePotentialMoves();

  /**
   * end the game
   */

  public void endGame() {
    wonGame = true;
  }
}
