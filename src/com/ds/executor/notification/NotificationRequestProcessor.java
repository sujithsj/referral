package com.ds.executor.notification;

import com.ds.executor.RequestProcessor;
import com.ds.executor.ExecutionRequest;
import com.ds.pact.service.notification.NotificationService;
import com.ds.impl.service.ServiceLocatorFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 9, 2013
 * Time: 7:07:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationRequestProcessor implements RequestProcessor{

  private NotificationService notificationService;

  @Override
  public void handleRequest(ExecutionRequest request) {
    if(request instanceof NotificationRequest){
      NotificationRequest notificationRequest = (NotificationRequest) request;

      getNotificationService().createNotification(notificationRequest);
    }
  }

  public NotificationService getNotificationService() {
    if(notificationService == null){
      notificationService = ServiceLocatorFactory.getService(NotificationService.class);
    }
    return notificationService;
  }
}
