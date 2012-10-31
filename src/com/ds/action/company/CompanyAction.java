package com.ds.action.company;

import com.ds.dto.company.CompanyRegistrationDTO;
import com.ds.web.action.BasePaginatedAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
@Component
public class CompanyAction extends BasePaginatedAction {

  private CompanyRegistrationDTO companyDTO;

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
