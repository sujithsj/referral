package com.ds.core.event;

import com.ds.core.event.listener.MarketingMaterialServeEventListener;
import com.ds.impl.service.ServiceLocatorFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public class AsyncEventListenerRegistry {

  private static Map<String, List<EventListener>> asyncEventListenerRegistry = new LinkedHashMap<String, List<EventListener>>();


  public static List<EventListener> getEventListeners(String eventName) {
    List<EventListener> eventListeners = asyncEventListenerRegistry.get(eventName);

    if (eventListeners == null || eventListeners.isEmpty()) {
      if (MarketingMaterialServeEvent.class.getSimpleName().equalsIgnoreCase(eventName)) {
        List<EventListener> mmServeEventListeners = new ArrayList<EventListener>();
        MarketingMaterialServeEventListener marketingMaterialServeEventListener = (MarketingMaterialServeEventListener) ServiceLocatorFactory.getService("MarketingMaterialServeEventListener");
        mmServeEventListeners.add(marketingMaterialServeEventListener);

        String key = MarketingMaterialServeEvent.class.getSimpleName();
        asyncEventListenerRegistry.put(key, mmServeEventListeners);
      }
    }
    return asyncEventListenerRegistry.get(eventName);
  }
}
