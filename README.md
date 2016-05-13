# circuit-breaker
A given service (Service1Itf, Service2Itf) can have multiple implementations. For each ServiceItf, I've added 3 implementations:

1. a mock cache implementation,<br>
2. a mock web service implementation,
3. a mock fallback implementation.

These implementations are in an ordered list that is associated with the ServiceItf (see ServiceCaller.implementationsByInterface).

Each method in each implementation has a Health object associated with it (see ServiceCaller.healthByMethod). For a given method call through the ServiceItf, ServiceCaller loops through the various implementations in order, calling the desired method. The health of the method is first checked by testing if there have been 5 or more exceptions thrown by the method in the last 10 seconds. If so, then, the method is marked as unhealthy and it is no longer called until the number of exceptions thrown in the most recent 10 second interval drops to 0 (this logic could, of course, be modified). If a method becomes healthy again, it will be called. If a method is unhealthy, or it is a cache implementation and throws the CacheMissException, or it throws an Exception itself, ServiceCaller loops to the next implementation. The Fallback implementation is assumed to always return a default return value.

In ServiceCaller.main, two threads are started that start calling methods on the two services. You can see from the output which implementation's method is called, the cache, the web service, or the fallback. I have randomly introduced cache misses and have introduced certain intervals when the WebService implementations throw exceptions, simulating that they are unhealthy. You can see from the output that the WebService implementation is called if the cache implementation throws a CacheMissException, and the Fallback implementation is called if the WebService implementation is unhealthy or throws an Exception.
