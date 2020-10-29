package ooga.Model.Exception;

public class ClassOrMethodNotFoundException extends RuntimeException{

  public ClassOrMethodNotFoundException(String message){
    super(message);
  }

  public ClassOrMethodNotFoundException(String message, Throwable cause){
    super(message,cause);
  }
}
