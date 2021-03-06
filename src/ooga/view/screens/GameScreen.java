package ooga.view.screens;

import java.util.List;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ooga.Util;
import ooga.controller.IFroyoController;
import ooga.controller.IGameController;
import ooga.fileHandler.Resources;
import ooga.model.game.Game;
import ooga.model.player.Player;
import ooga.view.ModelObserver;
import ooga.view.elements.ButtonGroup;
import ooga.view.elements.PlayerTurnBox;
import ooga.view.grid.PieceGrid;

/**
 * GameScreen class containing display elements for playing the game
 * @author Nate Mela (nrm27)
 */
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
  private static final int PLAYER_ONE_COLOR_KEY = 1;
  private static final int PLAYER_TWO_COLOR_KEY = 2;

  private Resources resources;
  private Game game;
  private PieceGrid grid;
  private Locale locale;
  private IFroyoController froyoController;

  public GameScreen(Locale locale, IGameController gameController,
      IFroyoController froyoController, Game game) {
    this.game = game;
    this.locale = locale;
    this.froyoController = froyoController;
    resources = new Resources(this.locale, Resources.UI_RESOURCE_PACKAGE, RESOURCE_FILE);
    grid = new PieceGrid(gameController, game.getAllBlockStates());

    setAlignment(Pos.CENTER);
    setWidth(SCREEN_WIDTH);
    setHeight(SCREEN_HEIGHT);
    setVgap(SCREEN_V_SPACING);
    setHgap(SCREEN_H_SPACING);

    add(new PlayerTurnBox(getPlayerName(0), PLAYER_ONE_COLOR_KEY, game.getGameType()), 1, 0);
    add(getControlButtonGroup(), 0, 1);
    add(grid, 1, 1);
    add(new PlayerTurnBox(getPlayerName(1), PLAYER_TWO_COLOR_KEY, game.getGameType()), 1, 2);

    Util.applyStyleSheet(this);
  }


  private String getPlayerName(int index) {
    List<Player> players = game.getAllPlayers();
    Player player = players.get(index);
    return player.getName();
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
    controlButtonGroup
        .setOnButtonPushed(event -> new SplashScreen(resources.getLocale(), froyoController), 0);
    controlButtonGroup.setOnButtonPushed(event -> saveGame(), 1);

    return result;
  }

  /**
   * Method called when save game button is pressed. Currently not implemented
   */
  private void saveGame() {
    // Save game feature was not implemented
    throw new UnsupportedOperationException();
  }

  /**
   * Method called periodically by model observer, updates grid with new block states
   */
  @Override
  public void update() {
    grid.update(game.getAllBlockStates());
  }
}
