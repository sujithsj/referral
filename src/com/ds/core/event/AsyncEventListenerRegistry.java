package com.ds.core.event;

import com.ds.core.event.listener.MarketingMaterialServeEventListener;
import com.ds.core.event.listener.MarketingMaterialSaleEventListener;
import com.ds.core.event.listener.MarketingMaterialImpressionEventListener;
import com.ds.core.event.listener.UserLoginEmailConfirmationRequestListener;
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

            if (MarketingMaterialSaleEvent.class.getSimpleName().equalsIgnoreCase(eventName)) {
                List<EventListener> mmSaleEventListeners = new ArrayList<EventListener>();
                MarketingMaterialSaleEventListener mmSaleEventListener = (MarketingMaterialSaleEventListener) ServiceLocatorFactory.getService("MarketingMaterialSaleEventListener");
                mmSaleEventListeners.add(mmSaleEventListener);

                String key = MarketingMaterialSaleEvent.class.getSimpleName();
                asyncEventListenerRegistry.put(key, mmSaleEventListeners);
            }

            if (MarketingMaterialImpressionEvent.class.getSimpleName().equalsIgnoreCase(eventName)) {
                List<EventListener> mmImpressionEventListeners = new ArrayList<EventListener>();
                MarketingMaterialImpressionEventListener impressionEventListener = (MarketingMaterialImpressionEventListener) ServiceLocatorFactory.getService("MarketingMaterialImpressionEventListener");
                mmImpressionEventListeners.add(impressionEventListener);

                String key = MarketingMaterialImpressionEvent.class.getSimpleName();
                asyncEventListenerRegistry.put(key, mmImpressionEventListeners);
            }

            if (UserLoginEmailConfirmationRequestEvent.class.getSimpleName().equalsIgnoreCase(eventName)) {
                List<EventListener> userLoginConfirmationRequestEventListeners = new ArrayList<EventListener>();
                UserLoginEmailConfirmationRequestListener userLoginEmailConfirmationRequestListener = (UserLoginEmailConfirmationRequestListener) ServiceLocatorFactory.getService("UserLoginEmailConfirmationRequestListener");
                userLoginConfirmationRequestEventListeners.add(userLoginEmailConfirmationRequestListener);

                String key = UserLoginEmailConfirmationRequestEvent.class.getSimpleName();
                asyncEventListenerRegistry.put(key, userLoginConfirmationRequestEventListeners);
            }

        }

        return asyncEventListenerRegistry.get(eventName);
    }
}
