package com.ds.impl.dao;

import com.ds.domain.company.Company;
import com.ds.pact.dao.AdminDAO;

import javax.xml.registry.infomodel.User;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class AdminDaoImpl extends BaseDaoImpl implements AdminDAO {

  @Override
  public void saveCompany(Company company) {
    save(company);
  }

  @SuppressWarnings("unchecked")
  public User getUser(String emailId) {
    List<User> results = findByQuery("from User where email=?", new Object[]{emailId});
    if (results.isEmpty()) {
      return null;
    }
    return results.get(0);
  }

  @SuppressWarnings("unchecked")
  public boolean isCompanyShortNameTaken(String shortName) {
    List results = findByQuery("select 1 from Company where shortName=?", new Object[]{shortName});
    return !results.isEmpty();
  }

  @SuppressWarnings("unchecked")
  public boolean isUserIdTaken(String userEmailId) {
    List results = findByQuery("select 1 from User where email=?", new Object[]{userEmailId});
    return !results.isEmpty();
  }

  @Override
  public Company getCompany(String companyShortName) {
    return get(Company.class, companyShortName);
  }
}
