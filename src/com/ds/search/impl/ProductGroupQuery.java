package com.ds.search.impl;

import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * @author adlakha.vaibhav
 */
public class ProductGroupQuery extends AbstractSearchQuery {

  private String groupName;
  private String productName;
  private String companyProductId;
  private String companyShortName;

  public ProductGroupQuery setGroupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public ProductGroupQuery setProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public ProductGroupQuery setCompanyProductId(String companyProductId) {
    this.companyProductId = companyProductId;
    return this;
  }

  public ProductGroupQuery setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
    return this;
  }


  @Override
  protected String getBaseQuery() {
    StringBuilder queryStr = new StringBuilder("select pg from ProductGroup pg join fetch products p where 1=1 ");

    if (StringUtils.isNotBlank(companyShortName)) {
      queryStr.append(" and pg.companyShortName =  :companyShortName ");
      getQueryParams().put("companyShortName", companyShortName);
    }


    if (StringUtils.isNotBlank(groupName)) {
      queryStr.append(" and pg.name like  :name ");
      getQueryParams().put("name", "%" + groupName + "%");
    }

    if (StringUtils.isNotBlank(productName)) {
      queryStr.append(" and p.name like  :name ");
      getQueryParams().put("name", "%" + productName + "%");
    }

    if (StringUtils.isNotBlank(companyProductId)) {
      queryStr.append(" and p.cpid  =  :companyProductId ");
      getQueryParams().put("companyProductId", companyProductId);
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
    return "pg";
  }
}
