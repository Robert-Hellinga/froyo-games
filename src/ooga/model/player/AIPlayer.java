package ooga.model.player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import ooga.Coordinate;
import ooga.Util;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.ai.AIBrain;
import ooga.model.game.Game;

public class AIPlayer extends Player {
  private static final String AI_NAME = "Computer";

  private AIBrain myBrain;

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
    return Util.reflect("ooga.model.ai." + gameType + "AIBrain", List.of(), List.of());
  }

}
