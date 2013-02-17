package com.ds.impl.service.notification;

import com.ds.domain.notification.Notification;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.dao.notification.NotificationDao;
import com.ds.pact.service.notification.NotificationService;
import com.ds.pact.service.core.SearchService;
import com.ds.constants.EnumNotificationType;
import com.ds.constants.NotificationMessages;
import com.ds.impl.dao.notification.NotificationDaoImpl;
import com.ds.web.action.Page;
import com.ds.search.impl.CompanyAffiliateInviteQuery;
import com.ds.search.impl.NotificationQuery;
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
	@Autowired
	private NotificationDao notificationDao;
	@Autowired
	private SearchService searchService;

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

	public long getPendingNotificationForAffiliate(String userId) {
		return notificationDao.getPendingNotificationForUser(userId);
	}

	public Page searchCompanyAffiliatePendingNotification(String userId,int pageNo,int perPage){

		NotificationQuery notificationQuery = new NotificationQuery();
		notificationQuery.setUserId(userId);
		notificationQuery.setNotified(false);
		notificationQuery.setOrderByField("id").setPageNo(pageNo).setRows(perPage);
		return getSearchService().list(notificationQuery);


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

	public NotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
}

