package com.ds.domain.core;

import com.ds.domain.company.Company;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author adlakha.vaibhav
 */
public class GAAuthInfo {
  private Long id;
  private String userName;
  private String password;
  private Company company;
  private String profileName; // like blog.webscale.co.in
  private String tableId;
  private String accessToken;
  private String tokenSecret;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the company
   */
  public Company getCompany() {
    return company;
  }

  /**
   * @param company the company to set
   */
  public void setCompany(Company company) {
    this.company = company;
  }

  /**
   * @return the profileName
   */
  public String getProfileName() {
    return profileName;
  }

  /**
   * @param profileName the profileName to set
   */
  public void setProfileName(String profileName) {
    this.profileName = profileName;
  }

  /**
   * @return the accessToken
   */
  public String getAccessToken() {
    return accessToken;
  }

  /**
   * @param accessToken the accessToken to set
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  /**
   * @return the tableId
   */
  public String getTableId() {
    return tableId;
  }

  /**
   * @param tableId the tableId to set
   */
  public void setTableId(String tableId) {
    this.tableId = tableId;
  }

  /**
   * @return the tokenSecret
   */
  public String getTokenSecret() {
    return tokenSecret;
  }

  /**
   * @param tokenSecret the tokenSecret to set
   */
  public void setTokenSecret(String tokenSecret) {
    this.tokenSecret = tokenSecret;
  }

  @Override
  public int hashCode() {
    if (getId() != null && getId() > 0) {
      return new HashCodeBuilder().append(getId()).toHashCode();
    }
    return new HashCodeBuilder().append(getCompany().getShortName()).append(getUserName()).append(getPassword()).append(getProfileName()).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof GAAuthInfo == false)
      return false;

    GAAuthInfo other = (GAAuthInfo) obj;
    if (this.getId() != null && this.getId() > 0 && other.getId() != null && other.getId() > 0)
      return this.getId() == other.getId();

    return new EqualsBuilder().append(getCompany().getShortName(), other.getCompany().getShortName()).append(getUserName(), other.getUserName()).append(getProfileName(),
        other.getProfileName()).append(getPassword(), other.getPassword()).isEquals();
  }

}
