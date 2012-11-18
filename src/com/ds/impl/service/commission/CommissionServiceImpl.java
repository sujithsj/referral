package com.ds.impl.service.commission;

import com.ds.domain.commission.CommissionPlan;
import com.ds.domain.commission.CommissionStrategy;
import com.ds.dto.commission.CommissionPlanDTO;
import com.ds.exception.InvalidParameterException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.commission.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public CommissionPlan createCommissionPlan(CommissionPlanDTO commissionPlanDTO, String companyShortName) {
    if (commissionPlanDTO.getCommissionStrategyId() == null) {
      throw new InvalidParameterException("COMMISSION_STATEGY_CANNOT_BE_NULL");
    }
    CommissionPlan commissionPlan = commissionPlanDTO.extractCommissionPlan();
    /**
     * TODO: a lot of validations need to
     * be done based on type of campaing and type of currency chosen, whether tiered applicable or not,
     *
     */

    if (commissionPlan.isAutoApproveComm() == null) {
      commissionPlan.setAutoApproveComm(true);
    }
    commissionPlan.setCompanyShortName(companyShortName);


    return null;
  }


  private CommissionStrategy getCommissionStrategy(Long commissionStartegyId){
      return null;
  }

  @Override
  public void updateCommissionPlan(CommissionPlan commissionPlan, CommissionPlanDTO commissionPlanDTO) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public BaseDao getBaseDao() {
    return baseDao;
  }
}
