package com.ds.core.event.listener;

import com.ds.constants.AppConstants;
import com.ds.constants.EnumCommisionStrategy;
import com.ds.constants.EnumCommissionEarningStatus;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.campaign.Campaign;
import com.ds.domain.commission.CommissionEarning;
import com.ds.domain.commission.CommissionEarningStatus;
import com.ds.domain.commission.CommissionPlan;
import com.ds.domain.commission.CommissionStrategy;
import com.ds.domain.user.User;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.dao.BaseDao;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 22, 2013
 * Time: 10:39:02 PM
 * To change this template use File | Settings | File Templates.
 */

public class MMCommissionProcessor {

  private Affiliate affiliate;
  private Campaign campaign;

  private Long marketingMaterialId;
  private Double revenue;
  private String companyShortName;

  public MMCommissionProcessor(Double revenue, Long marketingMaterialId, Affiliate affiliate, Campaign campaign) {
    this.revenue = revenue;
    this.marketingMaterialId = marketingMaterialId;
    this.affiliate = affiliate;
    this.campaign = campaign;
    this.companyShortName = campaign.getCompanyShortName();
  }

  private BaseDao baseDao;

  public CommissionEarning process() {

    CommissionEarning commissionEarning = null;
    CommissionStrategy commissionStrategy = campaign.getCommissionPlan().getCommissionStrategy();
    Long commissionStrategyId = commissionStrategy.getId();

    if (EnumCommisionStrategy.ONE_TIME_FLAT_COMM.getId().equals(commissionStrategyId)) {
      commissionEarning = processOneTimeFlatCommission();
    } else if (EnumCommisionStrategy.ONE_TIME_REV_SHARE.getId().equals(commissionStrategyId)) {
      commissionEarning = processOneTimeRevenueShareCommission();
    } else if (EnumCommisionStrategy.RECUR_COMMISSION.getId().equals(commissionStrategyId)) {
      commissionEarning = processOneTimeFlatCommission();
    } else if (EnumCommisionStrategy.RECUR_REV_SHARE.getId().equals(commissionStrategyId)) {
      commissionEarning = processRecurRevenueShareCommission();
    }

    return commissionEarning;
  }


  private CommissionEarning processOneTimeFlatCommission() {
    List<CommissionEarning> existingCommissionEarnings = getExistingCommissionEarnings();
    CommissionEarning commissionEarning = null;

    /**
     * if this is the first time we are giving a commission to aff for this ad
     */
    if (existingCommissionEarnings == null || existingCommissionEarnings.size() > 0) {
      CommissionPlan commissionPlan = campaign.getCommissionPlan();
      double oneTimeFlatCommission = commissionPlan.getOneTimeCom();
      boolean isAutoApproved = commissionPlan.isAutoApproveComm();

      commissionEarning = getCommissionEarningWithBasicInfo();
      commissionEarning.setEarning(oneTimeFlatCommission);
      //commissionEarning.setApproved(isAutoApproved);

      setCommissionEarningStatus(commissionEarning, isAutoApproved);

    }
    return commissionEarning;
  }

  private void setCommissionEarningStatus(CommissionEarning commissionEarning, boolean autoApproved) {
    if (autoApproved) {
      CommissionEarningStatus commissionEarningStatus = EnumCommissionEarningStatus.APPROVED.asCommissionEarningStatus();
      commissionEarning.setCommissionEarningStatus(commissionEarningStatus);
    } else {
      CommissionEarningStatus commissionEarningStatus = EnumCommissionEarningStatus.PENDING_APPROVAL.asCommissionEarningStatus();
      commissionEarning.setCommissionEarningStatus(commissionEarningStatus);
    }
  }


  private CommissionEarning processOneTimeRevenueShareCommission() {
    List<CommissionEarning> existingCommissionEarnings = getExistingCommissionEarnings();
    CommissionEarning commissionEarning = null;

    /**
     * if this is the first time we are giving a commission to aff for this ad
     */
    if (existingCommissionEarnings == null || existingCommissionEarnings.size() > 0) {
      CommissionPlan commissionPlan = campaign.getCommissionPlan();

      //double revenue = eventTracking.getRevenue();
      double oneTimeRevSharePercentage = commissionPlan.getOneTimeCom();
      double oneTimeRevShareComm = (revenue * (oneTimeRevSharePercentage / 100));

      boolean isAutoApproved = commissionPlan.isAutoApproveComm();

      commissionEarning = getCommissionEarningWithBasicInfo();
      commissionEarning.setEarning(oneTimeRevShareComm);
      //commissionEarning.setApproved(isAutoApproved);
      setCommissionEarningStatus(commissionEarning, isAutoApproved);

    }

    return commissionEarning;
  }

