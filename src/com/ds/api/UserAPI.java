package com.ds.api;

import com.ds.domain.user.UserSettings;

/**
 * @author adlakha.vaibhav
 */
public interface UserAPI {

  public UserSettings getUserSettings(String userName);
}
