package com.ds.action.company;

import com.ds.web.action.BasePaginatedAction;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
public class CompanyAction extends BasePaginatedAction {

  @DontValidate
  @DefaultHandler
  public Resolution pre() {
    System.out.println("in pre for action");

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
