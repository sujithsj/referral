package com.ds.security.dao;

import com.ds.domain.core.Permission;
import com.ds.pact.dao.BaseDao;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public interface PermissionDao extends BaseDao {

	List<Permission> getPermissions(String userName,String	entityName,String entityType,String entityQualifier,int accessType);

	List<Permission> getPermissionByUserAndEntityType(String username, String entityType);

	List<Permission> getPermissionByRoleAndEntityType(List<Long> roleIdList, String entityType);

	Permission getPermission(String permissionName);
}
