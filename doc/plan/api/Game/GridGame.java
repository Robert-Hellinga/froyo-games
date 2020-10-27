package Game;

public interface GridGame extends Playable {


  public void checkForWin();

  public void takeTurn();

  public void notifyObservers();

  public void startGame();

  public void saveGame();

  public void addObserver(? observer);

}