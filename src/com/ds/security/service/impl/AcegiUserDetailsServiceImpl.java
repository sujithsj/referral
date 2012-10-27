package com.ds.security.service.impl;

import com.ds.domain.user.User;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.security.api.SecurityAPI;
import com.ds.security.dao.UserDao;
import com.ds.security.service.AcegiUserDetailsService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author adlakha.vaibhav
 */

public class AcegiUserDetailsServiceImpl implements AcegiUserDetailsService {

  private Logger logger = LoggerFactory.getLogger(AcegiUserDetailsServiceImpl.class);
  
  private UserDao userDao;
  private SecurityAPI securityAPI;

  @Override
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

    return user;



  }

  private User getUser(String userName) {
    return getUserDao().get(User.class, userName);
  }

  private UserDao getUserDao() {
    if (userDao == null) {
      userDao = ServiceLocatorFactory.getService(UserDao.class);
    }

    return userDao;
  }

  public SecurityAPI getSecurityAPI() {
    if (securityAPI == null) {
      securityAPI = ServiceLocatorFactory.getService(SecurityAPI.class);
    }
    return securityAPI;
  }
}
