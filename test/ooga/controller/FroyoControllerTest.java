package ooga.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.player.HumanPlayer;
import ooga.view.screens.GameScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class FroyoControllerTest extends DukeApplicationTest {

  private FroyoController froyoController;
  private Stage myStage;


  @Override
  public void start(Stage stage) {
    myStage = stage;
    stage.show();
    froyoController = new FroyoController(stage);

  }

  @Test
  public void testCreatingInvalidGame() {
    assertThrows(ClassOrMethodNotFoundException.class, () ->
    {
      froyoController
          .createGame("Chess", new HumanPlayer("Player One"), new HumanPlayer("Player Two"),
              "default");
    });
  }

  @Test
  public void testSettingLayout() {
    Pane testLayout = new Pane();
    javafxRun(() -> froyoController.setNewLayout(testLayout));
    Scene scene = myStage.getScene();
    Node root = scene.getRoot();
    assertEquals(root, testLayout);

    Pane secondLayout = new GridPane();
    javafxRun(() -> froyoController.setNewLayout(secondLayout));
    scene = myStage.getScene();
    root = scene.getRoot();
    assertNotEquals(root, testLayout);
    assertEquals(root, secondLayout);
  }

  @Test
  // Tests that the scene contains a GameScreen as its root once a game is started.
  public void testStartingLocalGame() {
    javafxRun(
        () -> froyoController.startGame(Locale.ENGLISH, "Checkers", false, "John", false, "Bob"));
    Scene scene = myStage.getScene();
    assertEquals(GameScreen.class, scene.getRoot().getClass());
  }

  @Test
  public void testStartingOnlineGame() {
    javafxRun(() -> froyoController
        .startGame(Locale.ENGLISH, "Checkers", false, "Test P1", true, "Test P2"));
    Scene scene = myStage.getScene();
    assertEquals(GameScreen.class, scene.getRoot().getClass());
  }

}