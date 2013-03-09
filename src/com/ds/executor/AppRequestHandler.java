package com.ds.executor;

import com.ds.executor.notification.NotificationRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 9, 2013
 * Time: 8:10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppRequestHandler {

  private static final RequestExceutor   notificationRequestExecutor    = new RequestExceutor(75, 175, 60);


  public static void handleNotificationRequest(NotificationRequest notificationRequest){
    notificationRequestExecutor.addRequest(notificationRequest);
  }
}
