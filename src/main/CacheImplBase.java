package main;

import java.util.Random;

public abstract class CacheImplBase extends ImplBase {

  protected static Random random = new Random();

  protected void maybeThrowCacheMissException(String call) throws CacheMissException {
    if ((Math.abs(random.nextInt()) % 100) > 80) {
      throw new CacheMissException("CacheMissException thrown by " + this.getClass().getName() + "." + call); // Simulate a cache miss.
    }
  }
}
