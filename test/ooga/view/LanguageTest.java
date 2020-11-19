package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ooga.controller.FroyoController;
import ooga.controller.IFroyoController;
import ooga.view.elements.LabeledDropdown;
import ooga.view.screens.LanguageScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class LanguageTest extends DukeApplicationTest {

  private ComboBox dropdown;
  private LabeledDropdown labeledDropdown;
  private LanguageScreen languageScreen;
  private GridPane layoutPane;
  private Button startBtn;

  @Override
  public void start(Stage stage) {
    IFroyoController controller = new FroyoController(stage);
    languageScreen = new LanguageScreen(controller);

    dropdown = lookup("#ChooseLanguage/ChoisissezlaLangue/SpracheWahlenDropdown").queryAs(ComboBox.class);
    labeledDropdown = lookup("#ChooseLanguage/ChoisissezlaLangue/SpracheWahlenLabeledDropdown").queryAs(LabeledDropdown.class);

    layoutPane = lookup("#Layout").queryAs(GridPane.class);

    startBtn = lookup("#Start/Debut/StartBtn").queryAs(Button.class);
//    othelloBtn = lookup("#OthelloBtn").queryAs(ToggleButton.class);
//    checkersBtn = lookup("#CheckersBtn").queryAs(ToggleButton.class);
//    connect4Btn = lookup("#Connect4Btn").queryAs(ToggleButton.class);
//    onePlayerBtn = lookup("#OnePlayerBtn").queryAs(ToggleButton.class);
//    twoPlayerBtn = lookup("#TwoPlayerBtn").queryAs(ToggleButton.class);
//    twoPlayerOnlineBtn = lookup("#TwoPlayer(Online)Btn").queryAs(ToggleButton.class);
  }

  @Test
  public void testWindowSize() {
    assertEquals(layoutPane.getWidth(), 450);
    assertEquals(layoutPane.getHeight(), 150);
  }

  @Test
  public void testDropdownSelectLocaleLabels() {
    select(dropdown, "French");
    assertEquals(labeledDropdown.getValue(), "French");
    select(dropdown, "English");
    assertEquals(labeledDropdown.getValue(), "English");
    select(dropdown, "German");
    assertEquals(labeledDropdown.getValue(), "German");
  }

  @Test
  public void testSelectedLocaleIndex() {
    select(dropdown, "French");
    assertEquals(labeledDropdown.getSelectedIndex(), 1);
    select(dropdown, "English");
    assertEquals(labeledDropdown.getSelectedIndex(), 0);
    select(dropdown, "German");
    assertEquals(labeledDropdown.getSelectedIndex(), 2);
  }

  @Test
  public void testSplashScreenButtonLanguage() {
    select(dropdown, "German");
    clickOn(startBtn);

    ToggleButton twoPlayerOnlineBtnGerman = lookup("#ZweiSpieler(Online)Btn").queryAs(ToggleButton.class);
    assertEquals(twoPlayerOnlineBtnGerman.getText(), "Zwei Spieler (Online)");

    Button startGameBtn = lookup("#SpielStartenBtn").queryAs(Button.class);
    assertEquals(startGameBtn.getText(), "Spiel Starten");
  }

  @Test
  public void testSplashScreenAlertLocale() {
    select(dropdown, "French");
    clickOn(startBtn);

    ToggleButton twoPlayerOnlineBtnFrench = lookup("#DeuxJoueurs(Enligne)Btn").queryAs(ToggleButton.class);

    clickOn(twoPlayerOnlineBtnFrench);
    writeInputsToDialog(".");

    TextField nameField = lookup("#UsernameField").queryAs(TextField.class);
    clickOn(nameField).write(".");
    Button startGameBtn = lookup("#DemarrerJeuBtn").queryAs(Button.class);
    clickOn(startGameBtn);
    assertEquals(getDialogMessage(), "\".\" ou \".\" contient l'un des elements suivants: '.', '#', '[', ']'. Veuillez fournir un nom d'utilisateur different");
  }

  @Test
  public void testGameScreenButtonLanguage() {
    select(dropdown, "French");
    clickOn(startBtn);
    ToggleButton twoPlayerBtnFrench = lookup("#DeuxJoueursBtn").queryAs(ToggleButton.class);
    clickOn(twoPlayerBtnFrench);
    ToggleButton checkersBtn = lookup("#CheckersBtn").queryAs(ToggleButton.class);
    clickOn(checkersBtn);
    TextField nameField = lookup("#UsernameField").queryAs(TextField.class);
    getDialogMessage();
    clickOn(nameField).write("John");
    Button startGameBtn = lookup("#DemarrerJeuBtn").queryAs(Button.class);
    clickOn(startGameBtn);

    Button homeBtn = lookup("#DomicileBtn").queryAs(Button.class);
    assertEquals(homeBtn.getText(), "Domicile");
  }
}
