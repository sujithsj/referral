package com.ds.domain.user;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
public class UserKarmaProfile {

  private long id;
  private Long karmaPoints;
  private String userName;
  private String companyShortName;
  private Long karmaPointsConsumed;

  @XmlTransient
  private Set<String> badgeNames = new HashSet<String>();
  private Set<Long> rewards = new HashSet<Long>();

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
   * @return the karmaPoints
   */
  public Long getKarmaPoints() {
    return karmaPoints;
  }

  /**
   * @param karmaPoints the karmaPoints to set
   */
  public void setKarmaPoints(Long karmaPoints) {
    this.karmaPoints = karmaPoints;
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

  public void addBadgeName(String badgeName) {
    getBadgeNames().add(badgeName);
  }

  /**
   * @return the badgeNames
   */
  @XmlTransient
  public Set<String> getBadgeNames() {
    return badgeNames;
  }

  /**
   * @param badgeNames the badgeNames to set
   */
  @XmlTransient
  public void setBadgeNames(Set<String> badgeNames) {
    this.badgeNames = badgeNames;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof UserKarmaProfile == false)
      return false;

    UserKarmaProfile other = (UserKarmaProfile) obj;
    if (this.getId() > 0 && other.getId() > 0)
      return this.getId() == other.getId();

    return new EqualsBuilder().append(getKarmaPoints(),
        other.getKarmaPoints()).append(getCompanyShortName(),
        other.getCompanyShortName()).append(getUserName(),
        other.getUserName()).append(getBadgeNames(),
        other.getBadgeNames()).isEquals();
  }

  @Override
  public int hashCode() {
    if (getId() > 0) {
      return new HashCodeBuilder().append(getId()).toHashCode();
    }
    return new HashCodeBuilder().append(getKarmaPoints()).append(
        getUserName()).append(getBadgeNames()).append(
        getCompanyShortName()).toHashCode();
  }

  /**
   * @return the karmaPointsConsumed
   */
  public Long getKarmaPointsConsumed() {
    return karmaPointsConsumed;
  }

  /**
   * @param karmaPointsConsumed the karmaPointsConsumed to set
   */
  public void setKarmaPointsConsumed(Long karmaPointsConsumed) {
    this.karmaPointsConsumed = karmaPointsConsumed;
  }

  /**
   * @return the rewards
   */
  public Set<Long> getRewards() {
    return rewards;
  }

  /**
   * @param rewards the rewards to set
   */
  public void setRewards(Set<Long> rewards) {
    this.rewards = rewards;
	}
}
