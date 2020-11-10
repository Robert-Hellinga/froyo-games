package ooga.controller;

import java.util.Locale;
import javafx.scene.layout.Pane;

public interface IFroyoController {


  void startGame(Locale locale, String gameType, boolean onePlayer, String playerName);
  void setNewLayout(Pane layout);
}
