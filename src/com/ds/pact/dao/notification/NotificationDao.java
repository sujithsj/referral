package com.ds.pact.dao.notification;

import com.ds.pact.dao.BaseDao;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:37:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface NotificationDao extends BaseDao {

    public long getPendingNotificationForUser(String userId);

	public void markNotificationRead(Long notificationId);
}