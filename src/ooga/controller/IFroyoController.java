package ooga.controller;

import java.util.Locale;
import javafx.scene.layout.Pane;

public interface IFroyoController {

  /**
   * Creates and displays a new game based on the given parameters
   * @param locale the locale for determining the language of the text on GameScreen buttons
   * @param gameType the type of game to create
   * @param onePlayer whether or not the game is with one player or two, creates an AI player if true
   * @param playerName the name of the player creating the game, used to create the Player object
   * @param online whether or not the game is online, if true does necessary actions to create
   *               an game connected to database
   * @param opponentName the second player's name
   */
  void startGame(Locale locale, String gameType, boolean onePlayer, String playerName,
      boolean online, String opponentName);

  /**
   * Sets the scene of the current stage to be a new scene containing layout as its root
   * @param layout a pane that will be displayed
   */
  void setNewLayout(Pane layout);
}
