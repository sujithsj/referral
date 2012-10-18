package com.ds.search.query;

/**
 * @author adlakha.vaibhav
 */
public class SortField {

  /**
     * actual field name in the model files
     */
    private String sortFieldName;
    /**
     * name that is referred on the UI/elseWhere
     */
    private String fieldName;

    public SortField(String fieldName, String sortFieldName){
        this.sortFieldName = sortFieldName;
        this.fieldName = fieldName;
    }

    public String getSortFieldName() {
        return sortFieldName;
    }

    public void setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
