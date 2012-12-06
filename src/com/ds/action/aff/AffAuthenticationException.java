package com.ds.action.aff;

import com.ds.exception.DSException;

/**
 * @author adlakha.vaibhav
 */
public class AffAuthenticationException extends DSException {

  private static final long serialVersionUID = 1L;
  private String userName;

  public AffAuthenticationException(String key, String userName,  Object... messageParams) {
    super(key, null, messageParams);
    this.userName = userName;
  }

  @Override
  public String getMessage() {
    StringBuilder message = new StringBuilder(super.getMessage() != null ? super.getMessage() : "");
    message.append("Could not authenticate " + userName + " with given credentials");
    return message.toString();
  }
}
