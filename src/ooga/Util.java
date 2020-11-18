package ooga;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.game.Game;
import ooga.model.player.Player;

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
