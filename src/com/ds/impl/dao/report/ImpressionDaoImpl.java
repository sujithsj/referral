package com.ds.impl.dao.report;

import com.ds.impl.dao.BaseDaoImpl;
import com.ds.pact.dao.report.ImpressionDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Repository
public class ImpressionDaoImpl extends BaseDaoImpl implements ImpressionDao {

  public List<Object[]> getImpressionTrendForCompany(String companyShortName, Date startDate, Date endDate){
    String sql = "select sum(it.COUNT),cast(it.IMPRESSION_DATE as date) as impressionDate from IMPRESSION_TRACKING" +
        " it where it.COMPANY_SHORT_NAME = ? and it.IMPRESSION_DATE between ? and ? " +
        " group by impressionDate order by impressionDate asc";

    return findByNativeSql(sql, 0, 0, companyShortName, startDate, endDate);
    
  }

}
