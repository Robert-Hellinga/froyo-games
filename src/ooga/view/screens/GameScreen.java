package ooga.view.screens;

import java.util.Locale;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ooga.controller.IGameController;
import ooga.fileHandler.Resources;
import ooga.model.game.Game;
import ooga.view.GameObserver;
import ooga.view.Styleable;
import ooga.view.Util;
import ooga.view.elements.GameScreenButtonBox;
import ooga.view.grid.PieceGrid;

public class GameScreen extends GridPane implements Styleable, GameObserver {

  private static final String DEFAULT_STYLE_SHEET = "resources/style/default.css";
  private static final String RESOURCE_FILE = "GameScreen";
  private static final int SCREEN_WIDTH = 400;
  private static final int SCREEN_HEIGHT = 350;
  private static final int SCREEN_H_SPACING = 10;
  private static final int SCREEN_V_SPACING = 10;

  private IGameController controller;
  private Resources resources;
  private Game game;
  private PieceGrid grid;
  private Locale locale;


  public GameScreen(Locale locale, IGameController controller, Game game) {
    this.controller = controller;
    this.game = game;
    this.locale = locale;
    resources = new Resources(this.locale, Resources.UI_RESOURCE_PACKAGE, RESOURCE_FILE);
    grid = new PieceGrid(controller, game.getAllBlockStates());

    setStyleSheet(DEFAULT_STYLE_SHEET);
    setWidth(SCREEN_WIDTH);
    setHeight(SCREEN_HEIGHT);
    setVgap(SCREEN_V_SPACING);
    setHgap(SCREEN_H_SPACING);
    
    add(new GameScreenButtonBox(resources, controller), 0, 0);
    add(grid, 1, 0);
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
