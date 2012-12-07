package com.ds.pact.dao.report;

import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public interface ImpressionDao {

  public List<Object[]> getImpressionTrendForCompany(String companyShortName, Date startDate, Date endDate);


}
