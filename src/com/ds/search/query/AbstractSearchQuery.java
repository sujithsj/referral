package com.ds.search.query;

import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.core.SearchService;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public abstract class AbstractSearchQuery implements SearchQuery {
  private int pageNo;
  private int rows;
  private String orderByField;
  private String orderDirection;

  private Map<String, Object> queryParams = new HashMap<String, Object>();

  private SearchService searchService;

  public Map<String, Object> getQueryParams() {
    return queryParams;
  }

  protected abstract String getBaseQuery();

  /**
   * Fields for the query on which sort can be applied
   *
   * @return
   */
  protected abstract List<SortField> getSortFields();

  /**
   * Alias used in query on which sort will be applied.
   *
   * @return
   */
  protected abstract String getAliasToApply();


  public int getPageNo() {
    return pageNo;
  }

  public int getRows() {
    return rows;
  }

  @Override
  public SearchQuery setOrderByField(String orderByField) {
    this.orderByField = orderByField;
    return this;
  }

  @Override
  public SearchQuery setOrderDirection(String orderDirection) {
    this.orderDirection = orderDirection;
    return this;
  }

  @Override
  public SearchQuery setPageNo(Integer pageNo) {
    if (pageNo != null) {
      this.pageNo = pageNo;
    }
    return this;
  }

  @Override
  public SearchQuery setRows(Integer rows) {
    if (rows != null) {
      this.rows = rows;
    }
    return this;
  }

  @Override
  public String getQueryString() {
    StringBuilder queryStr = new StringBuilder(getBaseQuery());

    if (StringUtils.isNotEmpty(orderByField)) {
      for (SortField sortField : getSortFields()) {
        if (sortField.getFieldName().equalsIgnoreCase(orderByField)) {
          queryStr.append(" order by " + getAliasToApply() + "." + sortField.getSortFieldName());
        }
      }
      if (StringUtils.isNotEmpty(orderDirection)) {
        for (SortOrder sortOrder : SortOrder.values()) {
          if (sortOrder.getSortOrder().equalsIgnoreCase(orderDirection)) {
            queryStr.append(" " + sortOrder.getSortOrder());
          }
        }
      } else {
        queryStr.append(" " + SortOrder.ASC.getSortOrder());
      }
    }
    return queryStr.toString();
  }

  @Override
  public Map<String, Object> getResultConstratins() {
    Map<String, Object> resultConstraints = new HashMap<String, Object>(2);

    resultConstraints.put(PAGE_NO_KEY, pageNo);
    resultConstraints.put(ROWS_KEY, rows);

    return resultConstraints;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List execute() {
    return getSearchService().executeSearch(this);
  }

  @Override
  public int count() {
    return getSearchService().getCountForSearch(this);
  }

  public SearchService getSearchService() {
    if (searchService == null) {
      searchService = (SearchService) ServiceLocatorFactory.getService(SearchService.class);
    }

    return searchService;
  }
}
