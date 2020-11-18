package ooga.view.elements;

import java.util.Map;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.controller.IFroyoController;
import ooga.fileHandler.Resources;

public class SplashScreenButtonBox extends VBox {

  private static final Map<Integer, String> gameClasses = Map
      .of(0, "Othello", 1, "Checkers", 2, "Connect4");
  private static final ButtonFactory BUTTON_FACTORY = new ButtonFactory();


  private static final String[] GAME_BUTTONS = {"Othello", "Checkers", "Connect4"};
  private static final String[] PLAYER_BUTTONS = {"OnePlayer", "TwoPlayer", "TwoPlayerOnline"};

  private static final String START_BTN = "Start";
  private static final String PLAYER_NAME_FIELD = "Username";
  private static final String NO_NAME_MESSAGE = "NoNameMessage";
  private static final String BTN_STRING = "Btn"; // for id's
  private static final String FIELD_STRING = "Field"; // for id's

  private static final int PLAYER_BTN_WIDTH = 140;
  private static final int PLAYER_BTN_HEIGHT = 50;
  private static final int LARGE_SPACING = 20;
  private static final int MEDIUM_SPACING = 15;
  private static final int SMALL_SPACING = 10;

  private Resources resources;
  private ToggleButtonGroup gameButtonGroup, playerButtonGroup;
  private IFroyoController controller;
  private String opponentName;

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
    gameButtonGroup = new ToggleButtonGroup(
        result,
        resources,
        14,
        GAME_BUTTONS
    );

//    connect4Btn.setId(CONNECT_4_BTN + BTN_STRING);

    return result;
  }

  private HBox getPlayerButtonGroup() {
    HBox result = new HBox();
    result.setSpacing(LARGE_SPACING);
    result.setAlignment(Pos.CENTER);

    playerButtonGroup = new ToggleButtonGroup(result,
        resources,
        PLAYER_BTN_WIDTH,
        PLAYER_BTN_HEIGHT,
        14,
        PLAYER_BUTTONS
    );

    playerButtonGroup.setOnButtonPushed(2, event -> getOpponentName());

    playerButtonGroup.setButtonStyles(BUTTON_FACTORY.INFO_STYLE);
//    twoPlayerBtn.setId(TWO_PLAYER_BTN + BTN_STRING);

    return result;
  }

  private void getOpponentName() {
    TextInputDialog dialog = new TextInputDialog("Opponent Name");
    dialog.setHeaderText("Enter Opponent Name");
    dialog.showAndWait();
    opponentName = dialog.getEditor().getText();
  }

  private HBox getStartButtonGroup() {
    HBox result = new HBox();
    result.setAlignment(Pos.CENTER);
    result.setSpacing(MEDIUM_SPACING);

    TextField playerName = new TextField();
    playerName.setId(PLAYER_NAME_FIELD + FIELD_STRING);
    playerName.setPrefColumnCount(8);
    playerName.setPromptText(resources.getString(PLAYER_NAME_FIELD));
    Button startButton = BUTTON_FACTORY.makeButton(resources.getString(START_BTN),
        event -> startGame(playerName.getText()));
    startButton.setId(START_BTN + BTN_STRING);

    startButton.getStyleClass().add(BUTTON_FACTORY.SUCCESS_STYLE);
    result.getChildren().addAll(playerName, startButton);
    return result;
  }

  private void startGame(String username) {
    if (buttonsAreSelected()) {
      if (username.isEmpty()) {
        noNamePopUp();
      } else {
        String gameType = gameClasses.get(gameButtonGroup.getToggleIndexSelected());
        boolean onePlayer = playerButtonGroup.getToggleIndexSelected() == 0;
        boolean online = playerButtonGroup.getToggleIndexSelected() == 2;
        controller.startGame(resources.getLocale(), gameType, onePlayer, username, online, opponentName);
      }
    }
  }

  private void noNamePopUp() {
    Alert alert = new Alert(AlertType.NONE, resources.getString(NO_NAME_MESSAGE), ButtonType.OK);
    alert.showAndWait();
  }

  private boolean buttonsAreSelected() {
    return gameButtonGroup.hasSelectedToggle() && playerButtonGroup.hasSelectedToggle();
  }
}
