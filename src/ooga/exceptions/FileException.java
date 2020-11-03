package ooga.exceptions;

/**
 * Exceptions for fileExceptions, such as reading in the file, writing to a file, etc.
 */
public class FileException extends RuntimeException {

    public FileException(String message) {
      super(message);
      System.out.println("hi");
    }

    public FileException(String message, Throwable cause) {
      super(message, cause);
    }
}
