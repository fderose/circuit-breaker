package main;

public class NonRetryableException extends Exception {
  public NonRetryableException(String msg) {
    super(msg);
  }
}
