package ooga.model.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.exceptions.FileException;
import ooga.fileHandler.FileReader;
import ooga.model.Player;
import ooga.model.ai.AIBrain;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.Block;
import ooga.model.Player.PlayerType;
import ooga.view.GameObserver;

public abstract class Game {

  protected String gameType;
  protected List<Player> allPlayers = new ArrayList<>();
  protected int numPlayers;
  protected Player currentPlayer;
  protected List<GameObserver> observers;
  protected AIBrain aiBrain;

  public Game(String gameType, String playerName, PlayerMode playerMode, String startPattern) {
    this.gameType = gameType;
    allPlayers.add(new Player(playerName, PlayerType.HUMAN));
    allPlayers.add(createSecondPlayer(playerMode));
    currentPlayer = allPlayers.get(0);
    observers = new ArrayList<>();
  }


  protected BlockConfigStructure getInitiationBlockConfig(String gameType, String startPattern) throws FileException {
    FileReader fileReader = new FileReader(gameType, startPattern);
    return fileReader.readInAllData();
  }


  public void aiPlay(){
    List<Coordinate> aiMoves = aiBrain.decideMove(getBoard(), getCurrentPlayerIndex());
    for (Coordinate coordinate : aiMoves){
      play(coordinate);
    }
  }

  private Player createSecondPlayer(PlayerMode playerMode) {
    if (playerMode.equals(PlayerMode.PLAY_WITH_AI)) {
      this.aiBrain = createAIBrain(gameType);
      return new Player("AI player", PlayerType.AI);
    }
    //TODO: need to implement the social feature: connect with another human player through social network
    return new Player("my friend", PlayerType.HUMAN);
  }

  public static AIBrain createAIBrain(String gameType){
    try {
      Class<?> aiBrain = Class.forName("ooga.model.ai." + gameType + "AIBrain");
      Class<?>[] param = {};
      Constructor<?> cons = aiBrain.getConstructor(param);
      Object[] paramObject = {};
      Object gameAibrain = cons.newInstance(paramObject);
      return (AIBrain) gameAibrain;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
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

  public void registerObserver(GameObserver observer){
    observers.add(observer);
  }

  public void removeObserver(GameObserver observer){
    observers.remove(observer);
  }

  protected void notifyObservers() {
    for (GameObserver observer : observers){
      observer.update();
    }
  }
}
