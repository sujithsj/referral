package com.ds.impl.dao;

import com.ds.domain.company.Company;
import com.ds.domain.core.Plan;
import com.ds.domain.user.User;
import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.domain.user.UserSettings;
import com.ds.domain.affiliate.Affiliate;
import com.ds.pact.dao.affiliate.AffiliateDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:37:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AffiliateDAOImpl extends BaseDaoImpl implements AffiliateDAO {

  @Override
  public Company saveCompany(Company company) {
    return (Company)save(company);
  }

	@Override
  public Affiliate saveAffiliate(Affiliate affiliate) {
    return (Affiliate)save(affiliate);
  }

  @SuppressWarnings("unchecked")
  @Override
  public User getUser(String emailId) {
    List<User> results = findByQuery("from User where email=?", new Object[]{emailId});
    if (results.isEmpty()) {
      return null;
    }
    return results.get(0);
  }

  public boolean isCompanyShortNameTaken(String shortName) {
    List results = findByQuery("select 1 from Company where shortName=?", new Object[]{shortName});
    return !results.isEmpty();
  }

  public boolean isUserIdTaken(String userEmailId) {
    List results = findByQuery("select 1 from User where email=?", new Object[]{userEmailId});
    return !results.isEmpty();
  }

  @Override
  public Company getCompany(String companyShortName) {
    return get(Company.class, companyShortName);
  }

  @Override
  public boolean existsUser(String username) {
    return getByQuery("from User where username = ?", username) != null;
  }


  @Override
  public void saveUserLoginConfirmationRequest(UserLoginConfirmationRequest userLoginConfirmationRequest) {
    saveOrUpdate(userLoginConfirmationRequest);
  }

  @Override
  public UserLoginConfirmationRequest loadUserLoginConfirmationRequest(long userLoginConfirmationRequestId) {
    return get(UserLoginConfirmationRequest.class, userLoginConfirmationRequestId);
  }

  @Override
  public UserSettings getUserSettings(String username) {
    return getByQuery("from UserSettings where username = ?", username);
  }

  public List<User> getAllEmployees(String companyId) {
    List<User> results = findByQuery("select user from User user left join fetch user.roleNames where user.companyShortName = ?", new Object[]{companyId});
    if (results.isEmpty()) {
      return null;
    }
    return results;
  }

  public List<User> findEmployees(String companyId, int start, int rows, String sortBy, String sortHow) {
    StringBuilder hql = new StringBuilder("select user from User user where user.companyShortName=? ");
    if (StringUtils.isNotBlank(sortBy)) {
      hql.append(" order by ").append(sortBy).append(" ").append(getSortType(sortHow));
    }

    return find(hql.toString(), new Object[]{companyId}, start, rows);

  }

  @Override
  public long affiliatesCount(String companyShortName) {
    return count("select count(*) from User where companyShortName = ? ", companyShortName);

  }

  @Override
  public Plan getPlan(String planName) {
    return getByQuery("from Plan where name = ?", planName);
  }

}
