package com.ds.pact.dao.report;

import java.util.List;
import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public interface SaleDao {


  public List<Object[]> getSaleTrendForCompany(String companyShortName, Date startDate, Date endDate);
}
