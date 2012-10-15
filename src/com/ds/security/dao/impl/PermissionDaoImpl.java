package com.ds.security.dao.impl;

import com.ds.domain.core.Permission;
import com.ds.impl.dao.BaseDaoImpl;
import com.ds.security.dao.PermissionDao;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
@Repository
public class PermissionDaoImpl extends BaseDaoImpl implements PermissionDao {

  @SuppressWarnings("unchecked")
  public List<Permission> getPermissions(String userName, String entityName, String entityType, String entityQualifier, int accessType) {
    DetachedCriteria permissionCriteria = DetachedCriteria.forClass(Permission.class);

    if (StringUtils.isNotEmpty(entityName)) {
      permissionCriteria.add(Restrictions.eq("entityName", entityName));
    }

    if (StringUtils.isNotEmpty(entityType)) {
      permissionCriteria.add(Restrictions.eq("entityType", entityType));
    }

    if (StringUtils.isNotEmpty(entityQualifier)) {
      permissionCriteria.add(Restrictions.eq("entityQualifier", entityQualifier));
    }

    if (accessType != 0) {
      permissionCriteria.add(Restrictions.eq("accessType", accessType));
    }

    permissionCriteria.createCriteria("roles", DetachedCriteria.INNER_JOIN).createCriteria("users", DetachedCriteria.INNER_JOIN).add(Restrictions.eq("userName", userName));

    return findByCriteria(permissionCriteria);

  }

  @SuppressWarnings("unchecked")
  public List<Permission> getPermissionByUserAndEntityType(String username, String entityType) {
    String query = "SELECT p FROM Permission p LEFT JOIN FETCH p.users u WHERE u.username = ? AND p.entityType = ?";
    return findByQuery(query, new String[]{username, entityType});
  }

  @SuppressWarnings("unchecked")
  public List<Permission> getPermissionByRoleAndEntityType(List<Long> roleIdList, String entityType) {
    if (roleIdList.size() > 0) {
      Iterator<Long> iter = roleIdList.iterator();
      StringBuilder keys = new StringBuilder();
      keys.append(iter.next());
      while (iter.hasNext()) {
        keys.append(",").append(iter.next());
      }
      String query = "SELECT p FROM Permission p LEFT JOIN FETCH p.roles r WHERE r.id in (" + keys + ") AND p.entityType = ?";
      return findByQuery(query, new Object[]{entityType});
    } else {
      return Collections.EMPTY_LIST;
    }

  }

  @SuppressWarnings("unchecked")
  public Permission getPermission(String permissionName) {
    List<Permission> results = findByQuery("from Permission where name = ?", new Object[]{permissionName});
    if (results.isEmpty()) {
      return null;
    }
    return results.get(0);
  }
}