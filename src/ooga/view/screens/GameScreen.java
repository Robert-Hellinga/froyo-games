package ooga.view.screens;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ooga.controller.IGameController;
import ooga.model.game.Game;
import ooga.view.GameObserver;
import ooga.view.Styleable;
import ooga.view.Util;
import ooga.view.elements.GameScreenButtonBox;
import ooga.view.grid.PieceGrid;

public class GameScreen extends GridPane implements Styleable, GameObserver {

  private static final int WIDTH = 400;
  private static final int HEIGHT = 350;
  private static final String DEFAULT_STYLE_SHEET = "resources/style/default.css";

  private IGameController controller;
  private ResourceBundle resourceBundle;
  private Game game;
  private PieceGrid myGrid;

  public GameScreen(ResourceBundle resourceBundle, IGameController controller, Game game) {
    this.controller = controller;
    this.resourceBundle = resourceBundle;
    this.game = game;
    myGrid = makePieceGrid();

    setStyleSheet(DEFAULT_STYLE_SHEET);
    setWidth(WIDTH);
    setHeight(HEIGHT);
    
    add(makeGameButtons(), 0, 0);
    add(myGrid, 1, 0);
  }

  private PieceGrid makePieceGrid() {
    return new PieceGrid(controller, game.getAllBlockStates());
  }

  private VBox makeGameButtons() {
    return new GameScreenButtonBox(resourceBundle);
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
    myGrid.updateGrid(game.getAllBlockStates());
  }
}
