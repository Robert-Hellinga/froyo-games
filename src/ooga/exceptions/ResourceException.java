package ooga.exceptions;

/**
 * Handles exceptions if a key is not found in a .properties file
 */
public class ResourceException extends RuntimeException {

  public ResourceException(String message) {
    super(message);
  }

  public ResourceException(String message, Throwable cause) {
    super(message, cause);
  }
}
