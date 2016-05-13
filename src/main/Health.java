package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Health {
  private static final Object object = new Object();

  private final Map<Exception, Long> exceptionCache = new HashMap<>();

  public synchronized void incrementExceptionsForMethod(Exception exception) {
    exceptionCache.put(exception, System.currentTimeMillis());
  }

  public boolean isMethodHealthy() {
    Set<Exception> exceptionsToRemove = new HashSet<>();
    for (Exception e : exceptionCache.keySet()) {
      if (System.currentTimeMillis() - exceptionCache.get(e) > 10000) {
        exceptionsToRemove.add(e);
      }
    }
    for (Exception e : exceptionsToRemove) {
      exceptionCache.remove(e);
    }
    return exceptionCache.size() < 5;
  }

}
