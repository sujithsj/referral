package com.ds.search.impl;

import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * @author adlakha.vaibhav
 */
public class UserQuery extends AbstractSearchQuery {


  private String username;
  private String email;


  public UserQuery setUsername(String username) {
    this.username = username;
    return this;
  }

  public UserQuery setEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  protected String getBaseQuery() {
   StringBuilder queryStr = new StringBuilder("select u from User u where 1=1 ");

    if (StringUtils.isNotBlank(username)) {
      queryStr.append(" and u.fullName like  :userName ");
      getQueryParams().put("userName", "%" + username + "%");
    }

    if (StringUtils.isNotBlank(email)) {
      queryStr.append(" and u.email like  :email ");
      getQueryParams().put("email", "%" + email + "%");
    }

    return queryStr.toString(); 
  }

  @Override
  protected List<SortField> getSortFields() {
    List<SortField> sortFields = new ArrayList<SortField>();
    sortFields.add(new SortField("un", "username"));
    sortFields.add(new SortField("em", "email"));

    return sortFields;  
  }

  @Override
  protected String getAliasToApply() {
    return "u";  
  }
}
