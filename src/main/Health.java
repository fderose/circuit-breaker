package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Health {
  private final Map<Exception, Long> exceptionCache = new HashMap<>();
  private boolean healthy = true;

  public synchronized void incrementExceptionsForMethod(Exception exception) {
    exceptionCache.put(exception, System.currentTimeMillis());
  }

  public synchronized boolean isMethodHealthy() {
    Set<Exception> exceptionsToRemove = new HashSet<>();
    for (Exception e : exceptionCache.keySet()) {
      if (System.currentTimeMillis() - exceptionCache.get(e) > 10000) {
        exceptionsToRemove.add(e);
      }
    }
    for (Exception e : exceptionsToRemove) {
      exceptionCache.remove(e);
    }
    if (healthy) {
      healthy = exceptionCache.size() < 5;
    } else { // !healthy
      if (exceptionCache.size() == 0) {
        healthy = true;
      }
    }
    return healthy;
  }

}
