package com.ds.pact.service.commission;

import com.ds.domain.commission.CommissionPlan;
import com.ds.dto.commission.CommissionPlanDTO;

/**
 * @author adlakha.vaibhav
 */
public interface CommissionService {

  public CommissionPlan getCommissionPlanById(Long commissionPlanId);

  public CommissionPlan createCommissionPlan(CommissionPlanDTO commissionPlanDTO, String companyShortName);

  public CommissionPlan updateCommissionPlan(Long commissionPlanId, CommissionPlanDTO commissionPlanDTO);


  public void creditCommissionToAffiliateForEvent(Long eventTrackingId);
}
