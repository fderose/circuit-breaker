package main;

import service1.Service1CodeGen;
import service1.Service1Itf;
import service1.impl.Service1CacheImpl;
import service1.impl.Service1FallbackImpl;
import service1.impl.Service1WebServiceImpl;
import service1.types.Service1Call1Input;
import service2.Service2CodeGen;
import service2.Service2Itf;
import service2.impl.Service2CacheImpl;
import service2.impl.Service2FallbackImpl;
import service2.impl.Service2WebServiceImpl;
import service2.types.Service2Call1Input;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCaller<Input, Output> {

  private final static Map<Class<?>, List<Class<?>>> implementationsByInterface = new HashMap<>();
  private final static Map<Method, Health> healthByMethod = new HashMap<>();

  static {
    implementationsByInterface.put(Service1Itf.class, Arrays.asList(new Class[]{Service1CacheImpl.class, Service1WebServiceImpl.class, Service1FallbackImpl.class}));
    implementationsByInterface.put(Service2Itf.class, Arrays.asList(new Class[]{Service2CacheImpl.class, Service2WebServiceImpl.class, Service2FallbackImpl.class}));
  }

  private static Health getHealth(Method method) {
    synchronized(Health.class) {
      Health health = healthByMethod.get(method);
      if (health == null) {
        health = new Health();
        healthByMethod.put(method, health);
      }
      return health;
    }
  }

  private Output call(Method method, Input input) throws Exception {
    List<Class<?>> impls = implementationsByInterface.get(method.getDeclaringClass());
    if (impls == null) {
      throw new Exception("No impls for type " + method.getDeclaringClass());
    }
    for (Class<?> impl : impls) {
      Object instance = impl.newInstance();
      Method implMethod = impl.getMethod(method.getName(), input.getClass());
      Health health = getHealth(implMethod);
      try {
        if (health.isMethodHealthy()) {
          return (Output) impl.getMethod(method.getName(), input.getClass()).invoke(instance, input);
        }
      } catch (Exception e) {
        if (e.getCause() instanceof CacheMissException) {
          System.out.println(e.getCause().getMessage());
          continue;
        }
        health.incrementExceptionsForMethod(e);
        StringBuilder sb = new StringBuilder();
        sb.append("Exception thrown: " + e.getClass().getName());
        if (e.getMessage() != null) {
          sb.append("\n" + e.getMessage());
        }
        if (e.getCause() != null) {
          sb.append("\n" + e.getCause().getMessage());
          System.out.println();
        }
        System.out.println(sb.toString() + "\n");
      }
    }
    throw new Exception("Cannot obtain output");
  }

  private Method getMethod(Class<?> interfaceType, String methodName, Class<?> input) throws Exception {
    return interfaceType.getMethod(methodName, input);
  }

  public Output invoke(Class<?> interfaceType, String methodName, Input input) throws Exception {
    return call(getMethod(interfaceType, methodName, input.getClass()), input);
  }

  public static void main(String[] args) throws Exception {
//        Service1Itf service1 = new Service1CodeGen();
//        System.out.println(service1.call1(new Service1Call1Input()));
//        System.out.println(service1.call2(new Service1Call2Input()));
//
//        Service2Itf service2 = new Service2CodeGen();
//        System.out.println(service2.call1(new Service2Call1Input()));
//        System.out.println(service2.call2(new Service2Call2Input()));

    Thread t1 = new Thread() {
      @Override
      public void run() {
        try {
          Service1Itf service1 = new Service1CodeGen();
          long startTime = System.currentTimeMillis();
          while (System.currentTimeMillis() - startTime < 60000) {
            System.out.println(service1.call1(new Service1Call1Input()));
            Thread.sleep(100);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    Thread t2 = new Thread() {
      @Override
      public void run() {
        try {
          Service2Itf service2 = new Service2CodeGen();
          long startTime = System.currentTimeMillis();
          while (System.currentTimeMillis() - startTime < 60000) {
            System.out.println(service2.call1(new Service2Call1Input()));
            Thread.sleep(100);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    Thread t3 = new Thread() {
      @Override
      public void run() {
        try {
          Service1Itf service1 = new Service1CodeGen();
          long startTime = System.currentTimeMillis();
          while (System.currentTimeMillis() - startTime < 60000) {
            System.out.println(service1.call1(new Service1Call1Input()));
            Thread.sleep(100);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    t1.start();
    t2.start();
    t3.start();

    t1.join();
    t2.join();
    t3.join();

    System.out.println("done");
  }
}
