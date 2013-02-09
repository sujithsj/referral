package com.ds.pact.service.dashboard;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 9, 2013
 * Time: 6:26:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyDashboardService {

    public int getTotalReferrersForCompany(String companyShortName);

    public int getReferrersForCompanyInTimePeriod(String companyShortName, Date startDate, Date endDate);

    public int getTotalNumberOfPendingCommissions(String companyShortName);

    public long getTotalRevenueTrackedForCompany(String companyShortName);

    public long getTotalCommissionTrackedForCompany(String companyShortName);

}
