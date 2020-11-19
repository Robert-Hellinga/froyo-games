package ooga.controller;

import ooga.Coordinate;

public interface IGameController {

  void clickPiece(Coordinate coordinate);

  void setClickingEnabled(boolean clickingEnabled);

  String getGameType();

}
