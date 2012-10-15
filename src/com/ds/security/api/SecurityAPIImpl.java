package com.ds.security.api;

import com.ds.domain.core.Permission;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.security.dao.SecurityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
@Service
public class SecurityAPIImpl implements SecurityAPI {

  @Autowired
  private SecurityDao securityDao;

  @Override
  public Map<Permission.Type, Boolean> checkHasPermissions(User user, Permission.Type... permissionType) {

    return getSecurityDao().checkHasPermissions(user, permissionType);
  }

  @Override
  public boolean hasPermission(User user, Permission.Type permissionType) {
    return getSecurityDao().hasPermission(user, permissionType);
  }

  @Override
  public void grantRolesToUser(User user, Role.RoleType... roles) {
    for (Role.RoleType roleRoleType : roles) {
      Role role = getSecurityDao().loadRole(roleRoleType);
      user.addRoleName(role.getName());
    }
    getSecurityDao().saveOrUpdate(user);
  }

  @Override
  public void revokeRolesFromUser(User user, Role.RoleType... roles) {
    for (Role.RoleType roleRoleType : roles) {
      Role role = getSecurityDao().loadRole(roleRoleType);
      user.getRoleNames().remove(role.getName());
    }
    getSecurityDao().saveOrUpdate(user);

  }

  @Override
  public boolean userExists(String email) {
    return getSecurityDao().userExists(email);
  }

  /**
   * @return the securityDao
   */
  public SecurityDao getSecurityDao() {
    return securityDao;
  }

  /**
   * @param securityDao the securityDao to set
   */
  public void setSecurityDao(SecurityDao securityDao) {
    this.securityDao = securityDao;
  }

  @Override
  public List<String> getPermissionsGrantedToUser(User user) {
    List<String> permissions = new ArrayList<String>();
    for (String roleName : user.getRoleNames()) {
      for (Permission permission : getSecurityDao().loadRole(Role.RoleType.valueOf(roleName)).getPermissions()) {
        permissions.add(permission.getName());
      }
    }
    return permissions;
  }

}
