package ooga.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ooga.Util;
import ooga.controller.GameController.PlayerMode;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.game.Game;
import ooga.model.player.AIPlayer;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import ooga.view.screens.GameScreen;

public class FroyoController implements IFroyoController{

  private static final String DEFAULT_OPPONENT_NAME = "Player 2";

  private Stage myStage;
  private SocialController socialController;

  public FroyoController(Stage stage){
    myStage = stage;
  }

  @Override
  public void startGame(Locale locale, String gameType, boolean onePlayer, String playerName, boolean online, String opponentName) {
    Player userPlayer = new HumanPlayer(playerName);
    Player secondPlayer = createSecondPlayer(onePlayer, opponentName);

    Game game = createGame(gameType, userPlayer, secondPlayer, "default");
    userPlayer.setMyGame(game, gameType);
    secondPlayer.setMyGame(game, gameType);

    IGameController gameController = new GameController(game, onePlayer ? PlayerMode.PLAY_WITH_AI : PlayerMode.PLAY_WITH_FRIEND);

    GameScreen gameScreen = new GameScreen(locale, gameController, this, game);
    game.registerObserver(gameScreen);
    setNewLayout(gameScreen);

    if(online) {
      socialController = new SocialController(userPlayer, secondPlayer, gameController, game);
      socialController.joinGame();
      game.setDatabase(socialController);
    }
  }



  private Player createSecondPlayer(boolean onePlayer, String name) {
    name = name == null ? DEFAULT_OPPONENT_NAME : name;
    return onePlayer ? new AIPlayer() : new HumanPlayer(name);
  }

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

  public static Game createGame(String gameType, Player playerOne, Player playerTwo, String startPattern){
    return Util.reflect(
        "ooga.model.game." + gameType + "Game",
        List.of(String.class, Player.class, Player.class, String.class),
        List.of(gameType, playerOne, playerTwo, startPattern)
    );
  }
}
