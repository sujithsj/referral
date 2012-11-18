package com.ds.impl.service.commission;

import com.ds.domain.commission.CommissionCurrency;
import com.ds.domain.commission.CommissionPlan;
import com.ds.domain.commission.CommissionStrategy;
import com.ds.dto.commission.CommissionPlanDTO;
import com.ds.exception.InvalidParameterException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.commission.CommissionService;
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
    return (CommissionCurrency) getBaseDao().findUniqueByNamedQueryAndNamedParam("getCommissionCurrencyById", new String[]{"commissionCurrecnyId"}, new Object[]{commissionCurrecnyId});

  }


  public BaseDao getBaseDao() {
    return baseDao;
  }
}
