package ooga.controller;

import ooga.Coordinate;
import ooga.view.grid.PieceGrid;

public interface GameControllerInterface {

  void createGame();
  void clickPiece(Coordinate coordinate, PieceGrid grid);
  void saveGame();
  void restartGame();
  void quitGame();

}
