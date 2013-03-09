package com.ds.executor.notification;

import com.ds.executor.ExecutionRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 9, 2013
 * Time: 6:10:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationRequest implements ExecutionRequest {

  private String userId;
  private String companyShortName;
  private Long notificationType;
  private String[] params;


  public String getNotificationMessage(String template) {
    String message = template;
    for (int i = 0; i < params.length; i++) {
      String tokenToReplace = "#" + i + "#";
      message = message.replaceAll(tokenToReplace, params[i]);
    }
    return message;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Long getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(Long notificationType) {
    this.notificationType = notificationType;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public String[] getParams() {
    return params;
  }

  public void setParams(String[] params) {
    this.params = params;
  }
}
