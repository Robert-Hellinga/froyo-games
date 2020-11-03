package ooga.exceptions;

public class ResourcesFileNotFoundException extends RuntimeException{

  public ResourcesFileNotFoundException(String message){
    super(message);
  }

  public ResourcesFileNotFoundException(String message, Throwable cause){
    super(message,cause);
  }
}
