package main;

public class RetryableException extends Exception {
  public RetryableException(String msg) {
    super(msg);
  }
}
