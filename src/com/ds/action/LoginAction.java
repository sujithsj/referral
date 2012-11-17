package com.ds.action;

import com.ds.action.employee.UserSearchAction;
import com.ds.pact.service.core.LoginService;
import com.ds.security.service.UserService;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class LoginAction extends BaseAction {

  private String email;
  private String password;

  @Autowired
  private LoginService loginService;
  @Autowired
  private UserService userService;

  @DefaultHandler
  public Resolution pre() {
    return new RedirectResolution("/pages/login.jsp");
  }

  public Resolution loginUser() {

    boolean loginSuccess = getLoginService().login(getContext().getRequest(), getContext().getResponse(), email, password, false);
    System.out.println("login:->" + loginSuccess);
    
    if (loginSuccess) {
      return new RedirectResolution(UserSearchAction.class);

    } else {
      return new RedirectResolution(LoginAction.class, "pre");
    }


  }

  public LoginService getLoginService() {
    return loginService;
  }

  public UserService getUserService() {
    return userService;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
