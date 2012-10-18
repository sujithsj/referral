package com.ds.search.query;

import java.util.Map;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public interface SearchQuery {

  public static final String PAGE_NO_KEY = "pageNo";

  public static final String ROWS_KEY = "rows";

  /*public static final String CACHE_KEY_SEPARATOR = "-"; */

  public SearchQuery setOrderByField(String orderByField);

  public SearchQuery setOrderDirection(String orderDirection);

  public SearchQuery setPageNo(Integer pageNo);

  public SearchQuery setRows(Integer rows);

  /**
   * query string is specific to implementations.
   *
   * @return
   */
  public String getQueryString();

  /**
   * parameters will also be specific to implementation
   *
   * @return
   */
  public Map<String, Object> getQueryParams();

  /**
   * Get result size constraints viz. size and start for result list.
   *
   * @return
   */
  public Map<String, Object> getResultConstratins();

  /**
   * this will be the start of results, page no in case results are paginated
   * @return
   */
  public int getPageNo() ;


  /**
   * max number of rows that search query should return
   * @return
   */
  public int getRows() ;

  @SuppressWarnings("rawtypes")
  public List execute();


  public int count();
}
