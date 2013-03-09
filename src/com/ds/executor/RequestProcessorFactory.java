package com.ds.executor;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 9, 2013
 * Time: 6:16:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestProcessorFactory {

  private static final Map<Class, RequestProcessor> processors = new HashMap<Class, RequestProcessor>();


  public static RequestProcessor getProcessor(ExecutionRequest executionRequest) {
    if (processors.containsKey(executionRequest.getClass())) {
      return processors.get(executionRequest.getClass());
    } else {
      throw new IllegalStateException("processor not registered for product type:" + executionRequest.getClass());
    }
  }

  public static void register(Class searchRequestClass, RequestProcessor processor) {
        processors.put(searchRequestClass, processor);
    }
}
