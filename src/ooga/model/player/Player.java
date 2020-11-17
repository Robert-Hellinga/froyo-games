package ooga.model.player;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.model.game.Game;

public abstract class Player{

  private String name;
  private int ID;
  private Game myGame;
  private Coordinate nextCoordinate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMyGame(Game game, String gameType){
    this.myGame = game;
  }

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public List<Coordinate> calculateNextCoordinates(){
    return new ArrayList<Coordinate>();
  }

  public void makePlay(Coordinate coordinate){
    System.out.println("makePlay called");
    myGame.play(coordinate);
    System.out.println("Played");
  }

  protected Game getMyGame(){
    return myGame;
  }



}
