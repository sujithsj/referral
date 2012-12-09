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
public class CompanyAffiliateInviteQuery extends AbstractSearchQuery {


	private String companyShortName;
	private String affiliateEmail;

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public void setAffiliateEmail(String affiliateEmail) {
		this.affiliateEmail = affiliateEmail;
	}

	@Override
	protected String getBaseQuery() {
		StringBuilder queryStr = new StringBuilder("select cai from CompanyAffiliateInvite cai where converted = 0 ");

		if (StringUtils.isNotBlank(companyShortName)) {
			queryStr.append(" and cai.companyShortName  =  :companyShortName ");
			getQueryParams().put("companyShortName", companyShortName);
		}
		if (StringUtils.isNotBlank(affiliateEmail)) {
			queryStr.append(" and cai.affiliateEmail  =  :affiliateEmail ");
			getQueryParams().put("affiliateEmail", affiliateEmail);
		}


		return queryStr.toString();
	}

	@Override
	protected List<SortField> getSortFields() {
		List<SortField> sortFields = new ArrayList<SortField>();
		sortFields.add(new SortField("id", "id"));
		return sortFields;
	}

	@Override
	protected String getAliasToApply() {
		return "cai";
	}

}