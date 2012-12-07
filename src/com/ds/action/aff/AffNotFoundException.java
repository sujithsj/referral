package com.ds.action.aff;

/**
 * @author adlakha.vaibhav
 */
public class AffNotFoundException extends AffAuthenticationException {


  private static final long serialVersionUID = 1L;
  private String login;

  public AffNotFoundException(String key, String login,  Object... messageParams) {
    super(key, login, null, messageParams);
  }

  @Override
  public String getMessage() {
    StringBuilder message = new StringBuilder();
    message.append(" no affiliate  " + login + " exists in system");
    return message.toString();
  }
}
