package com.ds.pact.service.notification;

import com.ds.domain.notification.Notification;
import com.ds.executor.notification.NotificationRequest;
import com.ds.web.action.Page;

import java.util.List;

/**
 * User: Rahul
 * Date: Jan 7, 2013
 * Time: 2:39:13 PM
 */
public interface NotificationService {

	public Notification getNotificationById(Long Id);

	public Notification saveNotification(Notification notification);

	public Notification createNotification(NotificationRequest notificationRequest);

	public long getPendingNotificationCountForUser(String userEmail);

  public List<Notification> getLastestUnreadNotificationsForCompany(String companyShortName, int maxCount);

	public Page searchCompanyAffiliatePendingNotification(String userId,int pageNo,int perPage);

	/*public List<Notification> getNotificationForAffiliate(*/

}
