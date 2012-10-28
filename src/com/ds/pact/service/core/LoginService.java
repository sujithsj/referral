package com.ds.pact.service.core;

import com.ds.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author adlakha.vaibhav
 */
public interface LoginService {

  public boolean login(HttpServletRequest request, HttpServletResponse response, String userName, String password, boolean rememberMe) throws LoginException;
}
