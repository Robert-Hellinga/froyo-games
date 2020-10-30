package ooga.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.controller.GameController.PlayerMode;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.checkerboard.BlockGrid;
import ooga.model.player.*;

public class Game {

  private String gameType;
  private List<Player> allPlayers = new ArrayList<>();
  private BlockGrid checkBoard;
  private int numPlayers;
  private Player currentPlayer;

  public Game(String gameType, String playerName, PlayerMode playerMode) {
    this.gameType = gameType;
    checkBoard = new BlockGrid(gameType, getInitiationBlockConfig(gameType), numPlayers);
    //there is always a player that is a human player
    allPlayers.add(createHumanPlayer(gameType, playerName));
    //player 2 depends on the player mode chosen
    allPlayers.add(createSecondPlayer(playerMode));
    currentPlayer = decideWhichPlayerGoFirst(playerMode);
  }

  public void play() {
    playerTakeTurn();
  }


  //TODO: add implementation details in this method (not sure if the configuration will be featured in this level of code)
  private BlockConfigStructure getInitiationBlockConfig(String gameType) {
    return new BlockConfigStructure();
  }

  public static Player createHumanPlayer(String gameType, String name) {
    try {
      Class<?> player = Class.forName("ooga.model.player.humanplayer." + gameType + "HumanPlayer");
      Class<?>[] param = {String.class};
      Constructor<?> cons = player.getConstructor(param);
      Object[] paramObject = {name};
      Object gameHumanPlayer = cons.newInstance(paramObject);
      return (Player) gameHumanPlayer;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
  }

  public static Player createAIPlayer(String gameType) {
    try {
      Class<?> player = Class.forName("ooga.model.player.aiplayer." + gameType + "AIPlayer");
      Class<?>[] param = {};
      Constructor<?> cons = player.getConstructor(param);
      Object[] paramObject = {};
      Object gameAIPlayer = cons.newInstance(paramObject);
      return (Player) gameAIPlayer;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
  }

  private Player createSecondPlayer(PlayerMode playerMode) {
    if (playerMode.equals(PlayerMode.PLAY_WITH_AI)) {
      return createAIPlayer(gameType);
    }
    //TODO: need to implement the social feature: connect with another human player
    return createHumanPlayer(gameType, "my friend");
  }

  private Player decideWhichPlayerGoFirst(PlayerMode playerMode) {
    if (playerMode.equals(PlayerMode.PLAY_WITH_AI)) {
      return allPlayers.get(0);
    } else {
      //TODO: need to decide which player go first when there are multiple human players
      return allPlayers.get(1);
    }
  }

  private void playerTakeTurn() {
    int currentPlayerIndex = allPlayers.indexOf(currentPlayer);
    if (currentPlayerIndex == allPlayers.size() - 1) {
      currentPlayer = allPlayers.get(0);
    } else {
      currentPlayer = allPlayers.get(currentPlayerIndex + 1);
    }
  }

  public int getCurrentPlayerIndex() {
    return allPlayers.indexOf(currentPlayer) + 1;
  }

  public void updatePieceChosen(Coordinate chosenPieceCoordinate){
    if (chosenPieceCoordinate.xCoordinate() != -1){
      checkBoard.setChosenBlock(chosenPieceCoordinate);
    }
  }

  public List<Coordinate> getPotentialMovePos(){
    return checkBoard.getAvailablePosition(getCurrentPlayerIndex());
  }
}
