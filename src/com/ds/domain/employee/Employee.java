package com.ds.domain.employee;
// Generated Oct 1, 2012 8:48:06 PM by Hibernate Tools 3.2.4.CR1


import com.ds.domain.company.Company;
import com.ds.domain.BaseDataObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vaibhav.adlakha
 */
/*@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = "EMPLOYEEID"))*/
public class Employee extends BaseDataObject {


  /*@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)*/
  private Long id;

  /*@ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_SETTINGS_ID", nullable = false)*/
  private EmployeeSettings employeeSettings;

 /* @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMPANY_ID", nullable = false)*/
  private Company company;


  @Column(name = "EMPLOYEEID", unique = true, nullable = false)
  private String employeeId;


 /* @Column(name = "COMPANY_SHORT_NAME", nullable = false, length = 50)*/
  private String companyShortName;


  /*@Column(name = "PASSWORD", nullable = false)*/
  private String password;


  /*@Column(name = "FULLNAME", nullable = false)*/
  private String fullName;


  /*@Column(name = "ORIGINAL_IMAGE_URL")*/
  private String originalImageUrl;


  /*@Column(name = "THUMBNAIL_IMAGE_URL")*/
  private String thumbnailImageUrl;


  /*@Column(name = "ACCOUNT_NON_EXPIRED", length = 1)*/
  private boolean accountNonExpired;


  /*@Column(name = "ACCOUNT_NON_LOCKED", length = 1)*/
  private boolean accountNonLocked;


  /*@Column(name = "CREDENTIALS_NON_EXPIRED", length = 1)*/
  private boolean credentialsNonExpired;


  /*@Column(name = "EMAIL", nullable = false)*/
  private String email;


  /*@Column(name = "ENABLED", length = 1)*/
  private boolean enabled;


 /* @Column(name = "LOCK_VERSION", nullable = false)
  @Version
  private Long lockVersion;*/

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
  private Set<EmployeeSettings> employeeSettingses = new HashSet<EmployeeSettings>(0);

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EmployeeSettings getEmployeeSettings() {
    return this.employeeSettings;
  }

  public void setEmployeeSettings(EmployeeSettings employeeSettings) {
    this.employeeSettings = employeeSettings;
  }

  public Company getCompany() {
    return this.company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }


  public String getCompanyShortName() {
    return this.companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getOriginalImageUrl() {
    return this.originalImageUrl;
  }

  public void setOriginalImageUrl(String originalImageUrl) {
    this.originalImageUrl = originalImageUrl;
  }

  public String getThumbnailImageUrl() {
    return this.thumbnailImageUrl;
  }

  public void setThumbnailImageUrl(String thumbnailImageUrl) {
    this.thumbnailImageUrl = thumbnailImageUrl;
  }

  

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /*public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }*/

  public Set<EmployeeSettings> getEmployeeSettingses() {
    return this.employeeSettingses;
  }

  public void setEmployeeSettingses(Set<EmployeeSettings> employeeSettingses) {
    this.employeeSettingses = employeeSettingses;
  }


}


