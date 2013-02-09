package com.ds.impl.service.dashboard;

import com.ds.pact.service.dashboard.CompanyDashboardService;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.dao.report.SaleDao;
import com.ds.constants.EnumCommissionEarningStatus;
import com.ds.dto.dashboard.AffiliateByCommissionDto;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
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

    @Autowired
    private SaleDao saleDao;


    @Override
    public int getTotalReferrersForCompany(String companyShortName) {
        String totalReferrersForCompanySql = "select count(*) from COMPANY_AFFILIATE where COMPANY_SHORTNAME = ?";

        List resultArr = getBaseDao().findByNativeSql(totalReferrersForCompanySql, 0, 0, companyShortName);

        if (resultArr != null && resultArr.size() > 0) {
            //Object result[] = resultArr.get(0);
            BigInteger count = (BigInteger) resultArr.get(0);
            ;
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

        String totalNumberOfPendingCommissionsSql = "select count(*) from COMMISSION_EARNING where COMPANY_SHORT_NAME = ? and COMMISSION_EARNING_STATUS_ID = ?";

        List resultArr = getBaseDao().findByNativeSql(totalNumberOfPendingCommissionsSql, 0, 0, companyShortName, EnumCommissionEarningStatus.PENDING_APPROVAL.getId());

        if (resultArr != null && resultArr.size() > 0) {
            //Object result[] = resultArr.get(0);
            BigInteger count = (BigInteger) resultArr.get(0);
            ;
            if (count != null) {
                return count.intValue();
            }
        }

        return 0;
    }

    @Override
    public long getTotalRevenueTrackedForCompany(String companyShortName) {
        String totalRevenueSql = "select sum(REVENUE) from EVENT_TRACKING where COMPANY_SHORT_NAME = ?";

        List resultArr = getBaseDao().findByNativeSql(totalRevenueSql, 0, 0, companyShortName);

        if (resultArr != null && resultArr.size() > 0) {
            //Object result[] = resultArr.get(0);
            Double totalRevenue = (Double) resultArr.get(0);
            ;
            if (totalRevenue != null) {
                return totalRevenue.longValue();
            }
        }


        return 0;
    }

    @Override
    public long getPaidCommissionTrackedForCompany(String companyShortName) {
        String totalRevenueSql = "select sum(EARNING) from COMMISSION_EARNING where COMPANY_SHORT_NAME = ? and COMMISSION_EARNING_STATUS_ID = ?";

        List resultArr = getBaseDao().findByNativeSql(totalRevenueSql, 0, 0, companyShortName, EnumCommissionEarningStatus.PAID.getId());

        if (resultArr != null && resultArr.size() > 0) {
            //Object result[] = resultArr.get(0);
            Double totalPaidCommission = (Double) resultArr.get(0);
            ;
            if (totalPaidCommission != null) {
                return totalPaidCommission.longValue();
            }
        }

        return 0;
    }

    @Override
    public List<AffiliateByCommissionDto> getTopTenAffiliateByCommission(String companyShortName, Date startDate, Date endDate) {

        List<AffiliateByCommissionDto> results = new ArrayList<AffiliateByCommissionDto>();
        List<Object[]> topTenAffiliatesByCommission = getSaleDao().getTopTenAffiiliatesByCommission(companyShortName, startDate, endDate);

        for (Object[] row : topTenAffiliatesByCommission) {
            AffiliateByCommissionDto affiliateByCommissionDto = new AffiliateByCommissionDto();
            affiliateByCommissionDto.setAffiliateLogin((String) row[1]);
            affiliateByCommissionDto.setCommission((Double) row[0]);
            results.add(affiliateByCommissionDto);
        }

        return results;
    }

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public SaleDao getSaleDao() {
        return saleDao;
    }
}
