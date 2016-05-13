package main;

public abstract class ImplBase {
  protected String buildMessage(String call) {
    return "Output from " + this.getClass().getName() + "." + call;
  }
}
