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
public class SaleDaoImpl extends BaseDaoImpl implements SaleDao {

  @Override
  public List<Object[]> getSaleTrendForCompany(String companyShortName, Date startDate, Date endDate) {

    //TODO: use only those campaigns whose type is for sale 
    String sql = "select count(et.id),cast(et.EVENT_DATE as date) as eventDate from EVENT_TRACKING et" +
        " inner join CAMPAIGN c on c.ID = et.CAMPAIGN_ID where et.COMPANY_SHORT_NAME = ? and et.EVENT_DATE between ? and ? " +
        " and c.CAMPAIGN_TYPE_ID = 10 group by eventDate order by eventDate asc";

    return findByNativeSql(sql, 0, 0, companyShortName, startDate, endDate);
  }


  //TODO: currently since we do not have commission data , return sales for now
  @Override
  public List<Object[]> getTopTenAffiiliatesByCommission(String companyShortName, Date startDate, Date endDate) {
    String sql = "select sum(revenue) revenue, a.first_name, a.affiliate_type from event_tracking et " +
        "inner join affiliate a on a.id = et.affiliate_id  inner join CAMPAIGN c on c.ID = et.CAMPAIGN_ID  where et.COMPANY_SHORT_NAME = ?  " +
        " and et.EVENT_DATE between ? and ?  and c.CAMPAIGN_TYPE_ID = 10 " +
        "group by et.affiliate_id order by revenue desc limit 10";

    return findByNativeSql(sql, 0, 0, companyShortName, startDate, endDate);
  }

  @Override
  public List<Object[]> getSixMonthAffiliateSaleReport(String companyShortName, Date startDate, Date endDate) {

    String sql = "select sum(revenue) rev, a.first_name, year(et.event_date) year, month(et.event_date) month, from event_tracking et " +
        "inner join affiliate a on a.id = et.affiliate_id " +
        "inner join CAMPAIGN c on c.ID = et.CAMPAIGN_ID " +
        "where c.CAMPAIGN_TYPE_ID = 10 and and et.company_short_name = ? and et.event_date between ? and ?" +
        "group by et.affiliate_id, year(et.event_date), month(event_date) limit 30;";

    return findByNativeSql(sql, 0, 0, companyShortName, startDate, endDate);
  }


}
