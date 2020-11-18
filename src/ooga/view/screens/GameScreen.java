package ooga.view.screens;

import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ooga.controller.IFroyoController;
import ooga.controller.IGameController;
import ooga.fileHandler.Resources;
import ooga.model.game.Game;
import ooga.view.ModelObserver;
import ooga.view.Util;
import ooga.view.elements.ButtonGroup;
import ooga.view.elements.GameScreenButtonBox;
import ooga.view.elements.PlayerTurnBox;
import ooga.view.grid.PieceGrid;

public class GameScreen extends GridPane implements ModelObserver {

  private static final String RESOURCE_FILE = "GameScreen";

  private static final String[] CONTROL_BTNS = {"Home", "Save"};

  private static final int CONTROL_BTN_WIDTH = 100;
  private static final int CONTROL_BTN_HEIGHT = 100;
  private static final int CONTROL_BTN_SPACING = 10;
  private static final int CONTROL_BTN_FONT_SIZE = 18;
  private static final int SCREEN_WIDTH = 600;
  private static final int SCREEN_HEIGHT = 600;
  private static final int SCREEN_H_SPACING = 20;
  private static final int SCREEN_V_SPACING = 30;

  private Resources resources;
  private Game game;
  private PieceGrid grid;
  private Locale locale;
  private IGameController gameController;
  private IFroyoController froyoController;

  public GameScreen(Locale locale, IGameController gameController,
      IFroyoController froyoController, Game game) {
    this.game = game;
    this.locale = locale;
    this.gameController = gameController;
    this.froyoController = froyoController;
    resources = new Resources(this.locale, Resources.UI_RESOURCE_PACKAGE, RESOURCE_FILE);
    grid = new PieceGrid(gameController, game.getAllBlockStates());

    setAlignment(Pos.CENTER);
    setWidth(SCREEN_WIDTH);
    setHeight(SCREEN_HEIGHT);
    setVgap(SCREEN_V_SPACING);
    setHgap(SCREEN_H_SPACING);

    add(new PlayerTurnBox(), 1, 0);
    add(getControlButtonGroup(), 0, 1);
    add(grid, 1, 1);
    add(new PlayerTurnBox(), 1, 2);

    Util.applyStyleSheet(this);
  }

  private VBox getControlButtonGroup() {
    VBox result = new VBox();
    result.setSpacing(CONTROL_BTN_SPACING);
    result.setAlignment(Pos.CENTER);

    ButtonGroup controlButtonGroup = new ButtonGroup(
        result,
        resources,
        CONTROL_BTN_WIDTH,
        CONTROL_BTN_HEIGHT,
        CONTROL_BTN_FONT_SIZE
    );

    controlButtonGroup.addButtons(CONTROL_BTNS);
    controlButtonGroup.setOnButtonPushed(0, event -> new SplashScreen(resources.getLocale(), froyoController));
    controlButtonGroup.setOnButtonPushed(1, event -> saveGame());

    return result;
  }

  private void saveGame() {

  }

  @Override
  public void update() {
    grid.update(game.getAllBlockStates());
  }
}
