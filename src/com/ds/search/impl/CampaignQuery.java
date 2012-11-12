package com.ds.search.impl;

import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * @author adlakha.vaibhav
 */
public class CampaignQuery extends AbstractSearchQuery {

  private String name;
  private String companyShortName;

  public CampaignQuery setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
    return this;
  }

  public CampaignQuery setName(String name) {
    this.name = name;
    return this;
  }


  @Override
  protected String getBaseQuery() {
    StringBuilder queryStr = new StringBuilder("select c from Campaign c  where 1=1 ");

    if (StringUtils.isNotBlank(name)) {
      queryStr.append(" and c.name like  :name ");
      getQueryParams().put("name", "%" + name + "%");
    }

    if (StringUtils.isNotBlank(companyShortName)) {
      queryStr.append(" and c.companyShortName =  :companyShortName ");
      getQueryParams().put("companyShortName", companyShortName);
    }

    
    return queryStr.toString();
  }

  @Override
  protected List<SortField> getSortFields() {
    List<SortField> sortFields = new ArrayList<SortField>();
    sortFields.add(new SortField("name", "nm"));

    return sortFields;
  }

  @Override
  protected String getAliasToApply() {
    return "c";
  }
}
