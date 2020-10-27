package Game;

import java.util.*;

/**
 *
 */
public interface Playable {

  public void startGame();

  public void saveGame();

  public void addObserver(Observer observer);

}