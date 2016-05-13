package service1.impl;

import main.WebServiceImplBase;
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
 * 1.) Convert the input parameter into the input parameters of the external call.
 * 2.) Make the external call with those input parameters.
 * 3.) Convert the output parameters/return value of the external call into a return value of the appropriate type and return it.
 */

public class Service1WebServiceImpl extends WebServiceImplBase implements Service1Itf {
  @Override
  public Service1Call1Output call1(Service1Call1Input input) throws Exception {
    maybeThrowException("call1"); // Simulate failure.
    return new Service1Call1Output(buildMessage("call1"));
  }

  @Override
  public Service1Call2Output call2(Service1Call2Input input) throws Exception {
    return new Service1Call2Output(buildMessage("call2"));
  }
}
