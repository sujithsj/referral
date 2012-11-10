package com.ds.domain.marketing;



import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "marketing_material")
@NamedQueries({
    @NamedQuery(name = "getMarketingMaterialById", query = "select mm from MarketingMaterial mm where mm.id = :mmId")
})
public class MarketingMaterial implements java.io.Serializable {


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


  @Column(name = "FILE_ATTACHMENT_ID")
  private Long fileAttachmentId;


  @Column(name = "COMPANY_SHORTNAME", nullable = false, length = 50)
  private String companyShortname;


  @Column(name = "LANDING_PAGE_URL", length = 1000)
  private String landingPageUrl;

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

  public Long getFileAttachmentId() {
    return this.fileAttachmentId;
  }

  public void setFileAttachmentId(Long fileAttachmentId) {
    this.fileAttachmentId = fileAttachmentId;
  }

  public String getCompanyShortname() {
    return this.companyShortname;
  }

  public void setCompanyShortname(String companyShortname) {
    this.companyShortname = companyShortname;
  }

  public String getLandingPageUrl() {
    return this.landingPageUrl;
  }

  public void setLandingPageUrl(String landingPageUrl) {
    this.landingPageUrl = landingPageUrl;
  }


}


