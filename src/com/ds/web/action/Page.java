package com.ds.web.action;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class Page {
  public static final int DEFAULT_PAGE_SIZE = 70;

  private List resultList;
  private int pageSize;
  private int page;
  private int totalPages;
  private int totalResults;

  
  public Page(List resultList, int pageSize, int page, int totalResults) {
    this.resultList = resultList;
    this.pageSize = pageSize;
    this.page = page;
    this.totalResults = totalResults;
    this.totalPages = (totalResults - 1) / pageSize + 1;
  }

  public Page(List resultList, int page, int totalResults) {
    this(resultList, DEFAULT_PAGE_SIZE, page, totalResults);
  }

  /**
   * this is a constructor which we can use to create a new page object with custom values it takes an existing page
   * and sets all parameters from that page. it also takes a List type erasure is happening here
   *
   * @param page
   * @param list
   */
  /*@SuppressWarnings({"unchecked", "hiding"})
  public Page(Page page, List list) {
    this.pageSize = page.getPageSize();
    this.page = page.getPage();
    this.totalPages = page.getTotalPages();
    this.totalResults = page.getTotalResults();
    // using this cast to circumvent weird compilation error
    // noinspection unchecked
    this.resultList = (List) list;
  }*/


  public int getTotalPageResults() {
    if (resultList != null) {
      return resultList.size();
    } else {
      return 0;
    }
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getPage() {
    return page;
  }

  public List getList() {
    return resultList;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public int getTotalResults() {
    return totalResults;
  }
}
