package com.ds.security.service;

import com.ds.domain.user.ThirdPartyAuth;
import com.ds.domain.user.User;
import com.ds.domain.user.UserKarmaProfile;
import com.ds.domain.user.UserSettings;
import com.ds.web.action.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public interface UserService  {

  /**
   * Get all users including admin user
   *
   * @return
   */
  List<User> getAllUsers();

  /**
   * Get all users except admin user
   *
   * @return
   */
  List<User> getUsers();

  /**
   * Returns admin user
   *
   * @return
   */
  User getAdminUser();

  /**
   * Create new user
   *
   * @param user
   */
  void createUser(User user);

  /**
   * Update existing user
   *
   * @param user
   */
  void updateUser(User user);

  /**
   * Get user associated wtih 'userName'
   *
   * @param userName
   * @return
   */
  User getUser(String userName);

  void saveOrUpdateUser(User user);

  /**
   * Get a list of ThirdPartyAuth associated with this user.
   *
   * @param userName
   * @return
   */
  public List<ThirdPartyAuth> getThirdPartyAuth(String userName);

  /**
   * Get user settings for the user specified by userName.
   *
   * @param userName
   * @return
   */
  public UserSettings getUserSettings(String userName);

  /**
   * Get karma profile for user specified by userName
   *
   * @param userName
   * @return
   */
  public UserKarmaProfile getUserKarmaProfile(String userName, String companyShortName);

  /**
   * Update user settings for a particluar user.
   *
   * @param userSettings
   */
  public void saveOrUpdateUserSettings(UserSettings userSettings);


  public Page searchUser(String userName, String email, int pageNo, int perPage);
}
