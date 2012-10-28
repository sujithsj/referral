package com.ds.action.employee;

import com.ds.api.CacheAPI;
import com.ds.api.FeatureAPI;
import com.ds.constants.FeatureType;
import com.ds.domain.company.Company;
import com.ds.domain.core.Permission;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.domain.user.UserSettings;
import com.ds.dto.user.UserDTO;
import com.ds.pact.service.admin.AdminService;
import com.ds.security.api.SecurityAPI;
import com.ds.security.helper.SecurityHelper;
import com.ds.security.service.UserService;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
@Component
public class UserAction extends BaseAction {

  private String DEFAULT_USER_PWD = "password";

  private String companyShortName;
  private UserDTO userDTO;

  private String employeeId;
  private String roleName;
  private String employeeEmail;

  private List<Role> userRoles;


  @Autowired
  private AdminService adminService;
  @Autowired
  private FeatureAPI featureAPI;
  @Autowired
  private SecurityAPI securityAPI;
  @Autowired
  private UserService userService;
  @Autowired
  private CacheAPI cacheAPI;


  @DefaultHandler
  public Resolution createOrEditUser() {
    userRoles = getAdminService().getAllRoles();
    if (employeeId != null) {
      User user = getUserService().getUser(employeeId);
      UserSettings userSettings = getUserService().getUserSettings(user.getUsername());
      userDTO = new UserDTO();
      userDTO.bindUser(user, userSettings);
    }
    return new ForwardResolution("/pages/company/userCrud.jsp");
  }

  public Resolution saveUser() {
    if (employeeId != null) {
      return updateEmployee();
    } else {
      return createEmployee();
    }
  }

  public Resolution createEmployee() {

    User loggedInUser = SecurityHelper.getLoggedInUser();
    companyShortName = loggedInUser.getCompanyShortName();
    Company company = getAdminService().getCompany(companyShortName);
    getFeatureAPI().doesCompanyHaveAccessTo(company, FeatureType.EMPLOYEE_COUNT, getAdminService().employeesCount(companyShortName) + 1);

    /*//below null check added by rahul as userDTO is null for Add new user.
      if(userDTO == null){
        userDTO = new UserDTO();
      }*/
    User user = userDTO.extractUser();
    user.setPassword(DEFAULT_USER_PWD);
    user.setCompanyShortName(company.getShortName());

    getAdminService().addUser(user, userDTO.getRoleTypes());

    return new ForwardResolution("/pages/setup.jsp");
  }


  public Resolution updateEmployee() {

    return updateEmployeeDetails(userDTO, employeeId);
  }

  private Resolution updateEmployeeDetails(UserDTO userDTOForUpdate, String employeeId) {
    User user = userDTOForUpdate.extractUser();
    UserSettings userSettings = userDTOForUpdate.extactUserSettings();

    User userToUpdate = getAdminService().getUser(employeeId);
    UserSettings userSettingsToUpdate = getAdminService().getUserSettings(userToUpdate.getUsername());

    Map<Permission.Type, Boolean> permisisons = getSecurityAPI().checkHasPermissions(SecurityHelper.getLoggedInUser(), Permission.Type.ADD_EMPLOYEE,
        Permission.Type.UPDATE_EMPLOYEE, Permission.Type.DELETE_EMPLOYEE);

    if (userToUpdate != null) {
      if (userSettingsToUpdate == null) {
        userSettingsToUpdate = new UserSettings();
        userSettingsToUpdate.setCreatedDate(new Date());
        userSettingsToUpdate.setCreatedBy(userToUpdate.getUsername());
        userSettingsToUpdate.setUsername(userToUpdate.getUsername());
        userSettingsToUpdate.setSendEmailOnPayout(true);
        userSettingsToUpdate.setSendEmailOnTerminateAffiliate(true);
        userSettingsToUpdate.setSendEmailOnAddAffiliate(true);
        userSettingsToUpdate.setSendEmailOnGoalConversion(true);
        userSettingsToUpdate.setSendEmailOnJoinAffiliate(true);
      } else {
        userSettingsToUpdate.syncWith(userSettings);
      }
      getUserService().saveOrUpdateUserSettings(userSettingsToUpdate);

      userToUpdate.syncWith(user, userDTOForUpdate.isSyncRoles());
      userToUpdate.setUserSettingsId(userSettingsToUpdate.getId());

      getAdminService().updateUser(userToUpdate);

      // TODO Bug of Update
      if (userDTOForUpdate.isSyncRoles()) {
        getSecurityAPI().grantRolesToUser(userToUpdate, userDTOForUpdate.getRoleTypes());
      }

      UserDTO updatedUserDTO = new UserDTO();
      updatedUserDTO.bindUser(userToUpdate, userSettingsToUpdate);
      updatedUserDTO.setAllowedActions(permisisons);

      userDTO = updatedUserDTO;
    }
    return new ForwardResolution("/pages/setup.jsp");
  }


  public Resolution updateSelfDetails() {
    return updateEmployeeDetails(userDTO, SecurityHelper.getLoggedInUser().getUsername());
  }


  public Resolution resetPassword() {
    getAdminService().resetEmployeePassword(employeeEmail);
    return new ForwardResolution("/pages/setup.jsp");
  }


  public Resolution deleteEmployee() {
    getAdminService().deleteEmployee(employeeId);
    return new ForwardResolution("/pages/setup.jsp");
  }


  public Resolution deleteEmployeeRole() {
    User user = getAdminService().getUser(employeeId);
    if (user != null) {
      getSecurityAPI().revokeRolesFromUser(user, "admin".equals(roleName) ? Role.RoleType.admin : Role.RoleType.moderator);
      getCacheAPI().remove(CacheAPI.CacheConfig.USER_CACHE, employeeId);

    }

    return new ForwardResolution("/pages/setup.jsp");
  }


  public Resolution getEmployees() {
    /*List<UserDTO> usersList = new ArrayList<UserDTO>();

    List<User> users = getAdminService().findEmployees(companyShortName, start, rows, sortBy, sortHow);

    User loggedInUser = SecurityHelper.getLoggedInUser();

    Map<Permission.Type, Boolean> permisisons = getSecurityAPI().checkHasPermissions(loggedInUser, Permission.Type.ADD_EMPLOYEE, Permission.Type.UPDATE_EMPLOYEE,
        Permission.Type.DELETE_EMPLOYEE);

    for (User user : users) {
      UserDTO userDTO = new UserDTO();
      userDTO.bindUser(user);
      userDTO.setAllowedActions(permisisons);
      usersList.add(userDTO);
    }*/

    return new ForwardResolution("/pages/setup.jsp");
  }

  public UserDTO getUserDTO() {
    return userDTO;
  }

  public void setUserDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
  }

  public AdminService getAdminService() {
    return adminService;
  }

  public FeatureAPI getFeatureAPI() {
    return featureAPI;
  }

  public UserService getUserService() {
    return userService;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public SecurityAPI getSecurityAPI() {
    return securityAPI;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public CacheAPI getCacheAPI() {
    return cacheAPI;
  }

  public String getEmployeeEmail() {
    return employeeEmail;
  }

  public void setEmployeeEmail(String employeeEmail) {
    this.employeeEmail = employeeEmail;
  }

  public List<Role> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<Role> userRoles) {
    this.userRoles = userRoles;
  }
}
