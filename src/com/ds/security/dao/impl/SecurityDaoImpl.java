package com.ds.security.dao.impl;

import com.ds.domain.core.Permission;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.impl.dao.BaseDaoImpl;
import com.ds.security.dao.SecurityDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
@Repository
public class SecurityDaoImpl extends BaseDaoImpl implements SecurityDao {

    @Override
    public Map<Permission.Type, Boolean> checkHasPermissions(User user, Permission.Type... permissionType) {

        Map<Permission.Type, Boolean> permissions = new HashMap<Permission.Type, Boolean>();

        StringBuilder sql = new StringBuilder("select p.NAME from USER  u join USER_ROLE ur on u.USERID = ur.USERID " + "join ROLE r on r.NAME = ur.ROLE_NAME "
                + "join ROLE_PERMISSION rp on r.name = rp.ROLE_NAME " + "join PERMISSION p on p.id = rp.PERMISSION_ID "
                + "where u.USERID = ? and p.NAME in ( ");

        Object[] params = new Object[permissionType.length + 1];
        params[0] = user.getUsername();

        for (int i = 1; i <= permissionType.length; i++) {
            params[i] = permissionType[i - 1].toString();
            sql.append(" ? ");
            permissions.put(permissionType[i - 1], Boolean.FALSE);
            if (i != permissionType.length) {
                sql.append(" , ");
            }
        }
        sql.append(" ) ");

        List<String> permissionGrantedToUser = findByNativeSqlNoType(sql.toString(), 0, 0, params);

        for (String permission : permissionGrantedToUser) {
            permissions.put(Permission.Type.valueOf(permission), Boolean.TRUE);
        }

        return permissions;
    }

    @Override
    public boolean hasPermission(User user, Permission.Type permissionType) {

        String hql = "select 1 from User user join user.roles role join role.permissions permission where user.username = ? and permission.name = ?";

        String sql = "select 1 from USER u join USER_ROLE r on u.USERID = user_role.USERID " + "join ROLE r on r.NAME = ur.ROLE_NAME "
                + "join ROLE_PERMISSION rp on r.NAME = rp.ROLE_NAME " + "join PERMISSION p on p.ID = rp.PERMISSION_ID "
                + "where u.USERID = ? and p.NAME = ?";

        return findByNativeSql(sql, 0, 0, new Object[] { user.getUsername(), permissionType.toString() }).size() != 0;
    }

    @Override
    public Role loadRole(Role.RoleType role) {
        return getByQuery("select distinct role from Role role left join fetch role.permissions  where role.name = ? ", role.toString());
    }

    @Override
    public User findUserWithUsername(String username) {
        return getByQuery("from User where username = ?", username);
    }

    /*@Override
    public void associateUserToThirdPartyAuth(User user, ThirdPartyAuth thirdPartyAuth) {
        thirdPartyAuth.setUser(user);
        update(user);
        save(thirdPartyAuth);
    }*/

    @Override
    public void createUser(User user) {
        save(user);
    }

    @Override
    public User findUserCorrespondingToThirdPartyAuth(String authProviderName, String identifier) {
        return getByQuery("select user from User user join user.thirdPartyAuths thirdPartyAuth where thirdPartyAuth.providerName = ? and thirdPartyAuth.identifier = ? ",
                authProviderName, identifier);
    }

    @Override
    public void updateUser(User user) {
        update(user);
    }

    @Override
    public boolean userExists(String email) {
        String hql = "select 1 from User where email = ?";
        return findByQuery(hql, email).size() == 1;
    }

    @Override
    public List<String> getPermissionsGrantedToUser(User user) {
        String hql = "select permission.name from Role role inner join role.permissions permission,User user where role.name in user.roleNames and user = ? ";
        return findByQuery(hql, user);
    }

}
