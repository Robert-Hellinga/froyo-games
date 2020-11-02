package ooga.view.screens;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ooga.controller.GameController;
import ooga.view.Styleable;
import ooga.view.Util;
import ooga.view.elements.GameScreenButtonBox;
import ooga.view.grid.PieceGrid;

public class GameScreen extends GridPane implements Styleable {

  private static final int WIDTH = 400;
  private static final int HEIGHT = 350;
  private static final String DEFAULT_STYLE_SHEET = "style/default.css";

  private GameController controller;
  private ResourceBundle resourceBundle;

  public GameScreen(ResourceBundle resourceBundle, GameController controller) {
    this.controller = controller;
    this.resourceBundle = resourceBundle;

    setStyleSheet(DEFAULT_STYLE_SHEET);
    setWidth(WIDTH);
    setHeight(HEIGHT);
    
    add(makeGameButtons(), 0, 0);
    add(makePieceGrid(), 1, 0);
  }

  private Pane makePieceGrid() {
    return new PieceGrid(controller);
  }

  private VBox makeGameButtons() {
    return new GameScreenButtonBox(resourceBundle);
  }

  @Override
  public void setStyleSheet(String stylesheet) {
    getStylesheets().clear();
    Util.setPaneStylesheet(this, stylesheet);
  }

  @Override
  public String getStyleSheet() {
    return getStylesheets().get(0);
  }
}
