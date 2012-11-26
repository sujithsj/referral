package com.ds.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public class BaseDataObject implements Serializable {

  private String createdBy;
  private Date createdDate;
  private String updatedBy;
  private Date updatedDate;

  
  private long lockVersion = -1;

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public long getLockVersion() {
    return lockVersion;
  }

  public void setLockVersion(long lockVersion) {
    this.lockVersion = lockVersion;
  }

}

