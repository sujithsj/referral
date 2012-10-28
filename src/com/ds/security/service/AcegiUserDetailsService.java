package com.ds.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.dao.DataAccessException;

/**
 * @author adlakha.vaibhav
 */
public interface AcegiUserDetailsService extends UserDetailsService {

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException ;
}
