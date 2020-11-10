package ooga.view;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.controller.FroyoController;
import ooga.controller.IFroyoController;
import ooga.view.screens.LanguageScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class LanguageTest extends DukeApplicationTest {

  @Override
  public void start(Stage stage) {
    IFroyoController controller = new FroyoController(stage);
    LanguageScreen startScreen = new LanguageScreen(controller);
  }

  @Test
  public void testWindowPosition() {
//    Button pause = lookup("#PauseButton").query();
//    clickOn(pause);
//    assertTrue(true);
  }

  @Test
  public void testDropdownSelectLocale() {

  }

  @Test
  public void testDropdownCorrectLocaleLabels() {

  }

  @Test
  public void testCreateSplashScreenLocale() {

  }

  @Test
  public void testSplashScreenButtonLanguage() {

  }

  @Test
  public void testGameScreenButtonLanguage() {

  }
}
