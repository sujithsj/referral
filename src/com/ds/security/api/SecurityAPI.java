package com.ds.security.api;

import com.ds.domain.core.Permission;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;

import java.util.List;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public interface SecurityAPI {

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
     * @return Map key being Permission.Type and and boolean indicating whether user has this permission or not
     */
    public Map<Permission.Type, Boolean> checkHasPermissions(User user, Permission.Type... permissionType);

    /**
     * Grant Roles to User
     *
     * @param user
     */
    public void grantRolesToUser(User user, Role.Type... role);

    /**
     * Finds and Returns all the Permissions granted to User
     *
     * @param user
     * @return List of Permission Names granted to User
     */
    public List<String> getPermissionsGrantedToUser(User user);

    /**
     * Revoke Roles from User
     *
     * @param user
     * @param role
     */
    public void revokeRolesFromUser(User user, Role.Type... role);

    /**
     * Does a User Exists with the given email
     *
     * @param email
     * @return true if user exists with the given email, false otherwise
     */
    public boolean userExists(String email);
}
