package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import javafx.scene.control.ComboBox;
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

  ComboBox dropdown;
  LabeledDropdown labeledDropdown;
  LanguageScreen languageScreen;
  GridPane layoutPane;

  @Override
  public void start(Stage stage) {
    IFroyoController controller = new FroyoController(stage);
    languageScreen = new LanguageScreen(controller);

    dropdown = lookup("#ChooseLanguage/ChoisissezlaLangue/SpracheWahlenDropdown").queryAs(ComboBox.class);
    labeledDropdown = lookup("#ChooseLanguage/ChoisissezlaLangue/SpracheWahlenLabeledDropdown").queryAs(LabeledDropdown.class);

    layoutPane = lookup("#Layout").queryAs(GridPane.class);
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
    assertEquals(labeledDropdown.getSelectedIndex(), 0);
    select(dropdown, "English");
    assertEquals(labeledDropdown.getSelectedIndex(), 1);
    select(dropdown, "German");
    assertEquals(labeledDropdown.getSelectedIndex(), 2);
  }

  @Test
  public void testCreateSplashScreenLocale() {

  }

  @Test
  public void testSplashScreenAlertLocale() {

  }

  @Test
  public void testSplashScreenButtonLanguage() {

  }

  @Test
  public void testGameScreenButtonLanguage() {

  }
}
