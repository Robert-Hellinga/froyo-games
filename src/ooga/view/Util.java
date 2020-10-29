package ooga.view;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Util {

  public static int getResourceAsInt(ResourceBundle resource, String key) {
    return Integer.parseInt(resource.getString(key));
  }

  public static String getResourceAsString(ResourceBundle resource, String key) {
    return resource.getString(key);
  }

  public static void setSceneStylesheet(Scene scene, String stylesheet) {
    scene.getStylesheets().add(Util.class.getClassLoader().getResource(stylesheet).toExternalForm());
  }

  public static void configureElement(Node element, Pos alignment) {

  }

  public static void configureElement() {

  }

}
