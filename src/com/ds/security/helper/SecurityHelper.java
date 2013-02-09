package com.ds.security.helper;

import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author adlakha.vaibhav
 */
public class SecurityHelper {

  public static User getLoggedInUser() {
        User user = null;
        try {
          /*UserService userService = (UserService) ServiceLocatorFactory.getService(UserService.class);
          user = (User)userService.getUser("abc");*/
          //TODO: remove this hardcoding
          user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException cce) {      
            return null;
        } catch (NullPointerException npe) {
            return null;
        }
        return user;
    }

    public static User getLoggedInUserOrAnonymousUser() {
        User user = getLoggedInUser();
        if (user == null) {
            User anonymous = new User();
            anonymous.setUsername(getAnonymousUserName());
            return anonymous;
        }
        return user;
    }

    public static String getAnonymousUserName() {
        return "anonymous";
    }

    /**
     * Returns true if the currently logged in user is associated with a company i.e. is a company employee.
     *
     * @return
     */
    public static boolean isLoggedInUserCompanyEmployee() {
        User user = getLoggedInUser();
        if (user != null) {
            return user.getCompanyShortName() != null ? true : false;
        }
        return false;
    }

    /**
     * Returns true if the currently logged in user has a admin/moderator role.
     *
     * @return
     */
    public static boolean isLoggedInUserAdminOrModerator() {
        return isUserAdminOrModerator(getLoggedInUser());
    }

    public static boolean isUserAdminOrModerator(User user) {
        boolean result = false;
        if (user != null) {
            for (String roleName : user.getRoleNames()) {

                if ((Role.RoleType.admin.toString().equalsIgnoreCase(roleName) || Role.RoleType.moderator.toString().equalsIgnoreCase(roleName)))
                    result = true;
                break;
            }
        }
        return result;
    }

    public static boolean isLoggedInUserStoreEmployee() {
        User user = getLoggedInUser();
        if (user != null) {
           /* if (!StringUtils.isEmpty(user.getCompanyShortName())) {
                Company company = ServiceLocatorFactory.getService(AdminService.class).getCompany(user.getCompanyShortName());
                return !company.isVendor();
            }*/
        }
        return false;
    }

	//todo might have to handle this via spring security, doing the basic stuff for now.
	public static void logoutUser() {
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();

	}

}
