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
public class AffiliateGroupQuery extends AbstractSearchQuery {


	//private String login;
	private String name;
	private String companyShortName;


	public AffiliateGroupQuery setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
		return this;
	}

	public AffiliateGroupQuery setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	protected String getBaseQuery() {
		StringBuilder queryStr = new StringBuilder("select afg from AffiliateGroup afg where 1=1 ");

		/*if (StringUtils.isNotBlank(login)) {
					queryStr.append(" and aff.login like  :login ");
					getQueryParams().put("login", "%" + login + "%");
				} */

		if (StringUtils.isNotBlank(name)) {
			queryStr.append(" and afg.name like  :name ");
			getQueryParams().put("name", "%" + name + "%");
		}

		if (StringUtils.isNotBlank(companyShortName)) {
			queryStr.append(" and afg.companyShortName  =  :companyShortName ");
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
		return "afg";
	}
}