package ooga.view.screens;

import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ooga.controller.IFroyoController;
import ooga.controller.IGameController;
import ooga.fileHandler.Resources;
import ooga.model.game.Game;
import ooga.model.player.Player;
import ooga.view.GameObserver;
import ooga.view.Styleable;
import ooga.view.Util;
import ooga.view.elements.GameScreenButtonBox;
import ooga.view.elements.PlayerTurnBox;
import ooga.view.grid.PieceGrid;

public class GameScreen extends GridPane implements Styleable, GameObserver {

  private static final String DEFAULT_STYLE_SHEET = "resources/style/default.css";
  private static final String RESOURCE_FILE = "GameScreen";
  private static final int SCREEN_WIDTH = 450;
  private static final int SCREEN_HEIGHT = 500;
  private static final int SCREEN_H_SPACING = 20;
  private static final int SCREEN_V_SPACING = 30;

  private Resources resources;
  private Game game;
  private PieceGrid grid;
  private Locale locale;

  public GameScreen(Locale locale, IGameController gameController,
      IFroyoController froyoController, Game game) {
    this.game = game;
    this.locale = locale;
    resources = new Resources(this.locale, Resources.UI_RESOURCE_PACKAGE, RESOURCE_FILE);
    grid = new PieceGrid(gameController, game.getAllBlockStates());

    setAlignment(Pos.CENTER);
    setStyleSheet(DEFAULT_STYLE_SHEET);
    setWidth(SCREEN_WIDTH);
    setHeight(SCREEN_HEIGHT);
    setVgap(SCREEN_V_SPACING);
    setHgap(SCREEN_H_SPACING);

    add(new PlayerTurnBox(), 1, 0);
    add(new GameScreenButtonBox(resources, froyoController), 0, 1);
    add(grid, 1, 1);
    add(new PlayerTurnBox(), 1, 2);
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

  @Override
  public void update() {
    grid.update(game.getAllBlockStates());
  }
}
