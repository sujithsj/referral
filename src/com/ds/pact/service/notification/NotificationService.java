package com.ds.pact.service.notification;

import com.ds.domain.notification.Notification;
import com.ds.constants.EnumNotificationType;

/**
 * User: Rahul
 * Date: Jan 7, 2013
 * Time: 2:39:13 PM
 */
public interface NotificationService {

	public Notification getNotificationById(Long Id);

	public Notification saveNotification(Notification notification);

	public Notification createCompanyNotification(String companyShortName,final EnumNotificationType enumNotificationType);

}
