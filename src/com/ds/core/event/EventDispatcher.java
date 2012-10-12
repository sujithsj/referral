package com.ds.core.event;

/**
 * @author adlakha.vaibhav
 */
public interface EventDispatcher {

  public void dispatchEvent(Event event);

  public void invokeAsyncEventListeners(AsyncEvent event);
}
