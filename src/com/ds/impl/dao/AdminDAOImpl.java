package com.ds.impl.dao;

import com.ds.domain.company.Company;
import com.ds.domain.core.Plan;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.domain.user.UserSettings;
import com.ds.pact.dao.AdminDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
@Repository
public class AdminDAOImpl extends BaseDaoImpl implements AdminDAO {

  @Override
  @Transactional(readOnly = false)
  public Company saveCompany(Company company) {
    return (Company)save(company);
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

  /*@Override
  public void createForm(Form form) {
    save(form);
  }*/

  //@Override
  /* public void createIssueTracker(IssueTrackerConfig issueTrackerConfig) {
      save(issueTrackerConfig);
    }

    @Override
    public IssueTrackerConfig loadIssueTrackerConfig(String issueTrackerConfigName) {
      return getByQuery("from IssueTrackerConfig where name = ?", issueTrackerConfigName);
    }

    @Override
    public void saveOrUpdateForm(Form form) {
      saveOrUpdate(form);
    }

    @Override
    public void updateIssueTracker(IssueTrackerConfig issueTrackerConfig) {
      update(issueTrackerConfig);
    }

    @Override
    public IssueTrackerConfig loadIssueTrackerConfig(long issueTrackerId) {
      return getByQuery("from IssueTrackerConfig where id = ?", issueTrackerId);
    }

    @Override
    public IssueTrackerConfig loadIssueTrackerConfig(String companyShortName, long issueTrackerId) {
      return getByQuery("from IssueTrackerConfig where id = ? and company.shortName = ?", issueTrackerId, companyShortName);
    }

    @Override
    public boolean existsIssueTracker(String companyShortName, String issueTrackerConfigName) {
      return getByQuery("from IssueTrackerConfig where name = ? and company.shortName = ?", issueTrackerConfigName, companyShortName) != null;
    }

    @Override
    public List<IssueTrackerConfig> getIssueTrackers(String companyShortName) {
      return find("from IssueTrackerConfig where company.shortName = ?", companyShortName);
    }
  */

  @Override
  public boolean existsUser(String username) {
    return getByQuery("from User where username = ?", username) != null;
  }

  /*@Override
  public Form findForm(IssueTrackerConfig issueTrackerConfig, String formName) {
    Form form = getByQuery("from Form form where form.issueTrackerConfig = ? and form.name = ? ", issueTrackerConfig, formName);

    // just to make sure that Form gets the Results
    form.getFormFields().iterator().hasNext();
    for (FormField field : form.getFormFields()) {
      field.getOptions().iterator().hasNext();
    }
    // Evict it from session so that any changes done on this object dont get persisted by any chance
    getHibernateTemplate().evict(form);

    return form;
  }*/

  @Override
  public void saveUserLoginConfirmationRequest(UserLoginConfirmationRequest userLoginConfirmationRequest) {
    saveOrUpdate(userLoginConfirmationRequest);
  }

  @Override
  public UserLoginConfirmationRequest loadUserLoginConfirmationRequest(long userLoginConfirmationRequestId) {
    return get(UserLoginConfirmationRequest.class, userLoginConfirmationRequestId);
  }

/*@Override
  public List<FormFieldOption> getFormFieldOptions(IssueTrackerConfig issueTrackerConfig, String formName, String fieldName) {
    String hql = " select formField.options from FormField formField where formField.form.name=? and formField.form.issueTrackerConfig=? and formField.name = ? ";
    return find(hql, formName, issueTrackerConfig, fieldName);
  }
*/

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
  public long employeesCount(String companyShortName) {
    return count("select count(*) from User where companyShortName = ? ", companyShortName);

  }

  @Override
  public Plan getPlan(String planName) {
    return getByQuery("from Plan where name = ?", planName);
  }

  @Override
  public List<Role> getAllRoles(){
      return getAll(Role.class);
  }


  /*@Override
  public Badge getBadgeForCompany(String badgeName, String companyShortName) {
    List badges = find("from Badge where badgeName=? and companyShortName=?", badgeName, companyShortName);
    if (badges != null && badges.size() > 0) {
      return (Badge) badges.get(0);
    }

    return null;
  }
*/
  /*@Override
  public List<Company> getAllVendors(int start, int rows) {
    return find("from Company where vendor = ? ", new Object[]{true}, start, rows);
  }

  @Override
  public int getNoOfVendors() {
    return count("select count(*) from Company where vendor = ?", true);
  }
*/
}
