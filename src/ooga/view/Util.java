package ooga.view;

import java.util.ResourceBundle;

public class Util {

  public static int getResourceAsInt(ResourceBundle resource, String key) {
    return Integer.parseInt(resource.getString(key));
  }

  public static String getResourceAsString(ResourceBundle resource, String key) {
    return resource.getString(key);
  }

}
