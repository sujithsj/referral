package com.ds.impl.service.commission;

import com.ds.domain.campaign.Campaign;
import com.ds.domain.commission.CommissionCurrency;
import com.ds.domain.commission.CommissionPlan;
import com.ds.domain.commission.CommissionStrategy;
import com.ds.domain.tracking.EventTracking;
import com.ds.domain.affiliate.Affiliate;
import com.ds.dto.commission.CommissionPlanDTO;
import com.ds.exception.InvalidParameterException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.commission.CommissionService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.constants.EnumCommisionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adlakha.vaibhav
 */
@Service
public class CommissionServiceImpl implements CommissionService {

  @Autowired
  private BaseDao baseDao;

  @Autowired
  private CompanyAffiliateService companyAffiliateService;

  

  @Override
  public CommissionPlan getCommissionPlanById(Long commissionPlanId) {
    if (commissionPlanId == null) {
      throw new InvalidParameterException("COMMISSION_PLAN_ID_CANNOT_BE_BLANK");
    }
    return (CommissionPlan) getBaseDao().findUniqueByNamedQueryAndNamedParam("getCommissionPlanById", new String[]{"commissionPlanId"}, new Object[]{commissionPlanId});
  }

  @Override
  @Transactional
  public CommissionPlan updateCommissionPlan(Long commissionPlanId, CommissionPlanDTO commissionPlanDTO) {
    if (commissionPlanId == null) {
      throw new InvalidParameterException("COMMISSION_PLAN_ID_CANNOT_BE_BLANK");
    }

    CommissionPlan commissionPlan = getCommissionPlanById(commissionPlanId);

    if (commissionPlan == null) {
      throw new InvalidParameterException("NO_COMMSSION_PLAN_EXISTS_TO_UPDATE");
    }

    return persistCommissionPlan(commissionPlan, commissionPlanDTO);
  }


  @Override
  @Transactional
  public CommissionPlan createCommissionPlan(CommissionPlanDTO commissionPlanDTO, String companyShortName) {
    CommissionPlan commissionPlan = new CommissionPlan();
    commissionPlan.setCompanyShortName(companyShortName);
    return persistCommissionPlan(commissionPlan, commissionPlanDTO);
  }

  @Transactional
  private CommissionPlan persistCommissionPlan(CommissionPlan commissionPlan, CommissionPlanDTO commissionPlanDTO) {

    if (commissionPlanDTO.getCommissionStrategyId() == null) {
      throw new InvalidParameterException("COMMISSION_STATEGY_CANNOT_BE_NULL");
    }
    commissionPlanDTO.syncToCommissionPlan(commissionPlan);
    /**
     * TODO: lot of validations need to be done based on type of plan and startegry and currecny
     */
    if (commissionPlan.isAutoApproveComm() == null) {
      commissionPlan.setAutoApproveComm(true);
    }
    if(commissionPlan.isTiered() == null){
      commissionPlan.setTiered(false);
    }

    CommissionStrategy commissionStrategy = getCommissionStrategy(commissionPlanDTO.getCommissionStrategyId());
    commissionPlan.setCommissionStrategy(commissionStrategy);

    if (commissionPlanDTO.getCommissionCurrencyId() != null) {
      CommissionCurrency commissionCurrency = getCommissionCurrency(commissionPlanDTO.getCommissionCurrencyId());
      commissionPlan.setCommissionCurrency(commissionCurrency);
    }

    return (CommissionPlan) getBaseDao().save(commissionPlan);
  }

  private CommissionStrategy getCommissionStrategy(Long commissionStartegyId) {
    if (commissionStartegyId == null) {
      throw new InvalidParameterException("COMMISSION_STRATEGY_ID_CANNOT_BE_BLANK");
    }
    return (CommissionStrategy) getBaseDao().findUniqueByNamedQueryAndNamedParam("getCommissionStrategyById", new String[]{"commissionStartegyId"}, new Object[]{commissionStartegyId});

  }

  private CommissionCurrency getCommissionCurrency(Long commissionCurrecnyId) {
    if (commissionCurrecnyId == null) {
      throw new InvalidParameterException("COMMISSION_CURRENCY_ID_CANNOT_BE_BLANK");
    }
    return (CommissionCurrency) getBaseDao().findUniqueByNamedQueryAndNamedParam("getCommissionCurrencyById", new String[]{"commissionCurrencyId"}, new Object[]{commissionCurrecnyId});

  }

  @Override
  @Transactional
  public void creditCommissionToAffiliateForEvent(Long eventTrackingId){

    EventTracking eventTracking = getBaseDao().get(EventTracking.class, eventTrackingId);
    Campaign campaign = eventTracking.getCampaign();
    Affiliate affiliate = eventTracking.getAffiliate();

    CommissionStrategy commissionStrategy = campaign.getCommissionPlan().getCommissionStrategy();


    if(EnumCommisionStrategy.ONE_TIME_FLAT_COMM.getId().equals(commissionStrategy.getId())){
         // getCompanyAffiliateService().get
    }

    
  }


  public BaseDao getBaseDao() {
    return baseDao;
  }

  public CompanyAffiliateService getCompanyAffiliateService() {
    return companyAffiliateService;
  }
}
