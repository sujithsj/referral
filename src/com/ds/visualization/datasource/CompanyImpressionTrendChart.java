package com.ds.visualization.datasource;

import com.ds.exception.DSException;
import com.ds.pact.dao.report.ImpressionDao;
import com.google.visualization.datasource.Capabilities;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author adlakha.vaibhav
 */
@Component
public class CompanyImpressionTrendChart extends AbstractDataTableGenerator {

  private static Logger logger = LoggerFactory.getLogger(CompanyImpressionTrendChart.class);

  @Autowired
  private ImpressionDao impressionDao;

  @Override
  public DataTable generateDataTable(Query query, HttpServletRequest request) throws DataSourceException {
    String companyShortName = request.getParameter("companyShortName");


    Date startDate = getStartDateFromRequest(request);
    Date endDate = getEndDateFromRequest(request);
    List<Object[]> feedbackTrends = null;
    feedbackTrends = getImpressionDao().getImpressionTrendForCompany(companyShortName, startDate, endDate);

    // Create a data table.
    DataTable data = new DataTable();
    ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
    cd.add(new ColumnDescription("impressionDate", ValueType.DATE, "Impression Date"));
    cd.add(new ColumnDescription("impressionCount", ValueType.NUMBER, "Impression Count"));

    data.addColumns(cd);

    // Fill the data table.
    try {
      for (Object[] row : feedbackTrends) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime((Date) row[1]);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));

        //data.addRowFromValues(calendar, Integer.valueOf(count.intValue()));
        data.addRowFromValues(calendar, row[0]);
      }
    } catch (Throwable e) {
      logger.error("Invalid type!", e);
      throw new DSException("INVALID_DATA_TYPE", e);
    }
    return data;

  }


  public static void main(String[] args) {
    DataTable data = new DataTable();
    ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
    cd.add(new ColumnDescription("impressionDate", ValueType.DATE, "Impression Date"));
    cd.add(new ColumnDescription("impressionCount", ValueType.NUMBER, "Impression Count"));

    data.addColumns(cd);

    List<Object[]> feedbackTrends = new ArrayList<Object[]>();

    feedbackTrends.add(new Object[]{new Date(), 2D});

    for (Object[] row : feedbackTrends) {
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTime((Date)row[0]);
      calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
      
      try {
        data.addRowFromValues(calendar, row[1]);
        //data.addRowFromValues(row[1]);
      } catch (TypeMismatchException e) {
        e.printStackTrace();  
      }
    }
  }

  @Override
  public Capabilities getCapabilities() {
    return Capabilities.NONE;
  }

  public ImpressionDao getImpressionDao() {
    return impressionDao;
  }
}
