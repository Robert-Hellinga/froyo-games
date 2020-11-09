package ooga.view.elements;

import java.util.Map;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.controller.IFroyoController;
import ooga.fileHandler.Resources;
import ooga.view.Display;
import ooga.view.screens.GameScreen;

public class SplashScreenButtonBox extends VBox {

  private static final Map<Integer, String> gameClasses = Map.of(0, "Othello", 1, "Checkers", 2, "Connect4");
  private static final String OTHELLO_BTN = "Othello";
  private static final String CHECKERS_BTN = "Checkers";
  private static final String CONNECT_4_BTN = "Connect 4";
  private static final String ONE_PLAYER_BTN = "One Player";
  private static final String TWO_PLAYER_BTN = "Two Player";
  private static final String START_BTN = "Two Player";
  private static final int GAME_BTN_WIDTH = 150;
  private static final int GAME_BTN_HEIGHT = 55;


  private Resources resources;
  private ToggleGroup gameToggleGroup, playerToggleGroup;
  private IFroyoController controller;

  public SplashScreenButtonBox(Resources resources, IFroyoController controller) {
    this.resources = resources;
    this.controller = controller;
    setSpacing(15);
    getChildren().addAll(getGameButtonGroup(), getPlayerButtonGroup(), getStartButtonGroup());

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

  private void startGame() {
    String gameType = gameClasses.get(getToggleIndexSelected(gameToggleGroup));
    boolean onePlayer = getToggleIndexSelected(gameToggleGroup) == 0 ? true : false;
    controller.startGame(gameType, onePlayer, null);
  }

  private int getToggleIndexSelected(ToggleGroup group) {
    ObservableList<Toggle> toggles = group.getToggles();
    for(int i = 0; i < toggles.size(); i++) {
      if(toggles.get(i).isSelected()) {
        System.out.println(i);
        return i;
      }
    }
    return -1; // No toggle selected
  }
}
