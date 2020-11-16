package ooga.model.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.exceptions.FileException;
import ooga.fileHandler.Database;
import ooga.fileHandler.FileReader;
import ooga.model.player.Player;
import ooga.model.ai.AIBrain;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.BlockGrid;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.Block;
//import ooga.model.player.Player.PlayerType;
import ooga.view.ModelObserver;

public abstract class Game {

  protected String gameType;
  protected List<Player> allPlayers = new ArrayList<>();
  protected int numPlayers;
  protected Player currentPlayer;
  protected List<ModelObserver> observers;
  protected Database database;
  protected boolean turnsEnabled;

  public Game(String gameType, Player playerOne, Player playerTwo, String startPattern) {
    this.gameType = gameType;
    database = null;
    allPlayers.add(playerOne);
    allPlayers.add(playerTwo);
    currentPlayer = playerOne;
    observers = new ArrayList<>();
    turnsEnabled = true;
  }


  protected BlockConfigStructure getInitiationBlockConfig(String gameType, String startPattern) throws FileException {
    FileReader fileReader = new FileReader(gameType, startPattern);
    return fileReader.makeBlockStructure();
  }

  public void setDatabase(Database database) {
    this.database = database;
  }


//  public void aiPlay(){
//    List<Coordinate> aiMoves = aiBrain.decideMove(getBoard(), getCurrentPlayerIndex());
//    for (Coordinate coordinate : aiMoves){
//      play(coordinate);
//    }
//  }

//  private Player createSecondPlayer(PlayerMode playerMode) {
//    if (playerMode.equals(PlayerMode.PLAY_WITH_AI)) {
//      this.aiBrain = createAIBrain(gameType);
//      return new Player("AI player", PlayerType.AI);
//    }
//    //TODO: need to implement the social feature: connect with another human player through social network
//    return new Player("my friend", PlayerType.HUMAN);
//  }

  public String getGameType() {
    return gameType;
  }

  public void setCurrentPlayer(Player player) {
    currentPlayer = player;
  }

  public void playerTakeTurn() {

    if(database != null) {
      database.updateGame();
      System.out.println("Updating game");
    }
    else {
      int currentPlayerIndex = allPlayers.indexOf(currentPlayer);
      if (currentPlayerIndex == allPlayers.size() - 1) {
        currentPlayer = allPlayers.get(0);
      } else {
        currentPlayer = allPlayers.get(currentPlayerIndex + 1);
      }
    }
    System.out.println(currentPlayer.getName());
  }

  public void enableTurns() {
    turnsEnabled = true;
  }

  public void disableTurns() {
    turnsEnabled = false;
  }

  public boolean getTurnsEnabled() {
    return turnsEnabled;
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
//    notifyObservers();
  }

  public List<List<Integer>> getAllBlockStates() {
    BlockStructure blocks = getBoard().getAllBlocks();
    List<List<Integer>> blockState = new ArrayList<>();
    for(int i = 0; i < blocks.getBlockStructureHeight(); i++){
      List<Integer> blockStateLine = new ArrayList<>();
      for(int j = 0; j < blocks.getBlockStructureWidth(); j++){
        Block currentBlock = blocks.getBlock(new Coordinate(j,i));
        blockStateLine.add(currentBlock.getBlockState().getNumericState());
      }
      blockState.add(blockStateLine);
    }
    return blockState;
  }

  public String getAllBlockStatesAsString() {
    BlockStructure blocks = getBoard().getAllBlocks();
    String result = "";
    for(int i = 0; i < blocks.getBlockStructureHeight(); i++){
      for(int j = 0; j < blocks.getBlockStructureWidth(); j++){
        Block currentBlock = blocks.getBlock(new Coordinate(j,i));
        result += currentBlock.getBlockState().getNumericState() + ",";
      }
      result += "~";
    }
    return result;
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
}
