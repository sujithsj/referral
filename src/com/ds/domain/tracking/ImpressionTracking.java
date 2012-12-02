package com.ds.domain.tracking;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.marketing.MarketingMaterialType;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
@Entity
@Table(name = "impression_tracking")
@NamedQueries({
    @NamedQuery(name = "getAdImpressionForCompanyAffiliateOnDate", query = "select it from ImpressionTracking it where it.marketingMaterial.id = :mmId and it.affiliate.id = :affId and it.companyShortName =:companyShortName and date(it.impressionDate) =:impressionDate")
})
public class ImpressionTracking {


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


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AFFILIATE_ID", nullable = false)
  private Affiliate affiliate;

  @Column(name = "COMPANY_SHORT_NAME", nullable = false, length = 50)
  private String companyShortName;


  @Column(name = "COUNT", nullable = false)
  private Double count;

  @Column(name = "IMPRESSION_DATE", nullable = false, length = 50)
  private Date impressionDate;


  @Version
  @Column(name = "LOCK_VERSION", nullable = false)
  private Long version;
  
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

  public Affiliate getAffiliate() {
    return affiliate;
  }

  public void setAffiliate(Affiliate affiliate) {
    this.affiliate = affiliate;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public Double getCount() {
    return count;
  }

  public void setCount(Double count) {
    this.count = count;
  }

  public Date getImpressionDate() {
    return impressionDate;
  }

  public void setImpressionDate(Date impressionDate) {
    this.impressionDate = impressionDate;
  }
}
