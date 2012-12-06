package com.ds.pact.dao.report;

import java.util.List;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public interface ClickDao {


  public List<Object[]> getClickTrendForCompany(String companyShortName, Date startDate, Date endDate);
}
