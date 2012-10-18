package com.ds.pact.service.core;

import com.ds.search.query.SearchQuery;
import com.ds.web.action.Page;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public interface SearchService {

  @SuppressWarnings("rawtypes")
  public List executeSearch(SearchQuery searchQuery);

  public Page list(SearchQuery searchQuery);

  public int getCountForSearch(SearchQuery searchQuery);
}
