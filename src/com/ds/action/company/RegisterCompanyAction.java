package com.ds.action.company;

import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.dto.company.CompanyRegistrationDTO;
import com.ds.exception.CompositeValidationException;
import com.ds.pact.service.admin.AdminService;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class RegisterCompanyAction extends BaseAction {

  @Autowired
  private AdminService adminService;

  private CompanyRegistrationDTO companyRegistrationDTO;


  @DontValidate
  @DefaultHandler
  public Resolution registerCompany() {
    Company company = new Company();
    company.setName(companyRegistrationDTO.getName());
    company.setShortName(companyRegistrationDTO.getShortName());
    company.setUrl(companyRegistrationDTO.getUrl());
    company.setDescription(companyRegistrationDTO.getDescription());

    User user = new User();

    user.setUsername(companyRegistrationDTO.getUserName());
    user.setFullName(companyRegistrationDTO.getUserName());
    user.setPassword(companyRegistrationDTO.getPassword());
    user.setEmail(companyRegistrationDTO.getEmail());

    try {
      getAdminService().registerCompany(company, user, null);
    } catch (CompositeValidationException cve) {
        cve.printStackTrace();
    }

    return new ForwardResolution("/pages/setup.jsp");
  }

  public AdminService getAdminService() {
    return adminService;
  }

  public CompanyRegistrationDTO getCompanyRegistrationDTO() {
    return companyRegistrationDTO;
  }

  public void setCompanyRegistrationDTO(CompanyRegistrationDTO companyRegistrationDTO) {
    this.companyRegistrationDTO = companyRegistrationDTO;
  }
}
