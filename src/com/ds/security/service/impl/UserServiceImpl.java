package com.ds.security.service.impl;

import com.ds.api.CacheAPI;
import com.ds.api.UserAPI;
import com.ds.domain.user.ThirdPartyAuth;
import com.ds.domain.user.User;
import com.ds.domain.user.UserKarmaProfile;
import com.ds.domain.user.UserSettings;
import com.ds.security.api.SecurityAPI;
import com.ds.security.dao.UserDao;
import com.ds.security.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Service
public class UserServiceImpl implements UserService {

  private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserAPI userAPI;
  @Autowired
  private CacheAPI cacheAPI;
  @Autowired
  private SecurityAPI securityAPI;

  /*
  * (non-Javadoc)
  * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
  */
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    User user = getUser(username);
    if (user == null) {
      // throw new UsernameNotFoundException(username);
      logger.error("No such user found in system: " + username);
      throw new InvalidParameterException("INVALID_USER");
    }

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
    // Test implementation

    for (String roleName : user.getRoleNames()) {
      authorities.add(new GrantedAuthorityImpl(roleName));
    }

    for (String permission : getSecurityAPI().getPermissionsGrantedToUser(user)) {
      authorities.add(new GrantedAuthorityImpl(permission));
    }

    if (user.getCompanyShortName() != null) {
      authorities.add(new GrantedAuthorityImpl("ROLE_EMPLOYEE"));
    }

    user.setAuthorities(authorities);

    //return user;

    return null;

  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  public void createUser(User user) {

    if (StringUtils.isEmpty(user.getUsername())) {
      // TODO:Validate
      throw new SecurityException();
    }

    if (StringUtils.isEmpty(user.getPassword())) {
      // TODO:Validate
      throw new SecurityException();
    }

    if (StringUtils.isEmpty(user.getEmail())) {
      // TODO:Validate
      throw new SecurityException();
    }

    User existingUser = userDao.get(User.class, user.getUsername());
    if (existingUser != null) {
      throw new SecurityException("User with name, " + user.getUsername() + " already exists");
    }

    userDao.save(user);

  }

  public User getAdminUser() {
    return getUser("admin");
  }

  public List<User> getAllUsers() {
    return userDao.getUsers(true);
  }

  public User getUser(String userName) {
    return userDao.get(User.class, userName);
  }

  public List<User> getUsers() {
    return userDao.getUsers(false);
  }

  public void updateUser(User user) {
    userDao.update(user);

  }

  public void saveOrUpdateUser(User user) {
    userDao.saveOrUpdate(user);
  }

  public List<ThirdPartyAuth> getThirdPartyAuth(String userName) {
    return userDao.getThirdPartyAuth(userName);
  }

  @Override
  public UserSettings getUserSettings(String userName) {
    return getUserAPI().getUserSettings(userName);
  }

  @Override
  public UserKarmaProfile getUserKarmaProfile(String userName, String companyShortName) {
    return getUserDao().getUserKarmaProfile(userName, companyShortName);
  }

  @Override
  public void saveOrUpdateUserSettings(UserSettings userSettings) {
    getUserDao().saveOrUpdateUserSettings(userSettings);
    getCacheAPI().remove(CacheAPI.CacheConfig.USER_SETTINGS_CACHE, userSettings.getUsername());
  }

  /**
   * @return the userAPI
   */
  public UserAPI getUserAPI() {
    return userAPI;
  }

  /**
   * @param userAPI the userAPI to set
   */
  public void setUserAPI(UserAPI userAPI) {
    this.userAPI = userAPI;
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

  /**
   * @return the securityAPI
   */
  public SecurityAPI getSecurityAPI() {
    return securityAPI;
  }

  /**
   * @param securityAPI the securityAPI to set
   */
  public void setSecurityAPI(SecurityAPI securityAPI) {
    this.securityAPI = securityAPI;
  }
}
