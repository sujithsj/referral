package com.ds.pact.service.notification;

import com.ds.domain.notification.Notification;
import com.ds.web.action.Page;
import com.ds.executor.notification.NotificationRequest;

/**
 * User: Rahul
 * Date: Jan 7, 2013
 * Time: 2:39:13 PM
 */
public interface NotificationService {

	public Notification getNotificationById(Long Id);

	public Notification saveNotification(Notification notification);

	public Notification createNotification(NotificationRequest notificationRequest);

	public long getPendingNotificationForAffiliate(String affiliateEmailId);

	public Page searchCompanyAffiliatePendingNotification(String userId,int pageNo,int perPage);

	/*public List<Notification> getNotificationForAffiliate(*/

}
