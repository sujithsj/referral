package com.ds.search.impl;

import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 2:50:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateQuery extends AbstractSearchQuery {


  private String login;
  private String email;
	private String companyShortName;


  public AffiliateQuery setLogin(String login) {
    this.login = login;
    return this;
  }

  public AffiliateQuery setEmail(String email) {
    this.email = email;
    return this;
  }

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	@Override
  protected String getBaseQuery() {
   StringBuilder queryStr = new StringBuilder("select aff from Affiliate aff where 1=1 ");

    if (StringUtils.isNotBlank(login)) {
      queryStr.append(" and aff.login like  :login ");
      getQueryParams().put("login", "%" + login + "%");
    }

    if (StringUtils.isNotBlank(email)) {
      queryStr.append(" and aff.email like  :email ");
      getQueryParams().put("email", "%" + email + "%");
    }

	  if (StringUtils.isNotBlank(companyShortName)) {
      queryStr.append(" and aff.companyShortName  =  :companyShortName ");
      getQueryParams().put("companyShortName", companyShortName);
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
