package ooga.model.player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import ooga.Coordinate;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.ai.AIBrain;
import ooga.model.game.Game;

public class AIPlayer extends Player {
  private static final String AI_NAME = "Computer";

  AIBrain myBrain;

  public AIPlayer(){
    setName(AI_NAME);
  }

  @Override
  public void setMyGame(Game game, String gameType){
    super.setMyGame(game, gameType);
    myBrain = createAIBrain(gameType);

  }


  @Override
  public List<Coordinate> calculateNextCoordinates() {
    Game game = super.getMyGame();
    List<Coordinate> aiMoves = myBrain.decideMove(game.getBoard(), game.getCurrentPlayerIndex());
    return aiMoves;
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

}
