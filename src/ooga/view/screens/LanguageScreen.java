package ooga.view.screens;

import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ooga.Util;
import ooga.controller.IFroyoController;
import ooga.view.elements.ButtonGroup;
import ooga.view.elements.LabeledDropdown;

public class LanguageScreen extends GridPane {

  private static final String START_BTN_TEXT = "Start / Debut / Start";
  private static final String DROPDOWN_LABEL_TEXT = "Choose Language / Choisissez la Langue / Sprache Wahlen"; // Not in a resource bundle; language hasn't been selected yet
  private static final String LAYOUT_ID = "Layout";
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
    setId(LAYOUT_ID);

    add(getStartButtonGroup(controller), 0, 0);
    controller.setNewLayout(this);
    Util.applyStyleSheet(this);
  }

  private VBox getStartButtonGroup(IFroyoController controller) {
    VBox result = new VBox();
    result.setAlignment(Pos.CENTER);
    result.setSpacing(SCREEN_SPACING);

    LabeledDropdown languageSelector = getLanguageDropdown();
    result.getChildren().add(languageSelector);

    ButtonGroup startButton = new ButtonGroup(result, START_BTN_WIDTH, START_BTN_HEIGHT);
    startButton.addButtons(START_BTN_TEXT);
    startButton
        .setOnButtonPushed(event -> new SplashScreen(AVAILABLE_LOCALES[languageSelector.getSelectedIndex()], controller));
    startButton.setButtonStyles(ButtonGroup.SUCCESS_STYLE);

    return result;
  }

  private LabeledDropdown getLanguageDropdown() {
    String[] displayLanguages = new String[AVAILABLE_LOCALES.length];
    for(int i = 0; i < AVAILABLE_LOCALES.length; i++) {
      displayLanguages[i] = AVAILABLE_LOCALES[i].getDisplayName();
    }
    return new LabeledDropdown(DROPDOWN_LABEL_TEXT, displayLanguages);
  }
}
