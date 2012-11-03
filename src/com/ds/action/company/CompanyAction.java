package com.ds.action.company;

import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.dto.company.CompanyRegistrationDTO;
import com.ds.pact.service.admin.AdminService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class CompanyAction extends BaseAction {

  private CompanyRegistrationDTO companyDTO;
  private String companyShortName;

  @Autowired
  private AdminService adminService;

  @DontValidate
  @DefaultHandler
  public Resolution pre() {
    User loggedInUser = SecurityHelper.getLoggedInUser();
    companyShortName = loggedInUser.getCompanyShortName();
    companyDTO = new CompanyRegistrationDTO();
    Company company = getAdminService().getCompany(companyShortName);

    companyDTO.bindCompany(company);

    return new ForwardResolution("/pages/company/companyDetails.jsp");
  }


  public Resolution updateCompany() {


    Company company = companyDTO.extractCompany();
    Company compToUpdate = getAdminService().getCompany(companyShortName);

    if (compToUpdate != null) {
      compToUpdate.syncWith(company);
      getAdminService().updateEntity(compToUpdate);

      //return new JSONResponse().addField("message", "Company Updated Successfully").build();
    }

    //return new JSONResponse().addField("message", "Company Does not Exist").build();

    return new ForwardResolution("/pages/company/companyDetails.jsp");
  }

  public CompanyRegistrationDTO getCompanyDTO() {
    return companyDTO;
  }

  public void setCompanyDTO(CompanyRegistrationDTO companyDTO) {
    this.companyDTO = companyDTO;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public AdminService getAdminService() {
    return adminService;
  }
}
