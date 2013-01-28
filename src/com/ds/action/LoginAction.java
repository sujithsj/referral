package com.ds.action;

import com.ds.action.employee.UserSearchAction;
import com.ds.pact.service.core.LoginService;
import com.ds.security.service.UserService;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.RequestDispatcher;

/**
 * @author adlakha.vaibhav
 */
@Component
public class LoginAction extends BaseAction {

    private String j_username;
    private String j_password;

    private boolean authException;
    private String authFailueMessage;

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @DefaultHandler
    public Resolution pre() {
        return new ForwardResolution("/pages/login.jsp");
    }

    public Resolution loginUser() {

        try {
            RequestDispatcher dispatcher = ((ServletRequest) getContext().getRequest()).getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward((ServletRequest) getContext().getRequest(), (ServletResponse) getContext().getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*    boolean loginSuccess = getLoginService().login(getContext().getRequest(), getContext().getResponse(), email, password, false);
                System.out.println("login:->" + loginSuccess);

                if (loginSuccess) {
                    return new RedirectResolution(UserSearchAction.class);

                } else {
                    return new RedirectResolution(LoginAction.class, "pre");
                }
        */

        return null;

    }

    public LoginService getLoginService() {
        return loginService;
    }

    public UserService getUserService() {
        return userService;
    }

    /*public String getEmail() {
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
    }*/

    public String getJ_username() {
        return j_username;
    }

    public void setJ_username(String j_username) {
        this.j_username = j_username;
    }

    public String getJ_password() {
        return j_password;
    }

    public void setJ_password(String j_password) {
        this.j_password = j_password;
    }

    public boolean isAuthException() {
        return authException;
    }

    public void setAuthException(boolean authException) {
        this.authException = authException;
    }

    public String getAuthFailueMessage() {
        return authFailueMessage;
    }

    public void setAuthFailueMessage(String authFailueMessage) {
        this.authFailueMessage = authFailueMessage;
    }
}
