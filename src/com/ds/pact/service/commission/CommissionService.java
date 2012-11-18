package com.ds.pact.service.commission;

import com.ds.domain.commission.CommissionPlan;
import com.ds.dto.commission.CommissionPlanDTO;

/**
 * @author adlakha.vaibhav
 */
public interface CommissionService {

  public CommissionPlan getCommissionPlanById(Long commissionPlanId);

  public CommissionPlan createCommissionPlan(CommissionPlanDTO commissionPlanDTO, String companyShortName);

  public void updateCommissionPlan(CommissionPlan commissionPlan, CommissionPlanDTO commissionPlanDTO);
}
