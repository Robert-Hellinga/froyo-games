package Game;

public interface GridGame extends Game.Playable {


  public void checkForWin();

  public void takeTurn();

  public void notifyObservers();


}