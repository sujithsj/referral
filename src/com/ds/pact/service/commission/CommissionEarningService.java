package com.ds.pact.service.commission;

import com.ds.domain.commission.CommissionEarning;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public interface CommissionEarningService {

  public List<CommissionEarning> getOneTimeDirectCommissionEarnings(Long campaignId, Long companyAffiliateId);
}
