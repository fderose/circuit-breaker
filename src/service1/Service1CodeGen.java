package service1;

import main.ServiceCaller;
import service1.types.Service1Call1Input;
import service1.types.Service1Call1Output;
import service1.types.Service1Call2Input;
import service1.types.Service1Call2Output;

// This code is automatically generated by Guidewire from Service1Itf
public class Service1CodeGen implements Service1Itf {
  @Override
  public Service1Call1Output call1(Service1Call1Input input) throws Exception {
    return new ServiceCaller<Service1Call1Input, Service1Call1Output>().invoke(Service1Itf.class, "call1", input);
  }

  @Override
  public Service1Call2Output call2(Service1Call2Input input) throws Exception {
    return new ServiceCaller<Service1Call2Input, Service1Call2Output>().invoke(Service1Itf.class, "call2", input);
  }
}