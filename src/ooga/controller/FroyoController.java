package ooga.controller;

import java.util.List;
import java.util.Locale;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ooga.Util;
import ooga.controller.GameController.PlayerMode;
import ooga.model.game.Game;
import ooga.model.player.AIPlayer;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import ooga.view.screens.GameScreen;

/**
 * A general control responsible for the overall interaction of the view and model elements. Used
 * to create and display games.
 *
 * @author Lucas Carter (with additions by teammates)
 */
public class FroyoController implements IFroyoController {

  private static final String DEFAULT_OPPONENT_NAME = "Player 2";

  private Stage myStage;
  private SocialController socialController;

  /**
   * Creates a FroyoController
   * @param stage a stage that gets stored as a local variable
   */
  public FroyoController(Stage stage) {
    myStage = stage;
  }

  /**
   * @see IFroyoController#startGame(Locale, String, boolean, String, boolean, String)
   */
  @Override
  public void startGame(Locale locale, String gameType, boolean onePlayer, String playerName,
      boolean online, String opponentName) {
    Player userPlayer = new HumanPlayer(playerName);
    Player secondPlayer = createSecondPlayer(onePlayer, opponentName);

    Game game = createGame(gameType, userPlayer, secondPlayer, "default");
    userPlayer.setMyGame(game, gameType);
    secondPlayer.setMyGame(game, gameType);

    IGameController gameController = new GameController(game,
        onePlayer ? PlayerMode.PLAY_WITH_AI : PlayerMode.PLAY_WITH_FRIEND);

    GameScreen gameScreen = new GameScreen(locale, gameController, this, game);
    game.registerObserver(gameScreen);
    setNewLayout(gameScreen);

    if (online) {
      socialController = new SocialController(userPlayer, secondPlayer, gameController, game);
      socialController.joinGame();
      game.setDatabase(socialController);
    }
  }

  /**
   * Creates the second player to be passed to the game. Creates either an AI player or another
   * human one.
   *
   * @param onePlayer a boolean that determines whether an AI or Human player is created
   * @param name the name of the player being created, only used if Human player
   * @return
   */
  private Player createSecondPlayer(boolean onePlayer, String name) {
    name = name == null ? DEFAULT_OPPONENT_NAME : name;
    return onePlayer ? new AIPlayer() : new HumanPlayer(name);
  }

  /**
   * @see IFroyoController#setNewLayout(Pane)
   */
  @Override
  public void setNewLayout(Pane layout) {
    Scene scene = new Scene(layout, layout.getWidth(), layout.getHeight());
    myStage.setScene(scene);
    myStage.hide();
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    myStage.setX((screenBounds.getWidth() - layout.getWidth()) / 2);
    myStage.setY((screenBounds.getHeight() - layout.getHeight()) / 2);
    myStage.show();
  }

  /**
   * Uses reflection to create a game with two players.
   *
   * @param gameType the type of game (either Checkers, Othello, or Connect4)
   * @param playerOne the user's player object
   * @param playerTwo the opponent's player object
   * @param startPattern the initial configuration of the board/pieces
   * @return a new Game
   */
  public static Game createGame(String gameType, Player playerOne, Player playerTwo,
      String startPattern) {
    return Util.reflect(
        "ooga.model.game." + gameType + "Game",
        List.of(String.class, Player.class, Player.class, String.class),
        List.of(gameType, playerOne, playerTwo, startPattern)
    );
  }
}
