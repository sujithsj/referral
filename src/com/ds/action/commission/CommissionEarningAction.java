package com.ds.action.commission;

import com.ds.constants.AppConstants;
import com.ds.constants.EnumCommissionEarningStatus;
import com.ds.core.event.listener.MMCommissionProcessor;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.campaign.Campaign;
import com.ds.domain.commission.CommissionEarning;
import com.ds.domain.commission.CommissionEarningStatus;
import com.ds.domain.user.User;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.campaign.CampaignService;
import com.ds.pact.service.commission.CommissionEarningService;
import com.ds.security.helper.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 16, 2013
 * Time: 11:34:05 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CommissionEarningAction {

  private Long campaignId;
  private Double revenue;
  private Long affiliateId;
  private Double earning;

  //private String customerId;
  //private String uniqueId; //  this is the transaction Id
  //private double revenueToUseForEarning;

  private List<Campaign> campaignsForCompany;

  @Autowired
  private AffiliateService affiliateService;
  @Autowired
  private CampaignService campaignService;
  @Autowired
  private CommissionEarningService commissionEarningService;
  @Autowired
  private BaseDao baseDao;


  private void pre() {
    User loggedInUser = SecurityHelper.getLoggedInUser();
    campaignsForCompany = getCampaignService().getAllCampaigns(loggedInUser.getCompanyShortName());
  }

  public void addCommission() {

    if (revenue == null && earning == null) {
      //error, validate on UI
      return;
    }

    User loggedInUser = SecurityHelper.getLoggedInUser();
    Affiliate affiliate = getAffiliateService().getAffiliate(affiliateId);
    Campaign campaign = getCampaignService().getCampaignById(campaignId);
    //revenueToUseForEarning = revenue != null ? revenue : earning;
    //EventTracking eventTracking = getEventTracking(affiliate, campaign, loggedInUser.getCompanyShortName());
    //eventTracking = (EventTracking) getBaseDao().save(eventTracking);

    CommissionEarning commissionEarning = null;
    if (earning == null) {
      commissionEarning = new MMCommissionProcessor(revenue, null, affiliate, campaign).process();
    } else {
      commissionEarning = new CommissionEarning();
      commissionEarning.setCampaign(campaign);
      commissionEarning.setCompanyShortName(campaign.getCompanyShortName());
      commissionEarning.setAffiliate(affiliate);
      CommissionEarningStatus commissionEarningStatus = EnumCommissionEarningStatus.APPROVED.asCommissionEarningStatus();
      commissionEarning.setCommissionEarningStatus(commissionEarningStatus);
      //commissionEarning.setEventTracking(eventTracking);
      commissionEarning.setDirectCommission(true);
      commissionEarning.setRecurCommission(false);
      User systemUser = getBaseDao().get(User.class, AppConstants.SYS_USER_ID);
      commissionEarning.setActedBy(systemUser);
    }

    if (commissionEarning != null) {
      getCommissionEarningService().saveCommissionEarning(commissionEarning);
    }

  }


  /*private EventTracking getEventTracking(Affiliate affiliate, Campaign campaign, String companyShortName) {
    EventTracking eventTracking = new EventTracking();
    eventTracking.setRevenue(revenueToUseForEarning);
    eventTracking.setCustomerId(customerId);
    eventTracking.setUniqueId(uniqueId);
    eventTracking.setAffiliate(affiliate);
    eventTracking.setCampaign(campaign);
    eventTracking.setCompanyShortName(companyShortName);

    return eventTracking;
  }*/


  public AffiliateService getAffiliateService() {
    return affiliateService;
  }

  public CampaignService getCampaignService() {
    return campaignService;
  }

  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }

  public Double getRevenue() {
    return revenue;
  }

  public void setRevenue(Double revenue) {
    this.revenue = revenue;
  }

/*
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
*/

  public Long getAffiliateId() {
    return affiliateId;
  }

  public void setAffiliateId(Long affiliateId) {
    this.affiliateId = affiliateId;
  }

/*
  public String getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }
*/

  public Double getEarning() {
    return earning;
  }

  public void setEarning(Double earning) {
    this.earning = earning;
  }

  public List<Campaign> getCampaignsForCompany() {
    return campaignsForCompany;
  }

  public void setCampaignsForCompany(List<Campaign> campaignsForCompany) {
    this.campaignsForCompany = campaignsForCompany;
  }

  public BaseDao getBaseDao() {
    return baseDao;
  }

  public CommissionEarningService getCommissionEarningService() {
    return commissionEarningService;
  }
}
