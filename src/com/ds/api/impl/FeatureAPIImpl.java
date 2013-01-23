package com.ds.api.impl;

import com.ds.api.CacheAPI;
import com.ds.api.FeatureAPI;
import com.ds.constants.FeatureType;
import com.ds.domain.company.Company;
import com.ds.domain.core.Feature;
import com.ds.domain.core.Plan;
import com.ds.exception.FeatureNotAccessibleException;
import com.ds.pact.dao.AdminDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;

/**
 * @author adlakha.vaibhav
 */
@Service
public class FeatureAPIImpl implements FeatureAPI {

  @Autowired
  private AdminDAO adminDAO;
  @Autowired
  private CacheAPI cacheAPI;

  @Override
  public boolean isFeatureAccessible(Company company, FeatureType featureType) {
    for (Feature feature : company.getFeatures()) {
      if (featureType.featureType().equals(feature.getFeatureType())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void doesCompanyHaveAccessTo(Company company, FeatureType featureType) {
    if (!isFeatureAccessible(company, featureType)) {
      throw new FeatureNotAccessibleException("FEATURE_NOT_ACCESSIBLE", featureType.featureType());
    }
  }

  @Override
  public boolean isFeatureAccessible(Company company, FeatureType featureType, long count) {
    for (Feature feature : company.getFeatures()) {
      if (featureType.featureType().equals(feature.getFeatureType()) && feature.getCount() >= count) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void doesCompanyHaveAccessTo(Company company, FeatureType featureType, long count) throws FeatureNotAccessibleException  {
    if (!isFeatureAccessible(company, featureType, count)) {
      throw new FeatureNotAccessibleException("FEATURE_NOT_ACCESSIBLE", featureType.featureType());
    }
  }

  @Override
  public Plan getPlan(String planName) {
    Plan cachedPlan = (Plan) getCacheAPI().get(CacheAPI.CacheConfig.PLAN_CACHE, planName);
    if (cachedPlan == null) {
      cachedPlan = getAdminDAO().getPlan(planName);
      if (cachedPlan != null) {
        getCacheAPI().put(CacheAPI.CacheConfig.PLAN_CACHE, planName, cachedPlan);
      } else {
        throw new InvalidParameterException("NO_PLAN_FOUND");
      }
    }
    return cachedPlan;
  }

  @Override
  public void grantCompanyAccessTo(Company company, FeatureType featureType) {
    Feature feature = new Feature();
    feature.setName(company.getShortName() + "-" + featureType.featureType());
    feature.setFeatureType(feature.getFeatureType());
    company.getFeatures().add(feature);
    updateCompany(company);
  }

  private void updateCompany(Company company) {
    getAdminDAO().update(company);
    getCacheAPI().remove(CacheAPI.CacheConfig.COMPANY_CACHE, company.getShortName());
  }

  @Override
  public void grantCompanyAccessTo(Company company, FeatureType featureType, long count) {
    Feature feature = new Feature();
    feature.setName(company.getShortName() + "-" + featureType.featureType());
    feature.setFeatureType(feature.getFeatureType());
    feature.setCount(count);
    company.getFeatures().add(feature);
    updateCompany(company);

  }

  @Override
  public void grantCompanyAccessTo(Company company, Plan plan) {
    for (Feature feature : plan.getFeatures()) {
      company.getFeatures().add(feature);
    }
    updateCompany(company);
  }

  @Override
  public void grantCompanyAccessTo(Company company, String planName) {
    grantCompanyAccessTo(company, getPlan(planName));
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

  @Override
  public void revokeCompanyAccessFrom(Company company, Plan plan) {
    for (Feature feature : plan.getFeatures()) {
      company.getFeatures().remove(feature);
    }
    updateCompany(company);
  }

  @Override
  public void revokeCompanyAccessFrom(Company company, String planName) {
    revokeCompanyAccessFrom(company, getPlan(planName));
  }

}
