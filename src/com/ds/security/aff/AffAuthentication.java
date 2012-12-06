package com.ds.security.aff;

/**
 * @author adlakha.vaibhav
 */
public interface AffAuthentication {


  public Object getCredentials();


  /**
   * The identity of the principal being authenticated. In the case of an authentication request with username and
   * password, this would be the username. Callers are expected to populate the principal for an authentication
   * request.
   *
   * @return the <code>Principal</code> being authenticated or the authenticated principal after authentication.
   */
  Object getPrincipal();


  boolean isAuthenticated();


  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;
}
