package com.ds.action.aff;

/**
 * @author adlakha.vaibhav
 */
public class AffDisbaledException extends AffAuthenticationException {


  private static final long serialVersionUID = 1L;
  private String userName;

  public AffDisbaledException(String key, String userName,  Object... messageParams) {
    super(key, userName, null, messageParams);
  }

  @Override
  public String getMessage() {
    StringBuilder message = new StringBuilder();
    message.append("user  " + userName + " is disabled");
    return message.toString();
  }

}