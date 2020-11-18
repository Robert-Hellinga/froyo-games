package ooga.view.screens;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Locale;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import ooga.controller.FroyoController;
import ooga.controller.IFroyoController;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class SplashScreenTest extends DukeApplicationTest {

  private IFroyoController controller;
  private SplashScreen splashScreen;
  private Stage stage;

  private ToggleButton othelloBtn;
  private ToggleButton checkersBtn;
  private ToggleButton connect4Btn;
  private ToggleButton onePlayerBtn;
  private ToggleButton twoPlayerBtn;
  private ToggleButton twoPlayerOnlineBtn;
  private ToggleGroup gameToggle;
  private ToggleGroup playerToggle;
  private TextField nameField;
  private Button startBtn;


  @Override
  public void start(Stage stage) {
    this.stage = stage;
    controller = new FroyoController(stage);
    splashScreen = new SplashScreen(Locale.ENGLISH, controller);

    othelloBtn = lookup("#OthelloBtn").queryAs(ToggleButton.class);
    checkersBtn = lookup("#CheckersBtn").queryAs(ToggleButton.class);
    connect4Btn = lookup("#Connect4Btn").queryAs(ToggleButton.class);
    gameToggle = othelloBtn.getToggleGroup();

    onePlayerBtn = lookup("#OnePlayerBtn").queryAs(ToggleButton.class);
    twoPlayerBtn = lookup("#TwoPlayerBtn").queryAs(ToggleButton.class);
    twoPlayerOnlineBtn = lookup("#TwoPlayer(Online)Btn").queryAs(ToggleButton.class);
    playerToggle = onePlayerBtn.getToggleGroup();

    nameField = lookup("#UsernameField").queryAs(TextField.class);

    startBtn = lookup("#StartGameBtn").queryAs(Button.class);

  }

  @Test
  public void testGameSelected() {
    assertEquals(gameToggle.getSelectedToggle(), null);
    clickOn(othelloBtn);
    assertEquals(gameToggle.getSelectedToggle(), othelloBtn);
    clickOn(checkersBtn);
    assertEquals(gameToggle.getSelectedToggle(), checkersBtn);
    assertFalse(othelloBtn.isSelected());
    clickOn(connect4Btn);
    assertEquals(gameToggle.getSelectedToggle(), connect4Btn);
    assertFalse(othelloBtn.isSelected());
    assertFalse(checkersBtn.isSelected());
  }

  @Test
  public void testPlayerTypeSelected() {
    assertEquals(playerToggle.getSelectedToggle(), null);
    clickOn(onePlayerBtn);
    assertEquals(playerToggle.getSelectedToggle(), onePlayerBtn);
    clickOn(twoPlayerBtn);
    assertEquals(playerToggle.getSelectedToggle(), twoPlayerBtn);
    clickOn(twoPlayerOnlineBtn);
    assertEquals(playerToggle.getSelectedToggle(), twoPlayerOnlineBtn);
  }


  // tests for game not starting because no name is given
  @Test
  public void testNotStartingGameName() {
    clickOn(checkersBtn);
    clickOn(onePlayerBtn);
    clickOn(startBtn);
    assertEquals("Please enter a name and select buttons.", getDialogMessage());
  }

  @Test
  public void testEnteringUsername(){
    clickOn(checkersBtn);
    clickOn(onePlayerBtn);
    String expected = "John";
    clickOn(nameField).write(expected);
    clickOn(startBtn);
    assertEquals(nameField.getText(), expected);
  }


}

