package com.ds.api.impl;

import com.ds.api.CacheAPI;
import com.ds.api.UserAPI;
import com.ds.domain.user.UserSettings;
import com.ds.security.dao.UserDao;

/**
 * @author adlakha.vaibhav
 */
public class UserAPIImpl implements UserAPI {

  private UserDao userDao;
  private CacheAPI cacheAPI;


  @Override
  public UserSettings getUserSettings(String userName) {
    UserSettings userSettings = (UserSettings) getCacheAPI().get(CacheAPI.CacheConfig.USER_SETTINGS_CACHE, userName);
    if (userSettings == null) {
      userSettings = getUserDao().getUserSettings(userName);
      if (userSettings != null) {
        getCacheAPI().put(CacheAPI.CacheConfig.USER_SETTINGS_CACHE, userName, userSettings);
      }
    }
    return userSettings;
  }

  /**
   * @return the userDao
   */
  public UserDao getUserDao() {
    return userDao;
  }

  /**
   * @param userDao the userDao to set
   */
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * @return the cacheAPI
   */
  public CacheAPI getCacheAPI() {
    return cacheAPI;
  }

  /**
   * @param cacheAPI the cacheAPI to set
   */
  public void setCacheAPI(CacheAPI cacheAPI) {
    this.cacheAPI = cacheAPI;
  }


}
