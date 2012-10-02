package com.ds.web.interceptor;


import com.ds.impl.service.ServiceLocatorFactory;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * @author adlakha.vaibhav
 */
@Intercepts(LifecycleStage.ActionBeanResolution)
public class StripesSpringInterceptor implements Interceptor {
  public Resolution intercept(ExecutionContext context) throws Exception {
    Resolution resolution = context.proceed();

    ServletContext servletContext = StripesFilter.getConfiguration().getServletContext();
    ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
    beanFactory.autowireBeanProperties(context.getActionBean(), AutowireCapableBeanFactory.AUTOWIRE_NO, false);
    beanFactory.initializeBean(context.getActionBean(), StringUtils.uncapitalize(context.getActionBean().getClass().getSimpleName()));

    ((SessionFactory) ServiceLocatorFactory.getService("SessionFactory")).getCurrentSession().setFlushMode(FlushMode.MANUAL);


    return resolution;
  }
}
