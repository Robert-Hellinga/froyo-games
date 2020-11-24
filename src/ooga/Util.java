package ooga;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javafx.scene.layout.Pane;
import ooga.exceptions.ClassOrMethodNotFoundException;

/**
 * Util class used for applying stylesheets to Panes and for using reflection
 * @author Nate Mela (nrm27)
 */
public class Util {

  private static final String STYLE_SHEET = "resources/style/default.css";

  public static void applyStyleSheet(Pane pane) {
    pane.getStylesheets().add(STYLE_SHEET);
  }

  public static <T> T reflect(String directory, List<Class<?>> paramClasses, List<?> parameters) {
    try {
      Class<?> game = Class.forName(directory);
      Class<?>[] parameterTypes = new Class[paramClasses.size()];
      Constructor<?> cons = game.getConstructor(paramClasses.toArray(parameterTypes));
      Object[] parameterObjects = new Object[parameters.size()];
      Object reflectedObject = cons.newInstance(parameters.toArray(parameterObjects));
      return (T) reflectedObject;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new ClassOrMethodNotFoundException("Class not found." + e.getMessage());
    }
  }

}
