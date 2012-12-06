package com.ds.action.aff;

/**
 * @author adlakha.vaibhav
 */
public class AffNotFoundException extends AffAuthenticationException {


  private static final long serialVersionUID = 1L;
  private String userName;

  public AffNotFoundException(String key, String userName,  Object... messageParams) {
    super(key, userName, null, messageParams);
  }

  @Override
  public String getMessage() {
    StringBuilder message = new StringBuilder();
    message.append(" no user  " + userName + " exists in system");
    return message.toString();
  }
}
