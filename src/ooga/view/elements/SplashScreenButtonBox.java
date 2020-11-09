package ooga.view.elements;

import java.util.Map;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
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
  private static final String CONNECT_4_BTN = "Connect4";
  private static final String ONE_PLAYER_BTN = "OnePlayer";
  private static final String TWO_PLAYER_BTN = "TwoPlayer";
  private static final String START_BTN = "Start";
  private static final String PLAYER_NAME_FIELD = "Username";
  private static final int PLAYER_BTN_WIDTH = 140;
  private static final int PLAYER_BTN_HEIGHT = 50;
  private static final int LARGE_SPACING = 20;
  private static final int MEDIUM_SPACING = 15;
  private static final int SMALL_SPACING = 10;


  private Resources resources;
  private ToggleGroup gameToggleGroup, playerToggleGroup;
  private IFroyoController controller;

  public SplashScreenButtonBox(Resources resources, IFroyoController controller) {
    this.resources = resources;
    this.controller = controller;
    setSpacing(MEDIUM_SPACING);
    getChildren().addAll(getGameButtonGroup(), getPlayerButtonGroup(), getStartButtonGroup());

  }

  private VBox getGameButtonGroup() {
    VBox result = new VBox();
    result.setSpacing(SMALL_SPACING);
    result.setAlignment(Pos.CENTER);
    gameToggleGroup = new ToggleGroup();
    CustomToggleButton othelloBtn = new CustomToggleButton(resources.getString(OTHELLO_BTN),
        gameToggleGroup);
    CustomToggleButton checkersBtn = new CustomToggleButton(resources.getString(CHECKERS_BTN),
        gameToggleGroup);
    CustomToggleButton connect4Btn = new CustomToggleButton(resources.getString(CONNECT_4_BTN),
        gameToggleGroup);
    result.getChildren().addAll(othelloBtn, checkersBtn, connect4Btn);
    return result;
  }

  private HBox getPlayerButtonGroup() {
    HBox result = new HBox();
    result.setSpacing(LARGE_SPACING);
    result.setAlignment(Pos.CENTER);
    playerToggleGroup = new ToggleGroup();
    CustomToggleButton onePlayerBtn = new CustomToggleButton(resources.getString(ONE_PLAYER_BTN),
        playerToggleGroup, PLAYER_BTN_WIDTH
        , PLAYER_BTN_HEIGHT);
    CustomToggleButton twoPlayerBtn = new CustomToggleButton(resources.getString(TWO_PLAYER_BTN),
        playerToggleGroup, PLAYER_BTN_WIDTH
        , PLAYER_BTN_HEIGHT);
    onePlayerBtn.getStyleClass().add("info");
    twoPlayerBtn.getStyleClass().add("info");
    result.getChildren().addAll(onePlayerBtn, twoPlayerBtn);
    return result;
  }

  private HBox getStartButtonGroup() {
    HBox result = new HBox();
    result.setAlignment(Pos.CENTER);
    result.setSpacing(MEDIUM_SPACING);

    TextField playerName = new TextField();
    playerName.setPrefColumnCount(8);
    playerName.setPromptText(resources.getString(PLAYER_NAME_FIELD));
    CustomButton startButton = new CustomButton(resources.getString(START_BTN), event -> startGame());
    startButton.getStyleClass().add("success");
    result.getChildren().addAll(playerName, startButton);
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
