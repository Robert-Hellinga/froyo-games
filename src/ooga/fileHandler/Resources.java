package ooga.fileHandler;

import java.util.Locale;
import ooga.exceptions.ResourceException;

import java.util.ResourceBundle;

/**
 * Wrapper for ResourceBundle to make it easier to use throughout the program
 */
public class Resources {

  public static final String RESOURCES = "resources/";
  public static final String RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
  public static final String UI_RESOURCE_PACKAGE = RESOURCE_PACKAGE + ".ui.";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
  public static final String ERROR_MESSAGES_FILE = "errorMessages";
  private static final Locale DEFAULT_LOCALE = new Locale("en");

  ResourceBundle resources;

  public Resources(String propertiesFilename) {
    this(DEFAULT_LOCALE, RESOURCE_PACKAGE, propertiesFilename);
  }

  public Resources(Locale locale, String resourcePackage, String propertiesFilename) {
    String filePath = resourcePackage + propertiesFilename;
    filePath.replace("/", ".");

    resources = ResourceBundle.getBundle(filePath, locale);
  }

  /**
   * If a resource has a (key,value) pair that returns a String, this method can
   * be called to get the value using the corresponding key
   * @param key The key used to get the value from the properties file
   * @return value associated with the given key
   */
  public String getString(String key) {
    if (resources.containsKey(key)) {
      return resources.getString(key);
    }
    throw new ResourceException(String.format(resources.getString("ResourceKeyNotFound"), key));
  }

  /**
   * If a resource has a (key,value) pair that returns an object, this method can
   * be called to get the value using the corresponding key
   * @param key The key used to get the value from the properties file
   * @return value associated with the given key
   */
  public Object getObject(String key) {
    if (resources.containsKey(key)) {
      return resources.getObject(key);
    }
    throw new ResourceException(String.format(resources.getString("ResourceKeyNotFound"), key));
  }

  public Locale getLocale() {
    return resources.getLocale();
  }
}
