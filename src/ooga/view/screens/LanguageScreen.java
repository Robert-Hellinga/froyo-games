package ooga.view.screens;

import java.awt.Label;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import ooga.controller.IFroyoController;
import ooga.fileHandler.Resources;
import ooga.view.Styleable;
import ooga.view.Util;
import ooga.view.elements.CustomButton;
import ooga.view.elements.LabeledDropdown;
import ooga.view.grid.PieceGrid;

public class LanguageScreen extends GridPane {

  private static final String DEFAULT_STYLE_SHEET = "resources/style/default.css";
  private static final String START_BTN_TEXT = "Start / Début / Start";
  private static final String DROPDOWN_LABEL_TEXT = "Choose Language / Choisissez la Langue / "
      + "Sprache Wählen"; // Not in a resource bundle; language hasn't been selected yet
  private static final int SCREEN_WIDTH = 450;
  private static final int SCREEN_HEIGHT = 150;
  private static final int SCREEN_SPACING = 20;
  private static final int START_BTN_WIDTH = 140;
  private static final int START_BTN_HEIGHT = 30;
  private static final Locale[] AVAILABLE_LOCALES = {Locale.ENGLISH, Locale.FRENCH, Locale.GERMAN};

  public LanguageScreen(IFroyoController controller) {

    setAlignment(Pos.CENTER);
    Util.setPaneStylesheet(this, DEFAULT_STYLE_SHEET);
    setWidth(SCREEN_WIDTH);
    setHeight(SCREEN_HEIGHT);

    add(getStartButtonGroup(controller), 0, 0);
    controller.setNewLayout(this);
  }

  private VBox getStartButtonGroup(IFroyoController controller) {
    VBox result = new VBox();
    result.setAlignment(Pos.CENTER);
    result.setSpacing(SCREEN_SPACING);

    LabeledDropdown<Locale> languageSelector = getLanguageDropdown();

    CustomButton startButton = new CustomButton(START_BTN_TEXT,
        event -> new SplashScreen(languageSelector.getValue(), controller),
        START_BTN_WIDTH,
        START_BTN_HEIGHT
    );
    startButton.getStyleClass().add("success");

    result.getChildren().addAll(languageSelector, startButton);

    return result;
  }

  private LabeledDropdown getLanguageDropdown() {
    LabeledDropdown<Locale> languageDropdown = new LabeledDropdown(DROPDOWN_LABEL_TEXT, AVAILABLE_LOCALES);
    languageDropdown.setConverter(new StringConverter<>() {
      @Override
      public String toString(Locale locale) {
        return locale.getDisplayName();
      }

      @Override
      public Locale fromString(String string) {
        return new Locale(string);
      }
    });
    return languageDropdown;
  }
}
