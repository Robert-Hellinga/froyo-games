package ooga.view.screens;

import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import ooga.controller.IFroyoController;
import ooga.fileHandler.Resources;
import ooga.view.Styleable;
import ooga.view.Util;
import ooga.view.elements.CustomButton;
import ooga.view.elements.LabeledDropdown;
import ooga.view.grid.PieceGrid;

public class LanguageScreen extends GridPane implements Styleable {

  private static final String DEFAULT_STYLE_SHEET = "resources/style/default.css";
  private static final int SCREEN_WIDTH = 400;
  private static final int SCREEN_HEIGHT = 350;
  private static final String[] AVAILABLE_LOCALES = {"en", "it", "es"};

  public LanguageScreen(IFroyoController controller) {

    setAlignment(Pos.CENTER);
    setStyleSheet(DEFAULT_STYLE_SHEET);
    setWidth(SCREEN_WIDTH);
    setHeight(SCREEN_HEIGHT);

    setGridLinesVisible(true);
    LabeledDropdown languageDropdown = new LabeledDropdown("TEST", AVAILABLE_LOCALES[0], AVAILABLE_LOCALES);
    add(languageDropdown, 0, 0);

    CustomButton testButton = new CustomButton("Start",
        event -> new SplashScreen(new Locale((String) languageDropdown.getValue()), controller));
    add(testButton, 0, 1);
    controller.setNewLayout(this);
  }

  private void getLanguageCells() {

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

  private class LanguageCell {

    private Locale locale;

    public LanguageCell(String key) {
      this.locale = new Locale(key);
    }

    @Override
    public String toString() {
      return locale.getDisplayName();
    }
  }
}
