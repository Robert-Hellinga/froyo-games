package ooga.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ooga.controller.GameController.PlayerMode;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.fileHandler.Database;
import ooga.model.game.Game;
import ooga.model.player.AIPlayer;
import ooga.model.player.HumanPlayer;
import ooga.model.player.Player;
import ooga.view.screens.GameScreen;

public class FroyoController implements IFroyoController{

  private Stage myStage;
  private Database database;

  public FroyoController(Stage stage){
    myStage = stage;
  }

  @Override
  public void startGame(Locale locale, String gameType, boolean onePlayer, String playerName,
      boolean online, String opponentName) {
    Player userPlayer = new HumanPlayer(playerName);
    Player secondPlayer = createSecondPlayer(onePlayer, opponentName);

    Game game = createGame(gameType, userPlayer, secondPlayer, "default");
    userPlayer.setMyGame(game, gameType);
    secondPlayer.setMyGame(game, gameType);

    if(online) {
      database = new Database(userPlayer, secondPlayer, game);
      database.addNewGame();
    }

    IGameController gameController = new GameController(game, onePlayer ? PlayerMode.PLAY_WITH_AI : PlayerMode.PLAY_WITH_FRIEND);

    GameScreen gameScreen = new GameScreen(locale, gameController, this, game);
    game.registerObserver(gameScreen);
    setNewLayout(gameScreen);
  }



  private Player createSecondPlayer(boolean onePlayer, String name) {
    name = name == null ? "Player 2" : name;
    if(onePlayer){
      return new AIPlayer();
    }
    else{
      return new HumanPlayer(name);
    }
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
    try {
      Class<?> game = Class.forName("ooga.model.game." + gameType + "Game");
      Class<?>[] param = {String.class, Player.class, Player.class, String.class};
      Constructor<?> cons = game.getConstructor(param);
      Object[] paramObject = {gameType, playerOne, playerTwo, startPattern};
      Object gameObject = cons.newInstance(paramObject);
      return (Game) gameObject;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      e.printStackTrace();
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
  }


}
