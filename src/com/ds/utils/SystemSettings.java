package com.ds.utils;

import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.admin.LoadPropertyService;

/**
 * @author adlakha.vaibhav
 */
public class SystemSettings {

private static LoadPropertyService loadPropertyService;

    public static String getBaseUrl() {
        String baseUrl = (String) getLoadPropertyService().getProperty("referoscope.baseUrl");
        return baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    /**
     * @return the loadPropertyService
     */
    private static LoadPropertyService getLoadPropertyService() {
        if (loadPropertyService == null) {
            loadPropertyService = ServiceLocatorFactory.getService(LoadPropertyService.class);
        }
        return loadPropertyService;
    }
  
}
