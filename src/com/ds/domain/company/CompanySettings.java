package com.ds.domain.company;

import com.ds.domain.BaseDataObject;


/**
 * @author vaibhav.adlakha
 */
/*@Entity
@Table(name = "company_settings")*/
public class CompanySettings extends BaseDataObject{


  private long id;
  private long gaCustomVarNumber;
  private Company company;

  // whether by default we should publish all customer feebacks publicly, obviously it doesnt stop profanity filter
  // from being applied
  private boolean defaultPublish;

  private String apiKey;

  // Basically if company is using domain aliasing then we can use this in urls which we send out in emails and
  // internal navigation
  private String domainAliasUrl;

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return the gaCustomVarNumber
   */
  public long getGaCustomVarNumber() {
    return gaCustomVarNumber;
  }

  /**
   * @param gaCustomVarNumber the gaCustomVarNumber to set
   */
  public void setGaCustomVarNumber(long gaCustomVarNumber) {
    this.gaCustomVarNumber = gaCustomVarNumber;
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
   * @return the defaultPublish
   */
  public boolean isDefaultPublish() {
    return defaultPublish;
  }

  /**
   * @param defaultPublish the defaultPublish to set
   */
  public void setDefaultPublish(boolean defaultPublish) {
    this.defaultPublish = defaultPublish;
  }

  /**
   * @return the apiKey
   */
  public String getApiKey() {
    return apiKey;
  }

  /**
   * @param apiKey the apiKey to set
   */
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * @return the domainAliasUrl
   */
  public String getDomainAliasUrl() {
    return domainAliasUrl;
  }

  /**
   * @param domainAliasUrl the domainAliasUrl to set
   */
  public void setDomainAliasUrl(String domainAliasUrl) {
    this.domainAliasUrl = domainAliasUrl;
  }


}


