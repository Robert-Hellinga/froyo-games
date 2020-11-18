package ooga.model.game;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.exceptions.FileException;
import ooga.controller.SocialController;
import ooga.fileHandler.FileReader;
import ooga.model.player.Player;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.Block;
//import ooga.model.player.Player.PlayerType;
import ooga.view.ModelObserver;

public abstract class Game {

  public static final List<Integer> PLAYER_INDEX_POLL = new ArrayList<>(List.of(1, 2));

  protected String gameType;
  protected List<Player> allPlayers = new ArrayList<>();
  protected int numPlayers;
  protected Player currentPlayer;
  protected List<ModelObserver> observers;
  protected boolean wonGame;
  protected boolean haveNoPotentialMove;
  protected SocialController socialController;
  protected boolean turnsEnabled;

  public Game(String gameType, Player playerOne, Player playerTwo, String startPattern) {
    this.gameType = gameType;
    socialController = null;
    allPlayers.add(playerOne);
    allPlayers.add(playerTwo);
    currentPlayer = playerOne;
    observers = new ArrayList<>();
    wonGame = false;
    haveNoPotentialMove = false;
    turnsEnabled = true;
  }


  protected BlockConfigStructure getInitiationBlockConfig(String gameType, String startPattern) throws FileException {
    FileReader fileReader = new FileReader(gameType, startPattern);
    return fileReader.makeBlockStructure();
  }

  public void setDatabase(SocialController socialController) {
    this.socialController = socialController;
  }

  public void updateDatabase() {
    if(socialController != null) {
      socialController.updateGame(false);
    }
  }

  public String getGameType() {
    return gameType;
  }

  public void playerTakeTurn() {
    int currentPlayerIndex = allPlayers.indexOf(currentPlayer);
    if (currentPlayerIndex == allPlayers.size() - 1) {
      currentPlayer = allPlayers.get(0);
    } else {
      currentPlayer = allPlayers.get(currentPlayerIndex + 1);
    }
  }

  public Player getCurrentPlayer(){
    return currentPlayer;
  }

  public int getCurrentPlayerIndex() {
    return allPlayers.indexOf(currentPlayer) + 1;
  }

  public abstract BlockGrid getBoard();

  public abstract void play(Coordinate passInCoordinate);

  public void setAllBlockStates(String stateString) {
    getBoard().setAllBlockStates(stateString);
  }

  public List<List<Integer>> getAllBlockStates() {
    return getBoard().getAllBlockStates();
  }

  public String getAllBlockStatesAsString() {
    return getBoard().getAllBlockStatesAsString();
  }

  public void registerObserver(ModelObserver observer){
    observers.add(observer);
  }

  public void removeObserver(ModelObserver observer){
    observers.remove(observer);
  }

  public void notifyObservers() {
    for (ModelObserver observer : observers){
      observer.update();
    }
  }

  public List<Player> getAllPlayers(){
    return allPlayers;
  }

  public boolean isPlayerWonGame(){
    return wonGame;
  }

  public abstract Player getWinningPlayer();

  public boolean isHaveNoPotentialMove() {
    return haveNoPotentialMove;
  }

  public void resetHaveNotPotentialMove(){
    haveNoPotentialMove = false;
  }

  public abstract boolean currentPlayerHavePotentialMoves();

  public void endGame(){
    wonGame = true;
  }
}
