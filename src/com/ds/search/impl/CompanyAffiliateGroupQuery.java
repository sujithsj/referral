package com.ds.search.impl;

import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 17, 2012
 * Time: 6:53:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateGroupQuery extends AbstractSearchQuery {


	//private String login;
	private String name;
	private String companyShortName;


	public CompanyAffiliateGroupQuery setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
		return this;
	}

	public CompanyAffiliateGroupQuery setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	protected String getBaseQuery() {
		StringBuilder queryStr = new StringBuilder("select cafg from CompanyAffiliateGroup cafg where 1=1 ");

		if (StringUtils.isNotBlank(name)) {
			queryStr.append(" and cafg.name like  :name ");
			getQueryParams().put("name", "%" + name + "%");
		}

		if (StringUtils.isNotBlank(companyShortName)) {
			queryStr.append(" and cafg.companyShortName  =  :companyShortName ");
			getQueryParams().put("companyShortName", companyShortName);
		}

		return queryStr.toString();
	}

	@Override
	protected List<SortField> getSortFields() {
		List<SortField> sortFields = new ArrayList<SortField>();
		sortFields.add(new SortField("name", "name"));

		return sortFields;
	}

	@Override
	protected String getAliasToApply() {
		return "cafg";
	}
}