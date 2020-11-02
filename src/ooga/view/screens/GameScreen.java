package ooga.view.screens;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import ooga.controller.GameController;
import ooga.view.Styleable;
import ooga.view.Util;
import ooga.view.elements.piecegrid.PieceGrid;

public class GameScreen extends GridPane implements Styleable {

  private static final int WIDTH = 400;
  private static final int HEIGHT = 350;
  private static final String DEFAULT_STYLE_SHEET = "style/default.css";

  public GameScreen(ResourceBundle resourceBundle, GameController controller, Group game) {
    setStyleSheet(DEFAULT_STYLE_SHEET);
    setWidth(WIDTH);
    setHeight(HEIGHT);
    add(game, 0, 0);
  }

  @Override
  public void setStyleSheet(String stylesheet) {
    getStylesheets().clear();
    Util.setPaneStylesheet(this, stylesheet);
  }

  @Override
  public String getStyleSheet() {
    return getStylesheets().get(0);
  }
}
