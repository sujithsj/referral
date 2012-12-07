package com.ds.impl.dao.report;

import com.ds.impl.dao.BaseDaoImpl;
import com.ds.pact.dao.report.SaleDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Repository
public class SaleDaoImpl extends BaseDaoImpl implements SaleDao{

  @Override
  public List<Object[]> getSaleTrendForCompany(String companyShortName, Date startDate, Date endDate) {

    //TODO: use only those campaigns whose type is for sale 
    String sql = "select count(et.id),cast(et.EVENT_DATE as date) as eventDate from EVENT_TRACKING et" +
        " inner join CAMPAIGN c on c.ID = et.CAMPAIGN_ID where et.COMPANY_SHORT_NAME = ? and et.EVENT_DATE between ? and ? " +
        " and c.CAMPAIGN_TYPE_ID = 10 group by eventDate order by eventDate asc";

    return findByNativeSql(sql, 0, 0, companyShortName, startDate, endDate);
  }
}
