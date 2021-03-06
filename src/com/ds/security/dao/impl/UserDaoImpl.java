package com.ds.security.dao.impl;

import com.ds.domain.user.ThirdPartyAuth;
import com.ds.domain.user.User;
import com.ds.domain.user.UserKarmaProfile;
import com.ds.domain.user.UserSettings;
import com.ds.impl.dao.BaseDaoImpl;
import com.ds.security.dao.UserDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

  @SuppressWarnings("unchecked")
  public List<User> getUsers(boolean includeAdminUser) {
    if (includeAdminUser) {
      return getAll(User.class);
    }

    return findByQuery("from User where username!='admin'");
  }

  public List<ThirdPartyAuth> getThirdPartyAuth(String userName) {
    String hql = " from ThirdPartyAuth where user.username = ?";

    return findByQuery(hql, new Object[]{userName});
  }

  @Override
  public UserSettings getUserSettings(String userName) {
    String hql = " from UserSettings where username = ?";

    return getByQuery(hql, new Object[]{userName});
  }

  @Override
  public UserKarmaProfile getUserKarmaProfile(String userName, String companyShortName) {
    String hql = " from UserKarmaProfile where userName = ? and companyShortName = ?";

    return getByQuery(hql, new Object[]{userName, companyShortName});
  }

  @Override
  @Transactional(readOnly = false)
  public void saveOrUpdateUserSettings(UserSettings userSettings) {
    saveOrUpdate(userSettings);
  }


}
