package com.ds.domain.campaign;

import com.ds.domain.commission.CommissionPlan;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

@Entity
@Table(name = "campaign")
@NamedQueries({
    @NamedQuery(name = "getCampaignById", query = "select c from Campaign c where c.id = :campaignId")
})
public class Campaign implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CAMPAIGN_TYPE_ID", nullable = false)
  private CampaignType campaignType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMMISSION_PLAN_ID", nullable = false)
  private CommissionPlan commissionPlan;

  @Column(name = "COMPANY_SHORT_NAME", nullable = false, length = 50)
  private String companyShortName;


  @Column(name = "NAME", nullable = false, length = 500)
  private String name;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "START_DATE", length = 19)
  private Date startDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "END_DATE", length = 19)
  private Date endDate;


  @Column(name = "PRIVATE", length = 1, nullable = false)
  private boolean isPrivate =false;

  @Column(name = "ACTIVE", length = 1, nullable = false)
  private boolean active = true;

  @Column(name = "DELETED", length = 1, nullable = false)
  private boolean deleted = false;

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

  public CommissionPlan getCommissionPlan() {
    return commissionPlan;
  }

  public void setCommissionPlan(CommissionPlan commissionPlan) {
    this.commissionPlan = commissionPlan;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }
}


