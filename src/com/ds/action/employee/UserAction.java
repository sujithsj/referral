package com.ds.action.employee;

import com.ds.api.FeatureAPI;
import com.ds.constants.FeatureType;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.dto.user.UserDTO;
import com.ds.pact.service.admin.AdminService;
import com.ds.security.api.SecurityAPI;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class UserAction extends BaseAction {

  private String DEFAULT_USER_PWD = "password";

  private String companyShortName;
  private UserDTO userDTO;


  @Autowired
  private AdminService adminService;
  @Autowired
  private FeatureAPI featureAPI;
  @Autowired
  private SecurityAPI securityAPI;


  public Resolution createEmployee() {

    Company company = getAdminService().getCompany(companyShortName);
    getFeatureAPI().doesCompanyHaveAccessTo(company, FeatureType.EMPLOYEE_COUNT, getAdminService().employeesCount(companyShortName) + 1);
    User user = userDTO.extractUser();
    user.setPassword(DEFAULT_USER_PWD);
    user.setCompanyShortName(company.getShortName());

    getAdminService().addUser(user, userDTO.getRoleTypes());

    return new ForwardResolution("/pages/setup.jsp");
  }


  @Requires(rights = {Type.UPDATE_EMPLOYEE})
  @POST
  @Path("/{companyId}/employee/{employeeId}")
  @Produces("application/json")
  public String updateEmployee(UserDTO userDTO, @PathParam("companyId")
  String companyId, @PathParam("employeeId")
  String employeeId) {

    return updateEmployeeDetails(userDTO, employeeId);
  }

  private String updateEmployeeDetails(UserDTO userDTO, String employeeId) {
    User user = userDTO.extractUser();
    UserSettings userSettings = userDTO.extactUserSettings();

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
        userSettingsToUpdate.setSendEmailOnAssignedPost(true);
        userSettingsToUpdate.setSendEmailOnPost(true);
      } else {
        userSettingsToUpdate.syncWith(userSettings);
      }
      getUserService().saveOrUpdateUserSettings(userSettingsToUpdate);

      userToUpdate.syncWith(user, userDTO.isSyncRoles());
      userToUpdate.setUserSettingsId(userSettingsToUpdate.getId());

      getAdminService().updateUser(userToUpdate);

      // TODO Bug of Update
      if (userDTO.isSyncRoles()) {
        getSecurityAPI().grantRolesToUser(userToUpdate, userDTO.getRoleTypes());
      }

      UserDTO updatedUserDTO = new UserDTO();
      updatedUserDTO.bindUser(userToUpdate, userSettingsToUpdate);
      updatedUserDTO.setAllowedActions(permisisons);

      return new JSONResponse().addField("message", "User Updated Successfully").addField("data", updatedUserDTO).build();
    }

    return new JSONResponse().addField("message", "User Does not Exist").build();
  }


  @POST
  @Path("/{companyId}/employee/self")
  @Produces("application/json")
  public String updateSelfDetails(UserDTO userDTO, @PathParam("companyId")
  String companyId) {
    return updateEmployeeDetails(userDTO, SecurityHelper.getLoggedInUser().getUsername());
  }

  @GET
  @Path("/employee/{employeeEmail}/resetPassword")
  @Produces("application/json")
  public String resetPassword(@PathParam("employeeEmail")
  String employeeEmail) {

    getAdminService().resetEmployeePassword(employeeEmail);

    return new JSONResponse().addField("message", "Password reset successfully").build();
  }

  @POST
  @Path("/{companyId}/employee/{employeeId}/delete")
  @Produces("application/json")
  public String deleteEmployee(@PathParam("companyId")
  String companyId, @PathParam("employeeId")
  String employeeId) {

    getAdminService().deleteEmployee(employeeId);

    return new JSONResponse().addField("message", "Employee deleted successfully").build();
  }

  @POST
  @Path("/{companyId}/employee/{employeeId}/role/{roleName}/delete")
  @Produces("application/json")
  public String deleteEmployeeRole(@PathParam("companyId")
  String companyId, @PathParam("employeeId")
  String employeeId, @PathParam("roleName")
  String roleName) {

    User user = getAdminService().getUser(employeeId);
    if (user != null) {
      getSecurityAPI().revokeRolesFromUser(user, "admin".equals(roleName) ? Role.Type.admin : Role.Type.moderator);
      getCacheAPI().remove(CacheConfig.USER_CACHE, employeeId);
      return new JSONResponse().addField("message", "Employee Role deleted successfully").build();
    } else
      return new JSONResponse().addField("message", "Employee with email: " + employeeId + " Not found").build();
  }

  @GET
  @Path("/{companyShortName}/employee")
  @Produces("application/json")
  public String getEmployees(@PathParam("companyShortName")
  String companyShortName, @QueryParam("start")
  int start, @QueryParam("rows")
  int rows, @QueryParam("sort_by")
  String sortBy, @QueryParam("sort_how")
  String sortHow) {
    List<UserDTO> usersList = new ArrayList<UserDTO>();

    List<User> users = getAdminService().findEmployees(companyShortName, start, rows, sortBy, sortHow);

    User loggedInUser = SecurityHelper.getLoggedInUser();

    Map<Permission.Type, Boolean> permisisons = getSecurityAPI().checkHasPermissions(loggedInUser, Permission.Type.ADD_EMPLOYEE, Permission.Type.UPDATE_EMPLOYEE,
        Permission.Type.DELETE_EMPLOYEE);

    for (User user : users) {
      UserDTO userDTO = new UserDTO();
      userDTO.bindUser(user);
      userDTO.setAllowedActions(permisisons);
      usersList.add(userDTO);
    }

    JSONResponse response = new JSONResponse();
    response.addField("data", usersList);
    response.addField("count", getAdminService().employeesCount(companyShortName));

    return response.build();
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

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }
}
