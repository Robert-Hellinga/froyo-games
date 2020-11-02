package ooga.view.elements;

import java.lang.ModuleLayer.Controller;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.controller.GridGameController;
import ooga.view.Display;

public class SplashScreenButtonBox extends VBox {

  private ResourceBundle resourceBundle;
  private ToggleGroup gameToggleGroup, playerToggleGroup;

  public SplashScreenButtonBox(ResourceBundle resourceBundle, GridGameController controller, Display display) {
    this.resourceBundle = resourceBundle;
    setSpacing(15);

    getChildren().addAll(getGameButtonGroup(), getPlayerButtonGroup());

  }

  private VBox getGameButtonGroup() {
    VBox result = new VBox();
    result.setSpacing(10);
    result.setAlignment(Pos.CENTER);
    gameToggleGroup = new ToggleGroup();
    CustomToggleButton othelloBtn = new CustomToggleButton("Othello", gameToggleGroup);
    CustomToggleButton checkersBtn = new CustomToggleButton("Checkers", gameToggleGroup);
    CustomToggleButton connect4Btn = new CustomToggleButton("Connect 4", gameToggleGroup);
    result.getChildren().addAll(othelloBtn, checkersBtn, connect4Btn);
    return result;
  }

  private HBox getPlayerButtonGroup() {
    HBox result = new HBox();
    result.setSpacing(20);
    result.setAlignment(Pos.CENTER);
    playerToggleGroup = new ToggleGroup();
    CustomToggleButton onePlayerBtn = new CustomToggleButton("One Player", playerToggleGroup, 150
        , 60);
    CustomToggleButton twoPlayerBtn = new CustomToggleButton("Two Player", playerToggleGroup, 150
        , 60);
    result.getChildren().addAll(onePlayerBtn, twoPlayerBtn);
    return result;
  }
}
