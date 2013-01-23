package com.ds.search.impl;

import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Rahul
 * Date: Jan 7, 2013
 * Time: 2:54:55 PM
 */
public class NotificationQuery extends AbstractSearchQuery {

	private Long notificationTypeId;
	private Long affiliateId;
	private Long companyAffiliateId;
	private Long userId;
	private String companyShortName;

	public NotificationQuery setNotificationTypeId(Long notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
		return this;
	}

	public NotificationQuery setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
		return this;
	}

	public NotificationQuery setCompanyAffiliateId(Long companyAffiliateId) {
		this.companyAffiliateId = companyAffiliateId;
		return this;
	}

	public NotificationQuery setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public NotificationQuery setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
		return this;
	}


	@Override
	protected String getBaseQuery() {

		StringBuilder queryStr = new StringBuilder("select nf from Notification nf join nf.notificationType nt where 1=1 ");

		if (notificationTypeId != null) {
			queryStr.append(" and nt.id  =  :notificationTypeId ");
			getQueryParams().put("notificationTypeId", notificationTypeId);
		}

		if (affiliateId != null) {
			queryStr.append(" and nf.affiliateId =  :affiliateId ");
			getQueryParams().put("affiliateId", affiliateId );
		}

		if (companyAffiliateId != null) {
			queryStr.append(" and nf.companyAffiliateId =  :companyAffiliateId ");
			getQueryParams().put("companyAffiliateId", companyAffiliateId);
		}

		if (userId != null) {
			queryStr.append(" and nf.userId =  :userId ");
			getQueryParams().put("userId", userId);
		}

		if (StringUtils.isNotBlank(companyShortName)) {
			queryStr.append(" and nf.companyShortName =  :companyShortName ");
			getQueryParams().put("companyShortName", companyShortName);
		}

		return queryStr.toString();
	}

	@Override
	protected List<SortField> getSortFields() {
		List<SortField> sortFields = new ArrayList<SortField>();
		sortFields.add(new SortField("priority", "priority"));

		return sortFields;
	}

	@Override
	protected String getAliasToApply() {
		//specifying nt as we want this to be sorted on priority which is present in notificationType ie nt
		return "nt";
	}
}
