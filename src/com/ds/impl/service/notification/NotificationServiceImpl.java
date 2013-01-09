package com.ds.impl.service.notification;

import com.ds.domain.notification.Notification;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.notification.NotificationService;
import com.ds.constants.EnumNotificationType;
import com.ds.constants.NotificationMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

/**
 * User: Rahul
 * Date: Jan 7, 2013
 * Time: 2:38:37 PM
 */
@Service
public class NotificationServiceImpl implements NotificationService {


	private Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	public Notification getNotificationById(Long notificationId) {
		Notification notification = (Notification) getBaseDao().load(Notification.class, notificationId);

		if (notification == null) {
			logger.error("No such notification found in system: " + notificationId);
			throw new InvalidParameterException("INVALID_NOTIFICATION");
		}
		return notification;
	}

	@Transactional
	public Notification saveNotification(Notification notification) {
		return (Notification) getBaseDao().save(notification);
	}

	public Notification createCompanyNotification(String companyShortName,final EnumNotificationType enumNotificationType) {
		Notification notification = new Notification();
		notification.setCompanyShortName(companyShortName);
		switch(enumNotificationType) {
			case COMPANY_AFFILIATE_APPROVAL_PENDING:
				notification.setNotificationType(EnumNotificationType.COMPANY_AFFILIATE_APPROVAL_PENDING.asNotificationType());
				notification.setMessage(NotificationMessages.COMPANY_AFFILIATE_APPROVAL_PENDING);
				break;
		}
		return saveNotification(notification);
	}


	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}

