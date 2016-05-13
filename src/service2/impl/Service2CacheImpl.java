package service2.impl;

import main.CacheImplBase;
import service2.Service2Itf;
import service2.types.Service2Call1Input;
import service2.types.Service2Call1Output;
import service2.types.Service2Call2Input;
import service2.types.Service2Call2Output;

/**
 * This implementation class is defined by Guidewire or by the customer
 * <p>
 * It is the responsibility of each method in this class to:
 * <p>
 * 1.) Convert the input parameter into the key to be used to perform the lookup in the cache.
 * 2.) Do the lookup in the cache.
 * 3.) Convert the value returned from the cache into the appropriate return value and return it or throw a CacheMissException.
 */

public class Service2CacheImpl extends CacheImplBase implements Service2Itf {

  @Override
  public Service2Call1Output call1(Service2Call1Input input) throws Exception {
    maybeThrowCacheMissException("call1");
    return new Service2Call1Output(buildMessage("call1"));
  }

  @Override
  public Service2Call2Output call2(Service2Call2Input input) throws Exception {
    return new Service2Call2Output(buildMessage("call2"));
  }
}