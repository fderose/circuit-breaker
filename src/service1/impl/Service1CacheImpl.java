package service1.impl;

import main.CacheImplBase;
import service1.Service1Itf;
import service1.types.Service1Call1Input;
import service1.types.Service1Call1Output;
import service1.types.Service1Call2Input;
import service1.types.Service1Call2Output;

/**
 * This implementation class is defined by Guidewire or by the customer
 * <p>
 * It is the responsibility of each method in this class to:
 * <p>
 * 1.) Convert the input parameter into the key to be used to perform the lookup in the cache.
 * 2.) Do the lookup in the cache.
 * 3.) Convert the value returned from the cache into the appropriate return value and return it or throw a CacheMissException.
 */

public class Service1CacheImpl extends CacheImplBase implements Service1Itf {

  @Override
  public Service1Call1Output call1(Service1Call1Input input) throws Exception {
    maybeThrowCacheMissException("call1");
    return new Service1Call1Output(buildMessage("call1"));
  }

  @Override
  public Service1Call2Output call2(Service1Call2Input input) throws Exception {
    return new Service1Call2Output(buildMessage("call2"));
  }
}
