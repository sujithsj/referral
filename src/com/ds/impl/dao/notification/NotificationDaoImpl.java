package com.ds.impl.dao.notification;

import com.ds.impl.dao.BaseDaoImpl;
import com.ds.pact.dao.notification.NotificationDao;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:37:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class NotificationDaoImpl extends BaseDaoImpl implements NotificationDao {


	@Override
	public long getPendingNotificationForUser(String userId) {
		return count("select count(*) from Notification where user.id = ? and notified = 0", userId);
	}

}