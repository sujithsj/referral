package com.ds.domain.company;


import com.ds.domain.employee.Employee;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vaibhav.adlakha
 */
@Entity
@Table(name = "company")
public class Company implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "NAME", nullable = false)
  private String name;


  @Column(name = "SHORTNAME", nullable = false, length = 50)
  private String shortName;


  @Column(name = "VERIFICATIONTOKEN", length = 100)
  private String verificationToken;


  @Column(name = "ENABLED", length = 1)
  private Character enabled;


  @Column(name = "DESCRIPTION")
  private String description;


  @Column(name = "FROM_EMAIL")
  private String fromEmail;


  @Column(name = "URL", nullable = false, length = 500)
  private String url;


  @Column(name = "LOGO_FILE_ID")
  private Long logoFileId;


  @Column(name = "LOCK_VERSION", nullable = false)
  @Version
  private Long lockVersion;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
  private Set<Employee> employees = new HashSet<Employee>(0);

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
  private Set<CompanySettings> companySettingses = new HashSet<CompanySettings>(0);

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getVerificationToken() {
    return verificationToken;
  }

  public void setVerificationToken(String verificationToken) {
    this.verificationToken = verificationToken;
  }

  public Character getEnabled() {
    return this.enabled;
  }

  public void setEnabled(Character enabled) {
    this.enabled = enabled;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFromEmail() {
    return this.fromEmail;
  }

  public void setFromEmail(String fromEmail) {
    this.fromEmail = fromEmail;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Long getLogoFileId() {
    return this.logoFileId;
  }

  public void setLogoFileId(Long logoFileId) {
    this.logoFileId = logoFileId;
  }

  public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }

  public Set<Employee> getEmployees() {
    return this.employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  public Set<CompanySettings> getCompanySettingses() {
    return this.companySettingses;
  }

  public void setCompanySettingses(Set<CompanySettings> companySettingses) {
    this.companySettingses = companySettingses;
  }


}


