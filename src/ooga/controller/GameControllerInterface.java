package ooga.controller;

import ooga.Coordinate;

public interface GameControllerInterface {

  void createGame();
  void clickPiece(Coordinate coordinate);
  void saveGame();
  void restartGame();
  void quitGame();

}
