package com.ds.domain.campaign;



import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;


@Entity
@Table(name = "campaign")
public class Campaign implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CAMPAIGN_TYPE_ID", nullable = false)
  private CampaignType campaignType;


  @Column(name = "NAME", nullable = false, length = 500)
  private String name;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "START_DATE", length = 19)
  private Date startDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "END_DATE", length = 19)
  private Date endDate;


  @Column(name = "PRIVATE", length = 1)
  private boolean isPrivate ;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CampaignType getCampaignType() {
    return this.campaignType;
  }

  public void setCampaignType(CampaignType campaignType) {
    this.campaignType = campaignType;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return this.endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean aPrivate) {
    isPrivate = aPrivate;
  }
}


