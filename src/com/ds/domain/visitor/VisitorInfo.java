package com.ds.domain.visitor;

import com.ds.domain.BaseDataObject;

import javax.persistence.Transient;

/**
 * @author adlakha.vaibhav
 */
public class VisitorInfo extends BaseDataObject {

  private Long id;
  private String ipAddress;
  private String hostName;

  private String browserName;
  private String browserVersion;

  private String osName;
  private String osVersion;

  private String city;
  private String country;


  private String httpMethodType;

  private String visitorId;
  private String userName;
  private String companyShortName;


  // operation can be Create,View,Search
  private String operation;
  // item can be Post,Comment
  private String entity;
  //itemId will be the id of post or comment created.
  private String entityId;
  private String searchQuery;

  private String refererURL;

  private String isp;
  private String latitude;
  private String longitude;
  private String domain;

  /**
   * @return the ipAddress
   */
  public String getIpAddress() {
    return ipAddress;
  }

  /**
   * @param ipAddress the ipAddress to set
   */
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  /**
   * @return the hostName
   */
  public String getHostName() {
    return hostName;
  }

  /**
   * @param hostName the hostName to set
   */
  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  /**
   * @return the browserName
   */
  public String getBrowserName() {
    return browserName;
  }

  /**
   * @param browserName the browserName to set
   */
  public void setBrowserName(String browserName) {
    this.browserName = browserName;
  }

  /**
   * @return the browserVersion
   */
  public String getBrowserVersion() {
    return browserVersion;
  }

  /**
   * @param browserVersion the browserVersion to set
   */
  public void setBrowserVersion(String browserVersion) {
    this.browserVersion = browserVersion;
  }

  /**
   * @return the osName
   */
  public String getOsName() {
    return osName;
  }

  /**
   * @param osName the osName to set
   */
  public void setOsName(String osName) {
    this.osName = osName;
  }

  /**
   * @return the osVersion
   */
  public String getOsVersion() {
    return osVersion;
  }

  /**
   * @param osVersion the osVersion to set
   */
  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return the isp
   */
  public String getIsp() {
    return isp;
  }

  /**
   * @param isp the isp to set
   */
  public void setIsp(String isp) {
    this.isp = isp;
  }

  /**
   * @return the httpMethodType
   */
  public String getHttpMethodType() {
    return httpMethodType;
  }

  /**
   * @param httpMethodType the httpMethodType to set
   */
  public void setHttpMethodType(String httpMethodType) {
    this.httpMethodType = httpMethodType;
  }

  /**
   * @return the visitorId
   */
  public String getVisitorId() {
    return visitorId;
  }

  /**
   * @param visitorId the visitorId to set
   */
  public void setVisitorId(String visitorId) {
    this.visitorId = visitorId;
  }

  /**
   * @return the operation
   */
  public String getOperation() {
    return operation;
  }

  /**
   * @param operation the operation to set
   */
  public void setOperation(String operation) {
    this.operation = operation;
  }

  /**
   * @return the entity
   */
  public String getEntity() {
    return entity;
  }

  /**
   * @param entity the entity to set
   */
  public void setEntity(String entity) {
    this.entity = entity;
  }

  /**
   * @return the companyShortName
   */
  public String getCompanyShortName() {
    return companyShortName;
  }

  /**
   * @param companyShortName the companyShortName to set
   */
  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
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
   * @return the entityId
   */
  public String getEntityId() {
    return entityId;
  }

  /**
   * @param entityId the entityId to set
   */
  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  /**
   * @return the searchQuery
   */
  public String getSearchQuery() {
    return searchQuery;
  }

  /**
   * @param searchQuery the searchQuery to set
   */
  public void setSearchQuery(String searchQuery) {
    this.searchQuery = searchQuery;
  }


  /**
   * @return the latitude
   */
  public String getLatitude() {
    return latitude;
  }

  /**
   * @param latitude the latitude to set
   */
  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  /**
   * @return the longitude
   */
  public String getLongitude() {
    return longitude;
  }

  /**
   * @param longitude the longitude to set
   */
  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }


  /**
   * @return the domain
   */
  public String getDomain() {
    return domain;
  }

  /**
   * @param domain the domain to set
   */
  public void setDomain(String domain) {
    this.domain = domain;
  }


  /**
   * @return the refererURL
   */
  public String getRefererURL() {
    return refererURL;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @param refererURL the refererURL to set
   */
  public void setRefererURL(String refererURL) {
    this.refererURL = refererURL;
  }

  @Transient
  public Object[] getAllElementsInInfoAsArray() {
    return new Object[]{this.id, this.visitorId, this.userName, this.companyShortName, this.operation,
        this.entity, this.entityId, this.ipAddress, this.hostName, this.browserName, this.browserVersion, this.osName,
        this.osVersion, this.city, this.country, this.isp, this.httpMethodType,
        this.searchQuery, this.latitude, this.longitude, this.domain, this.refererURL};
  }

}
