package com.ds.domain.company;


import com.ds.domain.BaseDataObject;
import com.ds.domain.affiliate.AffiliateCompany;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.core.Feature;
import com.ds.domain.core.FileAttachment;
import com.ds.domain.core.GAAuthInfo;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author vaibhav.adlakha
 */
/*
@Entity
@Table(name = "company")
*/
public class Company extends BaseDataObject {


  private String name;

  private String shortName;
  private String description;
  private String url;
  private String verificationToken;

  private String fromEmail;

  private boolean enabled;
  private GAAuthInfo gaAuthInfo;
  private Map<String, Integer> karmaProfile;
  private Map<String, Integer> badges;
  private FileAttachment logo;

  private boolean vendor;

  private CompanySettings companySettings;
	private Set<Affiliate> affiliates;
	private Set<AffiliateCompany> affiliateCompanies;

  // we can cache it its not big data, at max lets say 10 features
  private Set<Feature> features = new HashSet<Feature>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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
   * @return the enabled
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * @return the verificationToken
   */
  @XmlTransient
  public String getVerificationToken() {
    return verificationToken;
  }

  /**
   * @param verificationToken the verificationToken to set
   */
  @XmlTransient
  public void setVerificationToken(String verificationToken) {
    this.verificationToken = verificationToken;
  }

  /**
   * @return the gaAuthInfo
   */
  @XmlTransient
  public GAAuthInfo getGaAuthInfo() {
    return gaAuthInfo;
  }

  /**
   * @param gaAuthInfo the gaAuthInfo to set
   */
  @XmlTransient
  public void setGaAuthInfo(GAAuthInfo gaAuthInfo) {
    this.gaAuthInfo = gaAuthInfo;
  }

  public void syncWith(Company company) {
    this.name = company.getName();
    this.url = company.getUrl();
    this.description = company.getDescription();
  }

  /**
   * @return the karmaProfile
   */
  @XmlTransient
  public Map<String, Integer> getKarmaProfile() {
    return karmaProfile;
  }

  /**
   * @param karmaProfile the karmaProfile to set
   */
  @XmlTransient
  public void setKarmaProfile(Map<String, Integer> karmaProfile) {
    this.karmaProfile = karmaProfile;
  }

  public boolean equals(Object obj) {
    if (obj instanceof Company == false)
      return false;

    Company other = (Company) obj;

    return new EqualsBuilder().append(getShortName(), other.getShortName()).isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getShortName()).toHashCode();
  }

  /**
   * @return the fromEmail
   */
  public String getFromEmail() {
    return fromEmail;
  }

  /**
   * @param fromEmail the fromEmail to set
   */
  public void setFromEmail(String fromEmail) {
    this.fromEmail = fromEmail;
  }

  /**
   * @return the logo
   */
  public FileAttachment getLogo() {
    return logo;
  }

  /**
   * @param logo the logo to set
   */
  public void setLogo(FileAttachment logo) {
    this.logo = logo;
  }

  /**
   * @return the companySettings
   */
  public CompanySettings getCompanySettings() {
    return companySettings;
  }

  /**
   * @param companySettings the companySettings to set
   */
  public void setCompanySettings(CompanySettings companySettings) {
    this.companySettings = companySettings;
  }

  /**
   * @return the features
   */
  public Set<Feature> getFeatures() {
    return features;
  }

  /**
   * @param features the features to set
   */
  public void setFeatures(Set<Feature> features) {
    this.features = features;
  }

  public void addFeature(Feature feature) {
    getFeatures().add(feature);
  }

  /**
   * @return the badges
   */
  public Map<String, Integer> getBadges() {
    return badges;
  }

  /**
   * @param badges the badges to set
   */
  public void setBadges(Map<String, Integer> badges) {
    this.badges = badges;
  }

  /**
   * @return the vendor
   */
  public boolean isVendor() {
    return vendor;
  }

  /**
   * @param vendor the vendor to set
   */
  public void setVendor(boolean vendor) {
    this.vendor = vendor;
  }

	public Set<Affiliate> getAffiliates() {
		return affiliates;
	}

	public void setAffiliates(Set<Affiliate> affiliates) {
		this.affiliates = affiliates;
	}

	public Set<AffiliateCompany> getAffiliateCompanies() {
		return affiliateCompanies;
	}

	public void setAffiliateCompanies(Set<AffiliateCompany> affiliateCompanies) {
		this.affiliateCompanies = affiliateCompanies;
	}
}


