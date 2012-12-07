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

    //TODO: use only those campaigns whose type is for sale 
    String sql = "select count(et.id),cast(et.CLICK_DATE as date) as eventDate from CLICK_TRACKING" +
        " ct where ct.COMPANY_SHORT_NAME = ? and ct.CLICK_DATE between ? and ? " +
        " group by clickDate order by clickDate asc";

    return findByNativeSql(sql, 0, 0, companyShortName, startDate, endDate);
  }
}
