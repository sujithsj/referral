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
import java.util.Set;

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


    
  }


  public SaleDao getSaleDao() {
    return saleDao;
  }
}
