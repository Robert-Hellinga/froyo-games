package ooga.model.player;

import java.util.List;
import ooga.Coordinate;
import ooga.Util;
import ooga.model.ai.AIBrain;
import ooga.model.game.Game;

/**
 * An AI player that has a 'brain' used to calculate moves based on the current game state
 */
public class AIPlayer extends Player {

  private static final String AI_NAME = "Computer";

  private AIBrain myBrain;

  /**
   * Creates an AI player with AI_NAME as its name.
   */
  public AIPlayer() {
    setName(AI_NAME);
  }

  /**
   * Sets the AI player's game and creates the AI brain based on the type of game.
   * @param game the game to be played
   * @param gameType the type of game being played
   */
  @Override
  public void setMyGame(Game game, String gameType) {
    super.setMyGame(game, gameType);
    myBrain = createAIBrain(gameType);

  }

  /**
   * Calculates the AI player's moves (multiple if checkers) based on the current game state
   * @return a list of coordinates to be played
   */
  @Override
  public List<Coordinate> calculateNextCoordinates() {
    Game game = super.getMyGame();
    return myBrain.decideMove(game.getBoard(), game.getCurrentPlayerIndex());
  }

  /**
   * Uses reflection to create an AI brain based on the game type
   * @param gameType the type of game
   * @return an AI brain based on the game type
   */
  public static AIBrain createAIBrain(String gameType) {
    return Util.reflect("ooga.model.ai." + gameType + "AIBrain", List.of(), List.of());
  }

}
