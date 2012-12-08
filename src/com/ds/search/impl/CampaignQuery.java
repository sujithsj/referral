package com.ds.search.impl;

import com.ds.constants.EnumCampaignType;
import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class CampaignQuery extends AbstractSearchQuery {

  private String name;
  private String companyShortName;
  private boolean active = true;
  private boolean isPrivate = false;
  private boolean isQueryForAffiliate = false;
  private Date startDate;
  private Date endDate;
  private EnumCampaignType campaignType;

  public CampaignQuery setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
    return this;
  }

  public CampaignQuery setName(String name) {
    this.name = name;
    return this;
  }


  public CampaignQuery setActive(boolean active) {
    this.active = active;
    return this;
  }

  public CampaignQuery setPrivate(boolean aPrivate) {
    isPrivate = aPrivate;
    return this;
  }

  public CampaignQuery setStartDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  public CampaignQuery setEndDate(Date endDate) {
    this.endDate = endDate;
    return this;
  }

  public CampaignQuery setCampaignType(EnumCampaignType campaignType) {
    this.campaignType = campaignType;
    return this;
  }

  public CampaignQuery setQueryForAffiliate(boolean queryForAffiliate) {
    isQueryForAffiliate = queryForAffiliate;
    return this;
  }

  @Override
  protected String getBaseQuery() {
    StringBuilder queryStr = new StringBuilder("select c from Campaign c where 1=1 ");

    if (StringUtils.isNotBlank(name)) {
      queryStr.append(" and c.name like  :name ");
      getQueryParams().put("name", "%" + name + "%");
    }

    if (StringUtils.isNotBlank(companyShortName)) {
      queryStr.append(" and c.companyShortName =  :companyShortName ");
      getQueryParams().put("companyShortName", companyShortName);
    }

    if (!isQueryForAffiliate && startDate != null && endDate != null) {
      queryStr.append(" and c.startDate >=  :startDate and c.endDate <=:endDate");
      getQueryParams().put("startDate", startDate);
      getQueryParams().put("endDate", endDate);
    }

    if(isQueryForAffiliate){
       queryStr.append(" and (c.endDate is null or c.endDate >=  :affiliateVisibleDate) ");
      getQueryParams().put("affiliateVisibleDate", new Date());
    }


    if (campaignType != null) {
      queryStr.append(" and c.campaignType.id =  :campaignTypeId ");
      getQueryParams().put("campaignTypeId", campaignType.getId());
    }

    queryStr.append(" and c.active =  :active ");
    queryStr.append(" and c.isPrivate =  :isPrivate ");
    getQueryParams().put("active", active);
    getQueryParams().put("isPrivate", isPrivate);


    return queryStr.toString();
  }

  @Override
  protected List<SortField> getSortFields() {
    List<SortField> sortFields = new ArrayList<SortField>();
    sortFields.add(new SortField("nm", "name"));

    return sortFields;
  }

  @Override
  protected String getAliasToApply() {
    return "c";
  }
}
