package com.ds.security.dao;

import com.ds.domain.core.Permission;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.pact.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public interface SecurityDao extends BaseDao {

    /**
     * Determines if the specified user has the given permission Type.
     *
     * @param user
     * @return
     */
    public boolean hasPermission(User user, Permission.Type permissionType);

    /**
     * @param user
     * @param permissionType
     * 
     * @return Map key being Permission.Type and and boolean indicating whether user has this permission or not
     */
    public Map<Permission.Type, Boolean> checkHasPermissions(User user, Permission.Type... permissionType);

    /**
     * Loads the Role
     *
     * @param role
     * @return Role
     */
    public Role loadRole(Role.RoleType role);

    /**
     * Finds the User with the Username
     *
     * @return
     */
    public User findUserWithUsername(String username);

    /**//**
     * Associates User to the ThirdPartyAuth this basically means that this user has used this third party
     * authentication also to login in our system
     *
     * @param user
     * @param thirdPartyAuth
     *//*
    public void associateUserToThirdPartyAuth(User user, ThirdPartyAuth thirdPartyAuth);*/

    /**
     * Creates a User into the System
     *
     * @param user
     */
    public void createUser(User user);

    /**
     * Updates a User into the System
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * Finds the User associated with the ThirdPartyAuth which is for authProviderName and has given us this identifier
     * for user
     *
     * @param authProviderName
     * @param identifier
     * @return User
     */
    public User findUserCorrespondingToThirdPartyAuth(String authProviderName, String identifier);

    /**
     * Does a User Exists with the given email
     *
     * @param email
     * @return true if user exists with the given email, false otherwise
     */
    public boolean userExists(String email);

    /**
     * Finds and Returns all the Permissions granted to User
     *
     * @param user
     * @return List of Permission Names granted to User
     */
    public List<String> getPermissionsGrantedToUser(User user);
}
