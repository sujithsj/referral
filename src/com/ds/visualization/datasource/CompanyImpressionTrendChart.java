package com.ds.visualization.datasource;

import com.ds.exception.DSException;
import com.ds.pact.dao.report.ClickDao;
import com.ds.pact.dao.report.ImpressionDao;
import com.ds.pact.dao.report.SaleDao;
import com.ds.visualization.dto.CompanyTrendDTO;
import com.google.visualization.datasource.Capabilities;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.DateValue;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;

/**
 * @author adlakha.vaibhav
 */
@Component
public class CompanyImpressionTrendChart extends AbstractDataTableGenerator {

  private static Logger logger = LoggerFactory.getLogger(CompanyImpressionTrendChart.class);

  @Autowired
  private ImpressionDao impressionDao;
  @Autowired
  private SaleDao saleDao;
  @Autowired
  private ClickDao clickDao;

  @Override
  public DataTable generateDataTable(Query query, HttpServletRequest request) throws DataSourceException {
    String companyShortName = request.getParameter("companyShortName");


    Date startDate = getStartDateFromRequest(request);
    Date endDate = getEndDateFromRequest(request);
    List<Object[]> impressionTrends = getImpressionDao().getImpressionTrendForCompany(companyShortName, startDate, endDate);
    List<Object[]> clickTrends = getClickDao().getClickTrendForCompany(companyShortName, startDate, endDate);
    List<Object[]> saleTrends = getSaleDao().getSaleTrendForCompany(companyShortName, startDate, endDate);

    Map<Date, CompanyTrendDTO> companyTrendDTOMap = new HashMap<Date, CompanyTrendDTO>();

    for (Object[] row : impressionTrends) {
      CompanyTrendDTO companyTrendDTO = new CompanyTrendDTO();
      companyTrendDTO.setImpressionCount((Double) row[0]);

      companyTrendDTOMap.put((Date) row[1], companyTrendDTO);
    }


    for (Object[] row : clickTrends) {
     Date date =  (Date) row[1];
      CompanyTrendDTO companyTrendDTO = companyTrendDTOMap.get(date);
      if(companyTrendDTO == null){
        companyTrendDTO = new CompanyTrendDTO();
      }
      companyTrendDTO.setClickCount((BigInteger) row[0]);

      companyTrendDTOMap.put((Date) row[1], companyTrendDTO);
    }

    for (Object[] row : saleTrends) {
     Date date =  (Date) row[1];
      CompanyTrendDTO companyTrendDTO = companyTrendDTOMap.get(date);
      if(companyTrendDTO == null){
        companyTrendDTO = new CompanyTrendDTO();
      }
      companyTrendDTO.setSaleCount((BigInteger) row[0]);

      companyTrendDTOMap.put((Date) row[1], companyTrendDTO);
    }

    // Create a data table.
    DataTable data = new DataTable();
    ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
    cd.add(new ColumnDescription("impressionDate", ValueType.DATE, "Impression Date"));
    cd.add(new ColumnDescription("impressionCount", ValueType.NUMBER, "Impression Count"));
    cd.add(new ColumnDescription("clickcount", ValueType.NUMBER, "Click Count"));
    cd.add(new ColumnDescription("saleCount", ValueType.NUMBER, "Sale Count"));

    data.addColumns(cd);

    // Fill the data table.
    try {
      for (Map.Entry<Date, CompanyTrendDTO> entry : companyTrendDTOMap.entrySet()) {

        GregorianCalendar calendar = new GregorianCalendar();
        //calendar.setTime((Date) row[1]);
        calendar.setTime(entry.getKey());

        int year = calendar.get(GregorianCalendar.YEAR);
        int month = calendar.get(GregorianCalendar.MONTH);
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);

        TableRow tableRow = new TableRow();
        tableRow.addCell(new DateValue(year, month, day));
        //tableRow.addCell((Double) row[0]);

        CompanyTrendDTO companyTrendDTO= entry.getValue();
        tableRow.addCell(companyTrendDTO.getImpressionCount());
        tableRow.addCell(companyTrendDTO.getClickCount().doubleValue());
        tableRow.addCell(companyTrendDTO.getSaleCount().doubleValue());

        //calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(calendar.getTime());
        //data.addRowFromValues(calendar, Integer.valueOf(count.intValue()));
        //data.addRowFromValues(calendar, row[0], 10 , 11 );

        data.addRow(tableRow);
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
      calendar.setTime((Date) row[0]);
      calendar.setTimeZone(TimeZone.getTimeZone("GMT"));

      try {
        data.addRowFromValues(calendar, row[1]);
        //data.addRowFromValues(row[1]);
      } catch (TypeMismatchException e) {
        logger.error("Error rendering company trend chart", e);
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

  public SaleDao getSaleDao() {
    return saleDao;
  }

  public ClickDao getClickDao() {
    return clickDao;
  }
}
