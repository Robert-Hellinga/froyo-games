package ooga.view.elements;

import java.util.Locale;
import javafx.scene.control.ListCell;

public class LocaleCell extends ListCell<Locale> {

  @Override
  public void updateItem(Locale locale, boolean empty) {
    super.updateItem(locale, empty);
    if (empty) {
      setText(null);
    } else {
      setText(locale.getDisplayLanguage(locale));
    }
  }
}