  //TODO: direct and recur not set properly.
  private CommissionEarning processRecurCommission() {
    List<CommissionEarning> existingCommissionEarnings = getExistingCommissionEarnings();
    CommissionEarning commissionEarning = null;

    CommissionPlan commissionPlan = campaign.getCommissionPlan();
    boolean isAutoApproved = commissionPlan.isAutoApproveComm();

    Long limitRecurCommDays = commissionPlan.getLimitRecurCommDays();
    Long limitRecurTxn = commissionPlan.getLimitRecurCommTxn();

    boolean shouldAwardRecurCommission = shouldAwardRecurCommission(existingCommissionEarnings, limitRecurCommDays, limitRecurTxn);

    if (shouldAwardRecurCommission) {
      /**
       * if this is the first time we are giving a commission to aff for this ad
       */
      if (existingCommissionEarnings == null || existingCommissionEarnings.size() > 0) {
        commissionEarning = getCommissionEarningWithBasicInfo();

        /**
         * since this is the first time we will use initCom
         */

        double recurFlatComm = commissionPlan.getInitCom();
        commissionEarning.setEarning(recurFlatComm);

      } else {
        commissionEarning = getCommissionEarningWithBasicInfo();


        /**
         * since this is the later to first time we will use initCom
         */

        double recurFlatComm = commissionPlan.getRecurCom();
        commissionEarning.setEarning(recurFlatComm);
      }

      //commissionEarning.setApproved(isAutoApproved);
      setCommissionEarningStatus(commissionEarning, isAutoApproved);
    }

    return commissionEarning;
  }

  private CommissionEarning processRecurRevenueShareCommission() {
    List<CommissionEarning> existingCommissionEarnings = getExistingCommissionEarnings();
    CommissionEarning commissionEarning = null;

    CommissionPlan commissionPlan = campaign.getCommissionPlan();
    boolean isAutoApproved = commissionPlan.isAutoApproveComm();

    Long limitRecurCommDays = commissionPlan.getLimitRecurCommDays();
    Long limitRecurTxn = commissionPlan.getLimitRecurCommTxn();

    boolean shouldAwardRecurCommission = shouldAwardRecurCommission(existingCommissionEarnings, limitRecurCommDays, limitRecurTxn);

    if (shouldAwardRecurCommission) {
      /**
       * if this is the first time we are giving a commission to aff for this ad
       */
      if (existingCommissionEarnings == null || existingCommissionEarnings.size() > 0) {
        commissionEarning = getCommissionEarningWithBasicInfo();

        //double revenue = eventTracking.getRevenue();
        /**
         * since this is the first time we will use initCom
         */
        double recurRevSharePercentage = commissionPlan.getInitCom();
        double recurRevShareComm = (revenue * (recurRevSharePercentage / 100));
        commissionEarning.setEarning(recurRevShareComm);

      } else {
        commissionEarning = getCommissionEarningWithBasicInfo();

        //double revenue = eventTracking.getRevenue();
        /**
         * since this is the first time we will use initCom
         */
        double recurRevSharePercentage = commissionPlan.getRecurCom();
        double recurRevShareComm = (revenue * (recurRevSharePercentage / 100));
        commissionEarning.setEarning(recurRevShareComm);
      }

      //commissionEarning.setApproved(isAutoApproved);
      setCommissionEarningStatus(commissionEarning, isAutoApproved);
    }

    return commissionEarning;
  }

  private boolean shouldAwardRecurCommission(List<CommissionEarning> existingCommissionEarnings, Long limitRecurCommDays, Long limitRecurTxn) {
    boolean shouldAwardCommission = true;

    if (existingCommissionEarnings != null) {

      if (limitRecurTxn != null && limitRecurTxn == existingCommissionEarnings.size() - 1) {
        shouldAwardCommission = false;
      }

      if (limitRecurCommDays != null) {
        CommissionEarning firstCommission = existingCommissionEarnings.get(0);

        int daysSinceFirstCommission = daysBetween(firstCommission.getEarningDate(), new Date());

        if (daysSinceFirstCommission > limitRecurCommDays) {
          shouldAwardCommission = false;
        }
      }
    }

    return shouldAwardCommission;
  }

  @SuppressWarnings("unchecked")
  private List<CommissionEarning> getExistingCommissionEarnings() {
    if (marketingMaterialId != null) {

      return (List<CommissionEarning>) getBaseDao().findByNamedQueryAndNamedParam("getEarningForAffOnAd",
          new String[]{"mmId", "affId", "companyShortName"},
          new Object[]{marketingMaterialId, affiliate.getId(), companyShortName});

    } else {
      return (List<CommissionEarning>) getBaseDao().findByNamedQueryAndNamedParam("getEarningForAffOnCampaign",
          new String[]{"campaignId", "affId", "companyShortName"},
          new Object[]{campaign.getId(), affiliate.getId(), companyShortName});
    }
  }


  private CommissionEarning getCommissionEarningWithBasicInfo() {
    CommissionEarning commissionEarning = new CommissionEarning();
    commissionEarning.setCampaign(campaign);
    //commissionEarning.setMarketingMaterial(marketingMaterial);
    commissionEarning.setCompanyShortName(campaign.getCompanyShortName());
    commissionEarning.setAffiliate(affiliate);
    //commissionEarning.setEventTracking(eventTracking);
    commissionEarning.setDirectCommission(true);
    User systemUser = getBaseDao().get(User.class, AppConstants.SYS_USER_ID);
    commissionEarning.setActedBy(systemUser);

    return commissionEarning;
  }


  private int daysBetween(Date d1, Date d2) {
    return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
  }

  private BaseDao getBaseDao() {
    if (baseDao == null) {
      baseDao = (BaseDao) ServiceLocatorFactory.getService(BaseDao.class);
    }
    return baseDao;
  }


}
