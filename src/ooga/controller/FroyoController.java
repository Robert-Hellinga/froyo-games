package ooga.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ooga.controller.GameController.PlayerMode;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.game.Game;
import ooga.view.Display;
import ooga.view.screens.GameScreen;

public class FroyoController implements IFroyoController{

  private Stage myStage;
  private Display myDisplay;
  private Locale myLocale;

  public FroyoController(Stage stage, Locale locale){
    myStage = stage;
    myLocale = locale;
    myDisplay = new Display(this, myLocale);
  }


  @Override
  public void startGame(String gameType, boolean onePlayer, String playerName) {
    Game game = createGame(gameType, playerName, PlayerMode.PLAY_WITH_AI);
    IGameController gameController = new GameController(game);
    GameScreen gameScreen = new GameScreen(myLocale, gameController, game);
    game.registerObserver(gameScreen);
    setNewLayout(gameScreen);
  }

  @Override
  public void setNewLayout(Pane layout) {
    Scene scene = new Scene(layout, layout.getWidth(), layout.getHeight());
    myStage.setScene(scene);
  }

  public static Game createGame(String gameType, String playerName, PlayerMode playerMode){
    try {
      Class<?> game = Class.forName("ooga.model.game." + gameType + "Game");
      Class<?>[] param = {String.class, String.class, PlayerMode.class};
      Constructor<?> cons = game.getConstructor(param);
      Object[] paramObject = {gameType, playerName, playerMode};
      Object gameObject = cons.newInstance(paramObject);
      return (Game) gameObject;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      e.printStackTrace();
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
  }


}
