package ooga.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ooga.model.game.Game;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class FroyoControllerTest extends DukeApplicationTest {

  private IFroyoController froyoController;


  @Override
  public void start(Stage stage){
    stage.show();
    froyoController = new FroyoController(stage);

  }

  @Test
  public void testCreatingAIGame(){

  }

}