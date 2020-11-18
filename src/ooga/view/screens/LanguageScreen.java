package ooga.view.screens;

import java.awt.Label;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import ooga.controller.IFroyoController;
import ooga.fileHandler.Resources;
import ooga.view.Util;
import ooga.view.elements.ButtonGroup;
import ooga.view.elements.LabeledDropdown;
import ooga.view.grid.PieceGrid;

public class LanguageScreen extends GridPane {

  private static final String START_BTN_TEXT = "Start / D�but / Start";
  private static final String DROPDOWN_LABEL_TEXT = "Choose Language / Choisissez la Langue / "
      + "Sprache W�hlen"; // Not in a resource bundle; language hasn't been selected yet
  private static final int SCREEN_WIDTH = 450;
  private static final int SCREEN_HEIGHT = 150;
  private static final int SCREEN_SPACING = 20;
  private static final int START_BTN_WIDTH = 140;
  private static final int START_BTN_HEIGHT = 30;
  private static final Locale[] AVAILABLE_LOCALES = {Locale.ENGLISH, Locale.FRENCH, Locale.GERMAN};

  public LanguageScreen(IFroyoController controller) {
    setAlignment(Pos.CENTER);
    setWidth(SCREEN_WIDTH);
    setHeight(SCREEN_HEIGHT);

    add(getStartButtonGroup(controller), 0, 0);
    controller.setNewLayout(this);
    Util.applyStyleSheet(this);
  }

  private VBox getStartButtonGroup(IFroyoController controller) {
    VBox result = new VBox();
    result.setAlignment(Pos.CENTER);
    result.setSpacing(SCREEN_SPACING);

    LabeledDropdown<Locale> languageSelector = getLanguageDropdown();
    result.getChildren().add(languageSelector);

    ButtonGroup startButton = new ButtonGroup(result, START_BTN_WIDTH, START_BTN_HEIGHT);
    startButton.addButtons(START_BTN_TEXT);
    startButton.setOnButtonPushed(event -> new SplashScreen(languageSelector.getValue(), controller));
    startButton.setButtonStyles(ButtonGroup.SUCCESS_STYLE);

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
