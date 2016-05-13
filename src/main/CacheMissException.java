package main;

public class CacheMissException extends Exception {
  public CacheMissException(String msg) {
    super(msg);
  }
}
