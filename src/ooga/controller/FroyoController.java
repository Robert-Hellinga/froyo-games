package ooga.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ooga.controller.GameController.PlayerMode;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.game.Game;
import ooga.view.Display;
import ooga.view.IDisplay;
import ooga.view.screens.GameScreen;

public class FroyoController implements IFroyoController{

  private static final String DEFAULT_RESOURCE_BUNDLE_PATH = "resources.ui.Display_en"; //needed right now to work with GameScreen's current implementation

  private Stage myStage;
  private IDisplay myDisplay;



  public FroyoController(Stage stage){
    myStage = stage;
    myDisplay = new Display(this);
  }


  @Override
  public void startGame(String gameType, boolean onePlayer, String playerName) {
    Game game = createGame(gameType, playerName, PlayerMode.PLAY_WITH_AI);
    IGameController gameController = new GameController(game);
    GameScreen gameScreen = new GameScreen(ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE_PATH), gameController, game);
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
