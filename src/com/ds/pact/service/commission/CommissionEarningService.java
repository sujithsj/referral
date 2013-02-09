package com.ds.pact.service.commission;

import com.ds.domain.commission.CommissionEarning;
import com.ds.web.action.Page;

import java.util.List;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public interface CommissionEarningService {

    //public List<CommissionEarning> getOneTimeDirectCommissionEarnings(Long campaignId, Long companyAffiliateId);

    public Page searchCommissionEarning(Long affiliateId, String companyShortName, Date startDate, Date endDate, Long commissionEarningStatusId, String customer, int pageNo, int perPage);

    public void changeCommissionEarningStatus(Long commissionEarningId, Long newStatusId);



}
