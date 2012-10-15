package com.ds.action.company;

import com.ds.domain.company.Company;
import com.ds.pact.dao.BaseDao;
import com.ds.web.action.BasePaginatedAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
@Component
public class CompanyAction extends BasePaginatedAction {

  @Autowired
  private BaseDao baseDao;

  @DontValidate
  @DefaultHandler
  public Resolution pre() {
    System.out.println("in pre for action");

    baseDao.get(Company.class,"abc");
    return null;
  }

  @Override
  public int getPageCount() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getResultCount() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Set<String> getParamSet() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
