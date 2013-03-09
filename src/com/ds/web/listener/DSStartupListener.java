package com.ds.web.listener;

import com.ds.executor.RequestProcessorFactory;
import com.ds.executor.notification.NotificationRequest;
import com.ds.executor.notification.NotificationRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author adlakha.vaibhav
 */
public class DSStartupListener implements ServletContextListener {

  private static Logger logger = LoggerFactory.getLogger(DSStartupListener.class);

  public DSStartupListener() {
  }

  public void contextInitialized(ServletContextEvent event) {

    /*PropertyConfigurator.configure( "D:\\IdeaProjects\\Minor\\codebase\\HealthKart\\dist\\WEB-INF\\log4j.properties");
    logger.info("logger configured");*/

    System.out.println("================  STARTING DS  ==================");

    RequestProcessorFactory.register(NotificationRequest.class, new NotificationRequestProcessor());
    
    //MemcacheClientFactory.setInstance("127.0.0.1:11211");
    logger.info("================  STARTING DS  ==================");
  }

  public void contextDestroyed(ServletContextEvent sce) {

    System.out.println("================  SHUTTING DOWN  ==================");
    logger.info("================  SHUTTING DOWN  ==================");
  }
}
