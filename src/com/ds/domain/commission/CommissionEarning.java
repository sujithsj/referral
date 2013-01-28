package com.ds.domain.commission;

import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.campaign.Campaign;
import com.ds.domain.tracking.EventTracking;
import com.ds.domain.user.User;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.notification.NotificationType;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */

@NamedQueries({
        @NamedQuery(name = "getEarningForAffOnAd", query = "select ce from CommissionEarning ce where ce.marketingMaterial.id = :mmId " +
                "and ce.affiliate.id = :affId and ce.companyShortName =:companyShortName order by ce.earningDate ")
})
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
    @JoinColumn(name = "AFFILIATE_ID", nullable = false)
    private Affiliate affiliate;

    @Column(name = "COMPANY_SHORT_NAME", nullable = false, length = 50)
    private String companyShortName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MARKETING_MATERIAL_ID", nullable = false)
    private MarketingMaterial marketingMaterial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_TRACKING_ID")
    private EventTracking eventTracking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTED_BY")
    private User actedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMISSION_EARNING_STATUS_ID", nullable = false)
    private CommissionEarningStatus commissionEarningStatus;

    @Column(name = "DIRECT_COMMISSION", nullable = false)
    private boolean directCommission;

    @Column(name = "RECUR_COMMISSION", nullable = false)
    private boolean recurCommission;

    @Column(name = "ACTED_ON")
    private Date actedOn;

    @Column(name = "EARNING_DATE")
    private Date earningDate;

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

    public EventTracking getEventTracking() {
        return eventTracking;
    }

    public void setEventTracking(EventTracking eventTracking) {
        this.eventTracking = eventTracking;
    }

    public User getActedBy() {
        return actedBy;
    }

    public void setActedBy(User actedBy) {
        this.actedBy = actedBy;
    }

    public CommissionEarningStatus getCommissionEarningStatus() {
        return commissionEarningStatus;
    }

    public void setCommissionEarningStatus(CommissionEarningStatus commissionEarningStatus) {
        this.commissionEarningStatus = commissionEarningStatus;
    }

    public Date getActedOn() {
        return actedOn;
    }

    public void setActedOn(Date actedOn) {
        this.actedOn = actedOn;
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

    public MarketingMaterial getMarketingMaterial() {
        return marketingMaterial;
    }

    public void setMarketingMaterial(MarketingMaterial marketingMaterial) {
        this.marketingMaterial = marketingMaterial;
    }

    public Date getEarningDate() {
        return earningDate;
    }

    public void setEarningDate(Date earningDate) {
        this.earningDate = earningDate;
    }
}
