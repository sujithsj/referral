package com.ds.search.query;

/**
 * @author adlakha.vaibhav
 */
public enum SortOrder {

  ASC("asc"),
  DSC("desc");

  private String sortOrder;

  private SortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
  }

  public String getSortOrder() {
    return sortOrder;
  }
}
