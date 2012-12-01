package com.ds.domain.marketing;


import com.ds.domain.BaseDataObject;
import com.ds.domain.core.FileAttachment;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "marketing_material")
@NamedQueries({
    @NamedQuery(name = "getMarketingMaterialById", query = "select mm from MarketingMaterial mm where mm.id = :mmId")
})
public class MarketingMaterial extends BaseDataObject {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MARKETING_MATERIAL_TYPE_ID", nullable = false)
  private MarketingMaterialType marketingMaterialType;


  @Column(name = "TITLE", nullable = false, length = 1000)
  private String title;


  @Column(name = "BODY")
  private String body;


  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "FILE_ATTACHMENT_ID")
  private FileAttachment image;


  @Column(name = "COMPANY_SHORTNAME", nullable = false, length = 50)
  private String companyShortName;


  @Column(name = "LANDING_PAGE_URL", length = 1000)
  private String landingPageUrl;

  @Version
  @Column(name = "LOCK_VERSION", nullable = false)
  private Long version;


  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MarketingMaterialType getMarketingMaterialType() {
    return this.marketingMaterialType;
  }

  public void setMarketingMaterialType(MarketingMaterialType marketingMaterialType) {
    this.marketingMaterialType = marketingMaterialType;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }


  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public String getLandingPageUrl() {
    return this.landingPageUrl;
  }

  public void setLandingPageUrl(String landingPageUrl) {
    this.landingPageUrl = landingPageUrl;
  }

  public FileAttachment getImage() {
    return image;
  }

  public void setImage(FileAttachment image) {
    this.image = image;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }
}


