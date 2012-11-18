package com.ds.search.impl;

import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class MarketingMaterialQuery extends AbstractSearchQuery {

  private String title;
  private Long type;
  private String landingPage;
  private String companyShortName;

  public MarketingMaterialQuery setTitle(String title) {
    this.title = title;
    return this;
  }

  public MarketingMaterialQuery setType(Long type) {
    this.type = type;
    return this;
  }

  public MarketingMaterialQuery setLandingPage(String landingPage) {
    this.landingPage = landingPage;
    return this;
  }

  public MarketingMaterialQuery setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
    return this;
  }


  @Override
  protected String getBaseQuery() {
    StringBuilder queryStr = new StringBuilder("select mm from MarketingMaterial mm  where 1=1 ");

    if (StringUtils.isNotBlank(companyShortName)) {
      queryStr.append(" and mm.companyShortName =  :companyShortName ");
      getQueryParams().put("companyShortName", companyShortName);
    }


    if (StringUtils.isNotBlank(title)) {
      queryStr.append(" and mm.title like  :title ");
      getQueryParams().put("title", "%" + title + "%");
    }

    if (StringUtils.isNotBlank(landingPage)) {
      queryStr.append(" and mm.landingPageUrl like  :landingPage ");
      getQueryParams().put("landingPage", "%" + landingPage + "%");
    }

    if (type != null) {
      queryStr.append(" and mm.marketingMaterialType.id  =  :type ");
      getQueryParams().put("type", type);
    }

    return queryStr.toString();
  }

  @Override
  protected List<SortField> getSortFields() {
    List<SortField> sortFields = new ArrayList<SortField>();
    sortFields.add(new SortField("title", "title"));

    return sortFields;
  }

  @Override
  protected String getAliasToApply() {
    return "mm";
  }
}
