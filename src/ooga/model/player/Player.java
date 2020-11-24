package ooga.model.player;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.game.Game;

/**
 * Represents a player that can play a game
 */
public abstract class Player {

  private String name;
  private Game myGame;

  /**
   * @return the player's name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the player's name to name
   *
   * @param name the player's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the game that the player is playing
   *
   * @param game     the game to be played
   * @param gameType the type of game being played
   */
  public void setMyGame(Game game, String gameType) {
    this.myGame = game;
  }

  /**
   * Calculates the next moves to be played (used by AI player)
   *
   * @return a list of coordinates to be played
   */
  public List<Coordinate> calculateNextCoordinates() {
    return new ArrayList<>();
  }

  /**
   * Makes a move on the game based on the given coordinate
   *
   * @param coordinate the location of the play to be made
   */
  public void makePlay(Coordinate coordinate) {
    myGame.play(coordinate);
  }

  /**
   * @return the player's current game
   */
  protected Game getMyGame() {
    return myGame;
  }

}
