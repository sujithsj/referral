package com.ds.impl.service.commission;

import com.ds.pact.service.commission.CommissionEarningService;
import com.ds.domain.commission.CommissionEarning;
import com.ds.web.action.Page;

import java.util.List;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public class CommissionEarningServiceImpl implements CommissionEarningService {

    @Override
    public Page searchCommissionEarning(Long affiliateId, String companyShortName, Date startDate, Date endDate, Long commissionEarningStatusId, String customer, int pageNo, int perPage) {

        return null;
    }
}
