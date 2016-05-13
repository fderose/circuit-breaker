package main;

import java.util.Timer;
import java.util.TimerTask;

public abstract class WebServiceImplBase extends ImplBase {
  protected static boolean healthy = true;

  protected static Timer timer = new Timer();

  static {
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        healthy = false;
      }
    }, 10000);

    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        healthy = true;
        timer.cancel();
      }
    }, 30000);
  }

  protected void maybeThrowException(String call) throws Exception {
    if (!healthy) {
      throw new Exception("Exception thrown by " + this.getClass().getName() + call); // Simulate a failure.
    }
  }
}
