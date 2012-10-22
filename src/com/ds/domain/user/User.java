package com.ds.domain.user;

import com.ds.domain.BaseDataObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * @author adlakha.vaibhav
 */
public class User extends BaseDataObject {

  private static final long serialVersionUID = 1L;
  private String password;
  private String username;                                                 // userId in db.
  // user name
  // since we are using emailId as
  // userName
  private String fullName;

  private Set<ThirdPartyAuth> thirdPartyAuths = new HashSet<ThirdPartyAuth>();

  @XmlTransient
  private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();


  private boolean accountNonExpired = true;
  private boolean accountNonLocked = true;
  private boolean credentialsNonExpired = true;
  private boolean enabled = false;


  private Set<String> roleNames = new HashSet<String>();

  private String email;
  private String originalImageUrl;
  private String thumbnailImageUrl;
  private String companyShortName;
  private Long karmaProfileId;
  private Long userSettingsId;
  private Long logoId;

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  @XmlTransient
  public void setAuthorities(Collection<GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  @XmlTransient
  public Collection<GrantedAuthority> getAuthorities() {
    return authorities;
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

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void addRoleName(String roleName) {
    getRoleNames().add(roleName);
  }

  @XmlTransient
  public Set<String> getRoleNames() {
    return roleNames;
  }

  @XmlTransient
  public void setRoleNames(Set<String> roleNames) {
    this.roleNames = roleNames;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void syncWith(User user, boolean syncRoles) {
    this.fullName = user.getFullName();
    this.email = user.getEmail();
    this.password = user.getPassword() != null && !StringUtils.isEmpty(user.getPassword()) ? user.getPassword() : this.password;

    this.accountNonExpired = user.isAccountNonExpired();
    this.accountNonLocked = user.isAccountNonLocked();
    this.credentialsNonExpired = user.isCredentialsNonExpired();
    this.enabled = user.isEnabled();

    setUserSettingsId(user.getUserSettingsId());
    if (syncRoles) {
      this.roleNames.clear();
      for (String roleName : user.getRoleNames()) {
        addRoleName(roleName);
      }
    }
  }

  /**
   * @return the originalImageUrl
   */
  public String getOriginalImageUrl() {
    return originalImageUrl;
  }

  /**
   * @param originalImageUrl the originalImageUrl to set
   */
  public void setOriginalImageUrl(String originalImageUrl) {
    this.originalImageUrl = originalImageUrl;
  }

  /**
   * @return the thumbnailImageUrl
   */
  public String getThumbnailImageUrl() {
    return thumbnailImageUrl;
  }

  /**
   * @param thumbnailImageUrl the thumbnailImageUrl to set
   */
  public void setThumbnailImageUrl(String thumbnailImageUrl) {
    this.thumbnailImageUrl = thumbnailImageUrl;
  }

  // /**
  // * @return the karmaProfile
  // */
  // public UserKarmaProfile getKarmaProfile() {
  // return karmaProfile;
  // }
  //
  // /**
  // * @param karmaProfile the karmaProfile to set
  // */
  // public void setKarmaProfile(UserKarmaProfile karmaProfile) {
  // this.karmaProfile = karmaProfile;
  // }
  //
  // /**
  // * @return the userSettings
  // */
  // public UserSettings getUserSettings() {
  // return userSettings;
  // }
  //
  // /**
  // * @param userSettings the userSettings to set
  // */
  // public void setUserSettings(UserSettings userSettings) {
  // this.userSettings = userSettings;
  // }

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
   * @return the karmaProfileId
   */
  public Long getKarmaProfileId() {
    return karmaProfileId;
  }

  /**
   * @param karmaProfileId the karmaProfileId to set
   */
  public void setKarmaProfileId(Long karmaProfileId) {
    this.karmaProfileId = karmaProfileId;
  }

  /**
   * @return the userSettingsId
   */
  public Long getUserSettingsId() {
    return userSettingsId;
  }

  /**
   * @param userSettingsId the userSettingsId to set
   */
  public void setUserSettingsId(Long userSettingsId) {
    this.userSettingsId = userSettingsId;
  }

  /**
   * @return the logoId
   */
  public Long getLogoId() {
    return logoId;
  }

  /**
   * @param logoId the logoId to set
   */
  public void setLogoId(Long logoId) {
    this.logoId = logoId;
  }

  public Set<ThirdPartyAuth> getThirdPartyAuths() {
    return thirdPartyAuths;
  }

  public void setThirdPartyAuths(Set<ThirdPartyAuth> thirdPartyAuths) {
    this.thirdPartyAuths = thirdPartyAuths;
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof User)) {
      return false;
    }
    User other = (User) obj;
    return new EqualsBuilder().append(getUsername(), other.getUsername()).isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getUsername()).toHashCode();
  }
}
