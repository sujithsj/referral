package com.ds.visualization.datasource;

import com.ds.exception.DSException;
import com.ds.pact.dao.report.SaleDao;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Component
public class TopTenAffiliateByCommissionPieChart extends AbstractDataTableGenerator {

  private static Logger logger = LoggerFactory.getLogger(CompanyImpressionTrendChart.class);

  @Autowired
  private SaleDao saleDao;


  @Override
  public DataTable generateDataTable(Query query, HttpServletRequest request) throws DataSourceException {
    String companyShortName = request.getParameter("companyShortName");
    Date startDate = getStartDateFromRequest(request);
    Date endDate = getEndDateFromRequest(request);

    List<Object[]> topTenAffiliatesByClicks = getSaleDao().getTopTenAffiiliatesByCommission(companyShortName, startDate, endDate);

    // Create a data table.
    DataTable data = new DataTable();
    ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
    cd.add(new ColumnDescription("affiliateName", ValueType.TEXT, "Affiliate Name"));
    cd.add(new ColumnDescription("affiliateType", ValueType.TEXT, "Affiliate Type"));
    cd.add(new ColumnDescription("revenue", ValueType.NUMBER, "Revenue"));



    data.addColumns(cd);

    // Fill the data table.
    try {
      for (Object[] row : topTenAffiliatesByClicks) {

        TableRow tableRow = new TableRow();
        tableRow.addCell((String)row[1]);
        tableRow.addCell((String)row[2]);
        tableRow.addCell((Double)row[0]);


        data.addRow(tableRow);
      }
    } catch (Throwable e) {
      logger.error("Invalid type!", e);
      throw new DSException("INVALID_DATA_TYPE", e);
    }
    return data;

  }


  public SaleDao getSaleDao() {
    return saleDao;
  }
}