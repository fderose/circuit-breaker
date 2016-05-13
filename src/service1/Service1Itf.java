package service1;

import service1.types.Service1Call1Input;
import service1.types.Service1Call1Output;
import service1.types.Service1Call2Input;
import service1.types.Service1Call2Output;

// This interface is defined by Guidewire
public interface Service1Itf {
  Service1Call1Output call1(Service1Call1Input input) throws Exception;

  Service1Call2Output call2(Service1Call2Input input) throws Exception;
}
