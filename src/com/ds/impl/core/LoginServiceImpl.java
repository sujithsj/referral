package com.ds.impl.core;

import com.ds.exception.LoginException;
import com.ds.pact.service.core.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSourceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author adlakha.vaibhav
 */
@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public boolean login(HttpServletRequest request, HttpServletResponse response, String userName, String password, boolean rememberMe) throws LoginException {

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);

    authRequest.setDetails((new AuthenticationDetailsSourceImpl()).buildDetails(request));
    Authentication authResult = getAuthenticationManager().authenticate(authRequest);
    SecurityContextHolder.getContext().setAuthentication(authResult);

    return authResult.isAuthenticated();
    //rememberMeServices.loginSuccess(request, response, authResult);
  }

  public AuthenticationManager getAuthenticationManager() {
    return authenticationManager;
  }
}
