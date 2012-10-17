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
    /*String name = request.getParameter("name");
    String shortName = request.getParameter("shortName");
    String description = request.getParameter("description");
    String url = request.getParameter("url");
    String recaptchaChallengeField = request.getParameter("recaptcha_challenge_field");
    String recaptchaResponsefield = request.getParameter("recaptcha_response_field");

    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String userName = request.getParameter("fullName");*/

    Company company = new Company();
    company.setName("abc company");
    company.setShortName("abc");
    company.setUrl("http://".concat("abc.com"));
    company.setDescription("abc desc");

    User user = new User();

    user.setUsername("abc");
    user.setFullName("abc test");
    user.setPassword("abc");
    user.setEmail("abc@def.com");

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
