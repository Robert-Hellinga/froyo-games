package ooga.view.screens;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.Util;
import ooga.controller.IFroyoController;
import ooga.fileHandler.Resources;
import ooga.view.elements.ButtonGroup;
import ooga.view.elements.ImageBox;
import ooga.view.elements.ToggleButtonGroup;

public class SplashScreen extends VBox {

  private static final String BACKGROUND_STYLE_CLASS = "background";
  private static final String RESOURCE_FILE = "SplashScreen";
  private static final String TITLE_IMG_PATH = "resources/ui/game_pieces/img/title.png";
  private static final String START_BTN = "Start";
  private static final String PLAYER_NAME_FIELD = "Username";
  private static final String OPPONENT_NAME = "OpponentNameMessage";
  private static final String NO_NAME_MESSAGE = "NoNameMessage";
  private static final String FIELD_STRING = "Field";
  private static final String INVALID_USERNAME = "InvalidUsername";

  private static final String[] GAME_BUTTONS = {"Othello", "Checkers", "Connect4"};
  private static final String[] PLAYER_BUTTONS = {"OnePlayer", "TwoPlayer", "TwoPlayerOnline"};
  private static final List<Character> INVALID_USERNAME_CHARACTERS = List.of('.', '#', '[', ']');

  private static final int TITLE_IMG_WIDTH = 500; // Shrink to 500px
  private static final int SCREEN_WIDTH = 600;
  private static final int SCREEN_HEIGHT = 450;
  private static final int PLAYER_BTN_WIDTH = 140;
  private static final int PLAYER_BTN_HEIGHT = 50;
  private static final int XL_SPACING = 25;
  private static final int LARGE_SPACING = 20;
  private static final int MEDIUM_SPACING = 15;
  private static final int SMALL_SPACING = 10;
  private static final int USERNAME_TEXT_FIELD_WIDTH = 8;

  private static final Map<Integer, String> gameClasses = Map.of(
      0, "Othello",
      1, "Checkers",
      2, "Connect4"
  );

  private Resources resources;
  private ToggleButtonGroup gameButtonGroup, playerButtonGroup;
  private IFroyoController controller;
  private String opponentName;
  private TextField playerName;

  public SplashScreen(Locale locale, IFroyoController controller) {
    resources = new Resources(locale, Resources.UI_RESOURCE_PACKAGE, RESOURCE_FILE);
    this.controller = controller;
    opponentName = "";

    setAlignment(Pos.CENTER);
    setWidth(SCREEN_WIDTH);
    setHeight(SCREEN_HEIGHT);
    setSpacing(XL_SPACING);

    getChildren().addAll(
        new ImageBox(TITLE_IMG_PATH, TITLE_IMG_WIDTH),
        getGameButtonGroup(),
        getPlayerButtonGroup(),
        getStartButtonGroup()
    );

    controller.setNewLayout(this);
    Util.applyStyleSheet(this);
    getStyleClass().add(BACKGROUND_STYLE_CLASS);
  }

  private VBox getGameButtonGroup() {
    VBox result = new VBox();
    result.setSpacing(SMALL_SPACING);
    result.setAlignment(Pos.CENTER);
    gameButtonGroup = new ToggleButtonGroup(result, resources);
    gameButtonGroup.addButtons(GAME_BUTTONS);

    return result;
  }

  private HBox getPlayerButtonGroup() {
    HBox result = new HBox();
    result.setSpacing(LARGE_SPACING);
    result.setAlignment(Pos.CENTER);

    playerButtonGroup = new ToggleButtonGroup(
        result,
        resources,
        PLAYER_BTN_WIDTH,
        PLAYER_BTN_HEIGHT
    );

    playerButtonGroup.addButtons(PLAYER_BUTTONS);
    playerButtonGroup.setOnButtonsPushed(event -> displayOpponentNameDialog(), 1, 2);
    playerButtonGroup.setButtonStyles(ButtonGroup.INFO_STYLE);

    return result;
  }

  private void displayOpponentNameDialog() {
    TextInputDialog dialog = new TextInputDialog(resources.getString(OPPONENT_NAME));
    dialog.setHeaderText(resources.getString(OPPONENT_NAME));
    dialog.getEditor().setId(OPPONENT_NAME + FIELD_STRING);
    dialog.showAndWait();
    opponentName = dialog.getEditor().getText();
  }

  private boolean checkValidUsername(String username) {
    for (char c : username.toCharArray()) {
      if (INVALID_USERNAME_CHARACTERS.contains(c)) {
        return false;
      }
    }
    return true;
  }

  public String getOpponentName() {
    return opponentName;
  }

  private HBox getStartButtonGroup() {
    HBox result = new HBox();
    result.setAlignment(Pos.CENTER);
    result.setSpacing(MEDIUM_SPACING);

    playerName = new TextField();
    playerName.setPrefColumnCount(USERNAME_TEXT_FIELD_WIDTH);
    playerName.setPromptText(resources.getString(PLAYER_NAME_FIELD));
    playerName.setId(PLAYER_NAME_FIELD + FIELD_STRING);
    result.getChildren().add(playerName);

    ButtonGroup startButton = new ButtonGroup(result, resources);
    startButton.addButtons(START_BTN);
    startButton.setOnButtonPushed(event -> startGame());
    startButton.setButtonStyles(ButtonGroup.SUCCESS_STYLE);

    return result;
  }

  private boolean checkUsernamesValid() {
    if (!checkValidUsername(playerName.getText()) || !checkValidUsername(opponentName)) {
      String errorMessage = String
          .format(resources.getString(INVALID_USERNAME), playerName.getText(), opponentName);
      Alert alert = new Alert(AlertType.ERROR, errorMessage, ButtonType.OK);
      alert.showAndWait();
      playerName.clear();
      return false;
    }
    return true;
  }

  private void startGame() {

    if (!checkUsernamesValid()) {
      return;
    }

    boolean readyToStart =
        gameButtonGroup.hasSelectedToggle() && playerButtonGroup.hasSelectedToggle() && !playerName
            .getText().isEmpty();

    if (readyToStart) {
      String gameType = gameClasses.get(gameButtonGroup.getToggleIndexSelected());
      boolean onePlayer = playerButtonGroup.getToggleIndexSelected() == 0;
      boolean online = playerButtonGroup.getToggleIndexSelected() == 2;
      controller.startGame(resources.getLocale(), gameType, onePlayer, playerName.getText(), online,
          opponentName);
    } else {
      Alert alert = new Alert(AlertType.NONE, resources.getString(NO_NAME_MESSAGE), ButtonType.OK);
      alert.showAndWait();
    }
  }
}
