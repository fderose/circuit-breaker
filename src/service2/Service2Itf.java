package service2;

import service2.types.Service2Call1Input;
import service2.types.Service2Call1Output;
import service2.types.Service2Call2Input;
import service2.types.Service2Call2Output;

// This interface is defined by Guidewire
public interface Service2Itf {
  Service2Call1Output call1(Service2Call1Input input) throws Exception;

  Service2Call2Output call2(Service2Call2Input input) throws Exception;
}
