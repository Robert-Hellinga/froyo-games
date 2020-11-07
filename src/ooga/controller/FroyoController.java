package ooga.controller;

import java.awt.Panel;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.GameController.PlayerMode;
import ooga.model.Game;
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
    Game game = new Game(gameType, "Anna", PlayerMode.PLAY_WITH_AI);
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


}
