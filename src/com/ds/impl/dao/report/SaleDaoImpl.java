package com.ds.impl.dao.report;

import com.ds.impl.dao.BaseDaoImpl;
import com.ds.pact.dao.report.SaleDao;

import java.util.List;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public class SaleDaoImpl extends BaseDaoImpl implements SaleDao{

  @Override
  public List<Object[]> getSaleTrendForCompany(String companyShortName, Date startDate, Date endDate) {
    String sql = "select sum(it.COUNT),cast(ct.CLICK_DATE as date) as clickDate from CLICK_TRACKING" +
        " ct where it.COMPANY_SHORT_NAME = ? and it.IMPRESSION_DATE between ? and ? " +
        " group by clickDate order by clickDate asc";

    return findByNativeSql(sql, 0, 0, companyShortName, startDate, endDate);
  }
}
