package com.ds.pact.service.commission;

import com.ds.web.action.Page;
import com.ds.domain.commission.CommissionEarning;

import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public interface CommissionEarningService {

  public CommissionEarning saveCommissionEarning(CommissionEarning commissionEarning);

  //public List<CommissionEarning> getOneTimeDirectCommissionEarnings(Long campaignId, Long companyAffiliateId);

  public Page searchCommissionEarning(Long affiliateId, String companyShortName, Date startDate, Date endDate, Long commissionEarningStatusId, String customer, int pageNo, int perPage);

  public void changeCommissionEarningStatus(Long commissionEarningId, Long newStatusId);

  public void bulkChangeCommissionEarningStatus(List<Long> earningIds, Long newStatusId);


}
