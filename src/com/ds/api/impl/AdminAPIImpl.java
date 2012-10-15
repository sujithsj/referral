package com.ds.api.impl;

import com.ds.api.AdminAPI;
import com.ds.api.CacheAPI;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.pact.dao.AdminDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Service
public class AdminAPIImpl implements AdminAPI {


  @Autowired
  private CacheAPI cacheAPI;
  @Autowired
  private AdminDAO adminDAO;

  @Override
  public Company getCompany(String companyShortName) {
    if (StringUtils.isBlank(companyShortName)) {
      return null;
    }
    Company company = (Company) getCacheAPI().get(CacheAPI.CacheConfig.COMPANY_CACHE, companyShortName);
    if (company == null) {
      company = getAdminDAO().getCompany(companyShortName);
      if (company != null) {
        getCacheAPI().put(CacheAPI.CacheConfig.COMPANY_CACHE, companyShortName, company);
      }
    }
    return company;
  }

  @Override
  public User getUser(String userId) {
    User user = (User) getCacheAPI().get(CacheAPI.CacheConfig.USER_CACHE, userId);
    if (user == null) {
      user = getAdminDAO().getUser(userId);
      if (user != null) {
        getCacheAPI().put(CacheAPI.CacheConfig.USER_CACHE, userId, user);
      }
    }
    return user;
  }

  @Override
  public List<User> findEmployees(String companyShortName, int start, int rows, String sortBy, String sortHow) {
    List<User> employees = getAdminDAO().findEmployees(companyShortName, start, rows, sortBy, sortHow);

    for (User user : employees) {
      User cached = (User) getCacheAPI().get(CacheAPI.CacheConfig.USER_CACHE, user.getUsername());
      if (cached != null) {
        user.setRoleNames(cached.getRoleNames());
      }
    }

    return employees;
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
   * @return the adminDAO
   */
  public AdminDAO getAdminDAO() {
    return adminDAO;
  }

  /**
   * @param adminDAO the adminDAO to set
   */
  public void setAdminDAO(AdminDAO adminDAO) {
    this.adminDAO = adminDAO;
  }



}
