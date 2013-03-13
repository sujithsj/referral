package com.ds.action.company;

import com.ds.domain.notification.Notification;
import com.ds.domain.user.User;
import com.ds.pact.service.notification.NotificationService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import net.sourceforge.stripes.action.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 9, 2013
 * Time: 1:14:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyNotificationAction extends BasePaginatedAction {

  private Page companyUserNotificationPage;
  private List<Notification> notificationList;

  private Long notificationId;

  @Autowired
  private NotificationService notificationService;


  @DefaultHandler
  @DontValidate
  @SuppressWarnings("unchecked")
  public Resolution displayAllNotifications() {

    User loggedInUser = SecurityHelper.getLoggedInUser();


    companyUserNotificationPage = getNotificationService().searchUserPendingNotification(loggedInUser.getUsername(), pageNo, perPage);
    notificationList = companyUserNotificationPage.getList();
    return new ForwardResolution("/pages/company/companyNotification.jsp");

  }

  public Resolution notificationRead() {
    getNotificationService().markNotificationRead(notificationId);
    return new RedirectResolution(CompanyNotificationAction.class);
  }

  public Resolution notificationDelete() {
    getNotificationService().deleteNotification(notificationId);
    return new RedirectResolution(CompanyNotificationAction.class);
  }


  public Page getCompanyUserNotificationPage() {
    return companyUserNotificationPage;
  }

  public void setCompanyUserNotificationPage(Page companyUserNotificationPage) {
    this.companyUserNotificationPage = companyUserNotificationPage;
  }

  public List<Notification> getNotificationList() {
    return notificationList;
  }

  public void setNotificationList(List<Notification> notificationList) {
    this.notificationList = notificationList;
  }

  public Long getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(Long notificationId) {
    this.notificationId = notificationId;
  }

  public NotificationService getNotificationService() {
    return notificationService;
  }

  @Override
  public int getPageCount() {
    return companyUserNotificationPage != null ? companyUserNotificationPage.getTotalPages() : 0;
  }

  @Override
  public int getResultCount() {
    	return companyUserNotificationPage != null ? companyUserNotificationPage.getTotalResults() : 0;
  }

  @Override
  public Set<String> getParamSet() {
    return null;  
  }
}
