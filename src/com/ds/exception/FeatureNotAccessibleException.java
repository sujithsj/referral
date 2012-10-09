package com.ds.exception;

/**
 * @author adlakha.vaibhav
 */
public class FeatureNotAccessibleException extends DSException {

  public FeatureNotAccessibleException(String key, Object... messageParams) {
    super(key, messageParams);
  }
}
