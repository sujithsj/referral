package com.ds.security.dao;

import com.ds.domain.user.User;
import com.ds.domain.user.UserSettings;
import com.ds.pact.dao.BaseDao;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public interface UserDao extends BaseDao {

    List<User> getUsers(boolean includeAdminUser);

    /**//**
     * Get a list of ThirdPartyAuth associated with this user.
     * @param userName
     * @return
     *//*
    public List<ThirdPartyAuth> getThirdPartyAuth(String userName);*/

    /**
     * Get user settings for the user specified by userName.
     * @param userName
     * @return
     */
    public UserSettings getUserSettings(String userName);

    /**//**
     * Get karma profile for user specified by userName
     * @param userName
     * @return
     *//*
    public UserKarmaProfile getUserKarmaProfile(String userName, String companyShortName);
*/
    /**
     * Save or Update user settings for a particluar user.
     * @param userSettings
     */
    public void saveOrUpdateUserSettings(UserSettings userSettings);


}
