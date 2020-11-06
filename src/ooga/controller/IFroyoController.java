package ooga.controller;

import javafx.scene.layout.Pane;

public interface IFroyoController {


  void startGame(String gameType, boolean onePlayer, String playerName);
  void setNewLayout(Pane layout);
}
