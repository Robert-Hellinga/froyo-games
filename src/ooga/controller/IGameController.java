package ooga.controller;

import ooga.Coordinate;
import ooga.view.grid.PieceGrid;

public interface IGameController {

  void createGame();
  void clickPiece(Coordinate coordinate);
  void saveGame();
  void restartGame();
  void quitGame();

}