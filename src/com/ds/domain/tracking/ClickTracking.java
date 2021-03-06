package com.ds.domain.tracking;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.campaign.Campaign;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.marketing.MarketingMaterialType;
import com.ds.domain.visitor.VisitorInfo;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author adlakha.vaibhav
 */
@Entity
@Table(name = "click_tracking")
public class ClickTracking {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MARKETING_MATERIAL_TYPE_ID", nullable = false)
  private MarketingMaterialType marketingMaterialType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MARKETING_MATERIAL_ID", nullable = false)
  private MarketingMaterial marketingMaterial;

  @Column(name = "COMPANY_SHORT_NAME", nullable = false, length = 50)
  private String companyShortName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CAMPAIGN_ID", nullable = false)
  private Campaign campaign;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AFFILIATE_ID", nullable = false)
  private Affiliate affiliate;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "VISITOR_ACTIVITY_DETAILS_ID", nullable = false)
  private VisitorInfo visitorInfo;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MarketingMaterialType getMarketingMaterialType() {
    return marketingMaterialType;
  }

  public void setMarketingMaterialType(MarketingMaterialType marketingMaterialType) {
    this.marketingMaterialType = marketingMaterialType;
  }

  public MarketingMaterial getMarketingMaterial() {
    return marketingMaterial;
  }

  public void setMarketingMaterial(MarketingMaterial marketingMaterial) {
    this.marketingMaterial = marketingMaterial;
  }

  public Campaign getCampaign() {
    return campaign;
  }

  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
  }

  public Affiliate getAffiliate() {
    return affiliate;
  }

  public void setAffiliate(Affiliate affiliate) {
    this.affiliate = affiliate;
  }

  public VisitorInfo getVisitorInfo() {
    return visitorInfo;
  }

  public void setVisitorInfo(VisitorInfo visitorInfo) {
    this.visitorInfo = visitorInfo;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }
}
