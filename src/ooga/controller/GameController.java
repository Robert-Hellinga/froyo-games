package ooga.controller;

import ooga.view.Display;

public class GameController implements GameControllerInterface {

  Display myDisplay;


  public enum PlayerMode {
    PLAY_WITH_AI,
    PLAY_WITH_FRIEND
  }

  public GameController() {
    myDisplay = new Display(this);
  }

  @Override
  public void createGame() {

  }

  @Override
  public void clickPiece() {

  }

  @Override
  public void clickBlock() {

  }

  @Override
  public void saveGame() {

  }

  @Override
  public void restartGame() {

  }

  @Override
  public void quitGame() {

  }


}
