package main;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class WebServiceImplBase extends ImplBase {
  private static boolean healthy = true;
  private static final Timer timer = new Timer();
  private static final Random random = new Random();

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
      throw new NonRetryableException("NonRetryableException thrown by " + this.getClass().getName() + call); // Simulate a non retryable failure.
    }

    if ((Math.abs(random.nextInt()) % 100) > 90) {
      throw new RetryableException("RetryableException thrown by " + this.getClass().getName() + call); // Simulate a retryable failure.
    }

  }
}
