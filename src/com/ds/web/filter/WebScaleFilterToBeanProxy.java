package com.ds.web.filter;

import com.ds.impl.service.ServiceLocatorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.FilterConfig;

/**
 * @author adlakha.vaibhav
 */
public class WebScaleFilterToBeanProxy extends DelegatingFilterProxy {

    protected ApplicationContext getContext(FilterConfig filterConfig) {
        return ServiceLocatorFactory.getApplicationContext();
    }

}

