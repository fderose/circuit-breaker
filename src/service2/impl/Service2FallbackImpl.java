package service2.impl;

import main.ImplBase;
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
 * 1.) Convert the input parameter into the input parameters of the external call.
 * 2.) Make the external call with those input parameters.
 * 3.) Convert the output parameters/return value of the external call into a return value of the appropriate type and return it.
 */

public class Service2FallbackImpl extends ImplBase implements Service2Itf {

  @Override
  public Service2Call1Output call1(Service2Call1Input input) throws Exception {
    return new Service2Call1Output(buildMessage("call1"));
  }

  @Override
  public Service2Call2Output call2(Service2Call2Input input) throws Exception {
    return new Service2Call2Output(buildMessage("call2"));
  }
}
