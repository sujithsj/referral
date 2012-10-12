package com.ds.domain.company;


import javax.persistence.*;

/**
 * @author vaibhav.adlakha
 */
/*@Entity
@Table(name = "company_settings")*/
public class CompanySettings implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMPANY_ID", nullable = false)
  private Company company;


  @Column(name = "GA_CUSTOM_VAR_NO")
  private Long gaCustomVarNo;


  @Column(name = "DEFAULT_PUBLISH", length = 1)
  private boolean defaultPublish;


  @Column(name = "COMPANY_SHORT_NAME", nullable = false, length = 50)
  private String companyShortName;


  @Column(name = "API_KEY", length = 500)
  private String apiKey;


  @Column(name = "DOMAIN_ALIAS_URL", length = 500)
  private String domainAliasUrl;


  @Column(name = "LOCK_VERSION", nullable = false)
  @Version
  private Long lockVersion;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Company getCompany() {
    return this.company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Long getGaCustomVarNo() {
    return this.gaCustomVarNo;
  }

  public void setGaCustomVarNo(Long gaCustomVarNo) {
    this.gaCustomVarNo = gaCustomVarNo;
  }

  public boolean isDefaultPublish() {
    return defaultPublish;
  }

  public void setDefaultPublish(boolean defaultPublish) {
    this.defaultPublish = defaultPublish;
  }

  public String getCompanyShortName() {
    return this.companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public String getApiKey() {
    return this.apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getDomainAliasUrl() {
    return this.domainAliasUrl;
  }

  public void setDomainAliasUrl(String domainAliasUrl) {
    this.domainAliasUrl = domainAliasUrl;
  }

  public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }


}


