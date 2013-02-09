package com.ds.impl.service.dashboard;

import com.ds.pact.service.dashboard.CompanyDashboardService;
import com.ds.pact.dao.BaseDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 9, 2013
 * Time: 10:40:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CompanyDashboardServiceImpl implements CompanyDashboardService {

    @Autowired
    private BaseDao baseDao;


    @Override
    public int getTotalReferrersForCompany(String companyShortName) {
        String totalReferrersForCompanySql = "select count(*) from COMPANY_AFFILIATE where COMPANY_SHORTNAME = ?";

        List resultArr = getBaseDao().findByNativeSql(totalReferrersForCompanySql, 0, 0, companyShortName);

        if (resultArr != null && resultArr.size() > 0) {
            //Object result[] = resultArr.get(0);
            BigInteger count = (BigInteger)  resultArr.get(0);;
            if (count != null) {
                return count.intValue();
            }
        }
        return 0;
    }

    @Override
    public int getReferrersForCompanyInTimePeriod(String companyShortName, Date startDate, Date endDate) {
        return 0;
    }

    @Override
    public int getTotalNumberOfPendingCommissions(String companyShortName) {
        return 0;
    }

    @Override
    public long getTotalRevenueTrackedForCompany(String companyShortName) {
        return 0;
    }

    @Override
    public long getTotalCommissionTrackedForCompany(String companyShortName) {
        return 0;
    }

    public BaseDao getBaseDao() {
        return baseDao;
    }
}
