package com.ds.impl.dao.report;

import com.ds.impl.dao.BaseDaoImpl;
import com.ds.pact.dao.report.ClickDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Repository
public class ClickDaoImpl extends BaseDaoImpl implements ClickDao{

  @Override
  public List<Object[]> getClickTrendForCompany(String companyShortName, Date startDate, Date endDate) {
    String sql = "select count(ct.id),cast(ct.CLICK_DATE as date) as clickDate from CLICK_TRACKING" +
        " ct where ct.COMPANY_SHORT_NAME = ? and ct.CLICK_DATE between ? and ? " +
        " group by clickDate order by clickDate asc";

    return findByNativeSql(sql, 0, 0, companyShortName, startDate, endDate);
  }
}
