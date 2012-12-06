package com.ds.action.aff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ds.pact.service.core.LoginService;
import com.ds.security.service.UserService;
import com.ds.action.employee.UserSearchAction;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 4, 2012
 * Time: 9:49:42 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AffiliateLoginAction extends BaseAction {

  private String password;
	private String login;


  @DefaultHandler
  public Resolution pre() {
    return new RedirectResolution("/pages/login.jsp");
  }

  public Resolution loginAffiliate() {

	  //login karao affiliate ka
    return null;


  }


  public String getPassword() {
    return password;
  }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
    this.password = password;
  }
}
