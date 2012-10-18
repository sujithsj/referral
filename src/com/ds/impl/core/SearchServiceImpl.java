package com.ds.impl.core;

import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.core.SearchService;
import com.ds.search.query.SearchQuery;
import com.ds.web.action.Page;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
@Service
public class SearchServiceImpl implements SearchService {

  private static final int MAX_RESULT_SIZE = 1000;

  @Autowired
  private BaseDao baseDao;


  @SuppressWarnings("rawtypes")
  @Override
  public List executeSearch(SearchQuery searchQuery) {

    Query query = getBaseDao().createQuery(searchQuery.getQueryString());
    applyQueryParams(searchQuery, query);
    setResultConstraints(searchQuery, query);


    System.out.println("query:" + query);
    return query.list();
  }

  @Override
  public Page list(SearchQuery searchQuery) {
    int count = getCountForSearch(searchQuery);
    List results = executeSearch(searchQuery);

    return new Page(results, searchQuery.getRows(), searchQuery.getPageNo(), count);
  }

  @Override
  public int getCountForSearch(SearchQuery searchQuery) {

    Query query = getBaseDao().createQuery(searchQuery.getQueryString());
    applyQueryParams(searchQuery, query);

    return query.list().size();
  }

  private void applyQueryParams(SearchQuery searchQuery, Query query) {
    Map<String, Object> params = searchQuery.getQueryParams();

    Iterator<Map.Entry<String, Object>> paramsItr = params.entrySet().iterator();

    while (paramsItr.hasNext()) {
      Map.Entry<String, Object> entry = paramsItr.next();
      query.setParameter(entry.getKey(), entry.getValue());
    }
  }

  private void setResultConstraints(SearchQuery searchQuery, Query query) {
    Map<String, Object> resultConstraints = searchQuery.getResultConstratins();
    int pageNo = 0, rows = 0;

    if (resultConstraints.get(SearchQuery.PAGE_NO_KEY) != null) {
      pageNo = (Integer) resultConstraints.get(SearchQuery.PAGE_NO_KEY);
    }
    if (resultConstraints.get(SearchQuery.ROWS_KEY) != null) {
      rows = (Integer) resultConstraints.get(SearchQuery.ROWS_KEY);
    }

    if (pageNo > 0) {
      int perPage = rows > 0 ? rows : Page.DEFAULT_PAGE_SIZE;
      int firstResult = (pageNo - 1) * perPage;
      query.setFirstResult(firstResult);
    }
    if (rows != 0) {
      query.setMaxResults(rows);
    } else {
      query.setMaxResults(MAX_RESULT_SIZE);
    }
  }

  public BaseDao getBaseDao() {
    return baseDao;
  }
}
