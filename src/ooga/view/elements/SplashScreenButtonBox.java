package ooga.view.elements;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ooga.controller.GameController;
import ooga.model.Game;
import ooga.view.Display;
import ooga.view.screens.GameScreen;

public class SplashScreenButtonBox extends VBox {

  private ResourceBundle resourceBundle;
  private ToggleGroup gameToggleGroup, playerToggleGroup;
  private GameController controller;
  private Display display;

  public SplashScreenButtonBox(ResourceBundle resourceBundle, GameController controller,
      Display display) {
    this.resourceBundle = resourceBundle;
    this.controller = controller;
    this.display = display;
    setSpacing(15);
    getChildren().addAll(getGameButtonGroup(), getPlayerButtonGroup(), getStartButtonGroup());

  }
  private void startGame() {
    Toggle selectedGame = gameToggleGroup.getSelectedToggle();
    Toggle selectedPlayers = playerToggleGroup.getSelectedToggle();

    // controller.setNumPlayers(selectedPlayers);
    // controller.setGame(selectedGame);
    GameScreen screen = new GameScreen(resourceBundle, controller);
    display.setNewLayout(screen);
  }

  private VBox getGameButtonGroup() {
    VBox result = new VBox();
    result.setSpacing(10);
    result.setAlignment(Pos.CENTER);
    gameToggleGroup = new ToggleGroup();
    CustomToggleButton othelloBtn = new CustomToggleButton("Othello", gameToggleGroup);
    CustomToggleButton checkersBtn = new CustomToggleButton("Checkers", gameToggleGroup);
    CustomToggleButton connect4Btn = new CustomToggleButton("Connect 4", gameToggleGroup);
    result.getChildren().addAll(othelloBtn, checkersBtn, connect4Btn);
    return result;
  }

  private HBox getPlayerButtonGroup() {
    HBox result = new HBox();
    result.setSpacing(20);
    result.setAlignment(Pos.CENTER);
    playerToggleGroup = new ToggleGroup();
    CustomToggleButton onePlayerBtn = new CustomToggleButton("One Player", playerToggleGroup, 150
        , 55);
    CustomToggleButton twoPlayerBtn = new CustomToggleButton("Two Player", playerToggleGroup, 150
        , 55);
    result.getChildren().addAll(onePlayerBtn, twoPlayerBtn);
    return result;
  }

  private VBox getStartButtonGroup() {
    VBox result = new VBox();
    result.setAlignment(Pos.CENTER);
    CustomButton startButton = new CustomButton("Start", event -> startGame());
    result.getChildren().add(startButton);
    return result;
  }
}
