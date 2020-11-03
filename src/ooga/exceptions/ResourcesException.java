package ooga.exceptions;

/**
 * Handles exceptions if a key is not found in a .properties file
 */
public class ResourcesException extends RuntimeException{

  public ResourcesException(String message){
    super(message);
  }

  public ResourcesException(String message, Throwable cause){
    super(message,cause);
  }
}
