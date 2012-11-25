package com.ds.search.impl;

import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 20, 2012
 * Time: 9:10:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateQuery extends AbstractSearchQuery {


	private String login;
	private String email;
	private String companyShortName;
	private Long excludingSelfId;


	public CompanyAffiliateQuery setLogin(String login) {
		this.login = login;
		return this;
	}

	public CompanyAffiliateQuery setEmail(String email) {
		this.email = email;
		return this;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public void setExcludingSelfId(Long excludingSelfId) {
		this.excludingSelfId = excludingSelfId;
	}

	@Override
	protected String getBaseQuery() {
		StringBuilder queryStr = new StringBuilder("select ca from CompanyAffiliate ca join ca.affiliate aff where 1=1 ");

		if (StringUtils.isNotBlank(login)) {
			queryStr.append(" and aff.login like  :login ");
			getQueryParams().put("login", "%" + login + "%");
		}

		if (StringUtils.isNotBlank(email)) {
			queryStr.append(" and aff.email like  :email ");
			getQueryParams().put("email", "%" + email + "%");
		}

		if (StringUtils.isNotBlank(companyShortName)) {
			queryStr.append(" and ca.companyShortName  =  :companyShortName ");
			getQueryParams().put("companyShortName", companyShortName);
		}

		if (excludingSelfId != null) {
			queryStr.append(" and ca.id != :excludingSelfId ");
			getQueryParams().put("excludingSelfId", excludingSelfId);
		}

		return queryStr.toString();
	}

	@Override
	protected List<SortField> getSortFields() {
		List<SortField> sortFields = new ArrayList<SortField>();
		sortFields.add(new SortField("login", "login"));
		sortFields.add(new SortField("email", "email"));

		return sortFields;
	}

	@Override
	protected String getAliasToApply() {
		return "aff";
	}

}
