package ooga.controller;

import ooga.Coordinate;

public interface IGameController {

  /**
   * Performs the necessary actions after a player has clicked on a piece. Can either do nothing,
   * make a piece 'active', or place/move a piece.
   * @param coordinate the location of the piece being clicked
   */
  void clickPiece(Coordinate coordinate);

  /**
   * Prevents or enables a user's clicks to perform actions within the game
   * @param clickingEnabled a boolean of what to set clicking enabled to
   */
  void setClickingEnabled(boolean clickingEnabled);

  /**
   * @return type of the game
   */
  String getGameType();

}
