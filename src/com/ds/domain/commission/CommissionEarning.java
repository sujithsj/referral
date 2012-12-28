package com.ds.domain.commission;

import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.campaign.Campaign;
import com.ds.domain.tracking.EventTracking;
import com.ds.domain.user.User;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
@Entity
@Table(name = "commission_earning")
public class CommissionEarning implements Serializable {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CAMPAIGN_ID")
  private Campaign campaign;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMPANY_AFFILIATE_ID")
  private CompanyAffiliate companyAffiliate;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "EVENT_TRACKING_ID")
  private EventTracking eventTracking;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "APPROVED_BY")
  private User approvedBy;

  @Column(name = "APPROVED", nullable = false)
  private boolean approved;

  @Column(name = "DIRECT_COMMISSION", nullable = false)
  private boolean directCommission;

  @Column(name = "RECUR_COMMISSION", nullable = false)
  private boolean recurCommission;

  @Column(name = "APPROVED_ON")
  private Date approvedOn;

  @Column(name = "EARNING", nullable = false)
  private Double earning;

  @Version
  @Column(name = "LOCK_VERSION", nullable = false)
  private Long version;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Campaign getCampaign() {
    return campaign;
  }

  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
  }

  public CompanyAffiliate getCompanyAffiliate() {
    return companyAffiliate;
  }

  public void setCompanyAffiliate(CompanyAffiliate companyAffiliate) {
    this.companyAffiliate = companyAffiliate;
  }

  public EventTracking getEventTracking() {
    return eventTracking;
  }

  public void setEventTracking(EventTracking eventTracking) {
    this.eventTracking = eventTracking;
  }

  public User getApprovedBy() {
    return approvedBy;
  }

  public void setApprovedBy(User approvedBy) {
    this.approvedBy = approvedBy;
  }

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  public Date getApprovedOn() {
    return approvedOn;
  }

  public void setApprovedOn(Date approvedOn) {
    this.approvedOn = approvedOn;
  }

  public Double getEarning() {
    return earning;
  }

  public void setEarning(Double earning) {
    this.earning = earning;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public boolean isDirectCommission() {
    return directCommission;
  }

  public void setDirectCommission(boolean directCommission) {
    this.directCommission = directCommission;
  }

  public boolean isRecurCommission() {
    return recurCommission;
  }

  public void setRecurCommission(boolean recurCommission) {
    this.recurCommission = recurCommission;
  }
}
