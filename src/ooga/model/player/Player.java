package ooga.model.player;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.game.Game;

public abstract class Player {

  private String name;
  private Game myGame;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMyGame(Game game, String gameType) {
    this.myGame = game;
  }

  public List<Coordinate> calculateNextCoordinates() {
    return new ArrayList<>();
  }

  public void makePlay(Coordinate coordinate) {
    myGame.play(coordinate);
  }

  protected Game getMyGame() {
    return myGame;
  }


}
