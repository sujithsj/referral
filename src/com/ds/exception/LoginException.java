package com.ds.exception;

/**
 * @author adlakha.vaibhav
 */
public class LoginException extends DSException {

  public LoginException(String key, Object... messageParams) {
    super(key, messageParams);
  }
}