package com.ds.dto.company;

import com.ds.domain.company.Company;

/**
 * @author adlakha.vaibhav
 */
public class CompanyRegistrationDTO {

  private String name;
  private String shortName;
  private String description;
  private String url;
  private String forumUrl;

  private String password;
  private String email;
  private String userName;

  private String fromEmail;
  private long imageId;


  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the shortName
   */
  public String getShortName() {
    return shortName;
  }

  /**
   * @param shortName the shortName to set
   */
  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
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
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getForumUrl() {
    return forumUrl;
  }

  public void setForumUrl(String forumUrl) {
    this.forumUrl = forumUrl;
  }

  public Company extractCompany() {

    Company company = new Company();
    company.setName(this.name);
    company.setDescription(this.description);
    company.setUrl(this.url);
    company.setShortName(this.shortName);

    return company;
  }

  public void bindCompany(Company company) {
    this.name = company.getName();
    this.url = company.getUrl();
    this.description = company.getDescription();
    this.shortName = company.getShortName();

    if (company.getLogo() != null) {
      this.imageId = company.getLogo().getId();
    }
  }

  public long getImageId() {
    return imageId;
  }

  public void setImageId(long imageId) {
    this.imageId = imageId;
  }
}
