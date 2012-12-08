package com.ds.visualization.datasource;

import com.ds.pact.dao.report.SaleDao;
import com.ds.utils.DSDateUtil;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class SixMonthAffiliateSaleReport extends AbstractDataTableGenerator {

  private static Logger logger = LoggerFactory.getLogger(CompanyImpressionTrendChart.class);

  @Autowired
  private SaleDao saleDao;

  @Override
  public DataTable generateDataTable(Query query, HttpServletRequest request) throws DataSourceException {
    String companyShortName = request.getParameter("companyShortName");
    Date startDate = getStartDateFromRequest(request);
    Date endDate = null;

    if (startDate == null) {
      endDate = new Date();
      startDate = DSDateUtil.addToDate(endDate, Calendar.MONTH, -6);
    }

    List<Object[]> sixMonthAffiliateSaleData = getSaleDao().getSixMonthAffiliateSaleReport(companyShortName, startDate, endDate);


    DataTable data = new DataTable();

    /**
     *   select sum(revenue) rev, a.first_name, year(et.event_date) year, month(et.event_date) from event_tracking et
     inner join affiliate a on a.id = et.affiliate_id
     inner join CAMPAIGN c on c.ID = et.CAMPAIGN_ID
     where c.CAMPAIGN_TYPE_ID = 10 and  et.company_short_name = 'hk' and et.event_date between '2012-12-01' and '2012-12-31'
     group by et.affiliate_id, year(et.event_date), month(event_date) order by  rev desc limit 5 ;


     -- delete from event_tracking where affiliate_id = 2 and month(event_date) = 6;
     */
    return null;

  }


  public SaleDao getSaleDao() {
    return saleDao;
  }
}
