package com.ds.domain.campaign;

import com.ds.domain.commission.CommissionPlan;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

@Entity
@Table(name = "campaign")
@NamedQueries({
        @NamedQuery(name = "getCampaignById", query = "select c from Campaign c where c.id = :campaignId"),
        @NamedQuery(name = "getAllCampaignsForCompany", query = "select c from Campaign c where c.companyShortName = :companyShortName"),
        @NamedQuery(name = "getAllCampaignsEligibleForAdForCompany", query = "select c from Campaign c where c.companyShortName = :companyShortName and c.campaignType.id <> 60")

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

    @Column(name = "LANDING_PAGE", length = 1000)
    private String landingPage;


    @Column(name = "NAME", nullable = false, length = 500)
    private String name;


    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE", length = 19)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", length = 19)
    private Date endDate;


    @Column(name = "PRIVATE", length = 1, nullable = false)
    private Boolean isPrivate = false;

    @Column(name = "ACTIVE", length = 1, nullable = false)
    private Boolean active = true;

    @Column(name = "DELETED", length = 1, nullable = false)
    private Boolean deleted = false;

    @Version
    @Column(name = "LOCK_VERSION", nullable = false)
    private Long version;

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


    public CommissionPlan getCommissionPlan() {
        return commissionPlan;
    }

    public void setCommissionPlan(CommissionPlan commissionPlan) {
        this.commissionPlan = commissionPlan;
    }

    public Boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }


    public Boolean getActive() {
        return active;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }
}


