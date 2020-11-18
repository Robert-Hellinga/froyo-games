package ooga.controller;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class FroyoControllerTest extends DukeApplicationTest {

  private IFroyoController froyoController;


  @Override
  public void start(Stage stage) {
    stage.show();
    froyoController = new FroyoController(stage);

  }

  @Test
  public void testCreatingAIGame() {

  }

}