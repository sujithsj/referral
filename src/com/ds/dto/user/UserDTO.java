package com.ds.dto.user;

import com.ds.domain.core.Permission;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.domain.user.UserSettings;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
public class UserDTO {

  private String fullName;
  private String email;
  private String password;
  private Set<String> roles = new HashSet<String>();
  private Set<String> actions = new HashSet<String>();


  private List<String> rolesToSync;

  //private List<RoleDTO> roles = new ArrayList<RoleDTO>();

  private String originalImageUrl;

  private String thumbnailImageUrl;

  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
  private boolean enabled;

  /**
   * fields for user settings
   */
  private boolean sendEmailOnAddAffiliate;
  private boolean sendEmailOnTerminateAffiliate;
  private boolean sendEmailOnJoinAffiliate;
  private boolean sendEmailOnPayout;
  private boolean sendEmailOnGoalConversion;


  private boolean syncRoles = true;

  private String newPassword;      //to be used only for change passwor requests

  public UserDTO(User user) {
    this.fullName = user.getFullName();
    this.email = user.getEmail();
    this.originalImageUrl = user.getOriginalImageUrl();
    this.thumbnailImageUrl = user.getThumbnailImageUrl();

    this.accountNonExpired = user.isAccountNonExpired();
    this.accountNonLocked = user.isAccountNonLocked();
    this.credentialsNonExpired = user.isCredentialsNonExpired();
    this.enabled = user.isEnabled();

    for (String roleName : user.getRoleNames()) {
      addRole(roleName);
    }
  }

  public UserDTO() {

  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /*public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }*/

  public void addRole(String role) {
    //RoleDTO roleDTO = new RoleDTO(role, true);
    roles.add(role);
  }

  public Set<String> getActions() {
    return actions;
  }

  /**
   * @param actions the actions to set
   */
  public void setActions(Set<String> actions) {
    this.actions = actions;
  }

  public void addAction(String action) {
    actions.add(action);
  }

  public User extractUser() {
    User user = new User();

    user.setUsername(this.email);
    user.setFullName(this.fullName);
    user.setEmail(this.email);
    user.setThumbnailImageUrl(this.thumbnailImageUrl);
    user.setOriginalImageUrl(this.originalImageUrl);

    user.setAccountNonExpired(this.accountNonExpired);
    user.setAccountNonLocked(this.accountNonLocked);
    user.setCredentialsNonExpired(this.credentialsNonExpired);
    user.setEnabled(this.enabled);


    // user.setPassword(this.password);
    return user;
  }

  public UserSettings extactUserSettings() {
    UserSettings userSettings = new UserSettings();
    userSettings.setUsername(this.email);
    userSettings.setSendEmailOnAddAffiliate(this.sendEmailOnAddAffiliate);
    userSettings.setSendEmailOnGoalConversion(this.sendEmailOnGoalConversion);
    userSettings.setSendEmailOnJoinAffiliate(this.sendEmailOnJoinAffiliate);
    userSettings.setSendEmailOnPayout(this.sendEmailOnPayout);
    userSettings.setSendEmailOnTerminateAffiliate(this.sendEmailOnTerminateAffiliate);

    return userSettings;
  }

  public void bindUser(User user) {
    bindUser(user, null, false);
  }

  public void bindUser(User user, UserSettings userSettings) {
    bindUser(user, userSettings, false);
  }

  private void bindUser(User user, UserSettings userSettings, boolean bindPassword) {

    this.email = user.getEmail();
    this.fullName = user.getFullName();
    this.originalImageUrl = user.getOriginalImageUrl();
    this.thumbnailImageUrl = user.getThumbnailImageUrl();

    this.accountNonExpired = user.isAccountNonExpired();
    this.accountNonLocked = user.isAccountNonLocked();
    this.credentialsNonExpired = user.isCredentialsNonExpired();
    this.enabled = user.isEnabled();

    if (bindPassword)
      this.password = user.getPassword();

    for (String roleName : user.getRoleNames()) {
      addRole(roleName);
    }

    if (userSettings != null) {
      this.sendEmailOnAddAffiliate = userSettings.isSendEmailOnAddAffiliate();
      this.sendEmailOnGoalConversion = userSettings.isSendEmailOnGoalConversion();
      this.sendEmailOnJoinAffiliate = userSettings.isSendEmailOnJoinAffiliate();
      this.sendEmailOnPayout = userSettings.isSendEmailOnPayout();
      this.sendEmailOnTerminateAffiliate = userSettings.isSendEmailOnTerminateAffiliate();
    }

  }

  public void setAllowedActions(Map<Permission.Type, Boolean> permissions) {
    for (Map.Entry<Permission.Type, Boolean> entry : permissions.entrySet()) {
      if (entry.getValue().booleanValue()) {
        getActions().add(entry.getKey().toString());
      }
    }
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role.RoleType[] getRoleTypes() {
    Role.RoleType[] types = new Role.RoleType[getRoles().size()];
    int i = 0;
    for (String role : getRoles()) {
      types[i] = Role.RoleType.valueOf(role);
      i++;
    }
    return types;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  /**
   * @return the originalImageUrl
   */
  public String getOriginalImageUrl() {
    return originalImageUrl;
  }

  /**
   * @param originalImageUrl the originalImageUrl to set
   */
  public void setOriginalImageUrl(String originalImageUrl) {
    this.originalImageUrl = originalImageUrl;
  }

  /**
   * @return the thumbnailImageUrl
   */
  public String getThumbnailImageUrl() {
    return thumbnailImageUrl;
  }

  /**
   * @param thumbnailImageUrl the thumbnailImageUrl to set
   */
  public void setThumbnailImageUrl(String thumbnailImageUrl) {
    this.thumbnailImageUrl = thumbnailImageUrl;
  }


  /**
   * @return the syncRoles
   */
  public boolean isSyncRoles() {
    return syncRoles;
  }

  /**
   * @param syncRoles the syncRoles to set
   */
  public void setSyncRoles(boolean syncRoles) {
    this.syncRoles = syncRoles;
  }

  /**
   * @return the newPassword
   */
  public String getNewPassword() {
    return newPassword;
  }

  /**
   * @param newPassword the newPassword to set
   */
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public boolean isSendEmailOnAddAffiliate() {
    return sendEmailOnAddAffiliate;
  }

  public void setSendEmailOnAddAffiliate(boolean sendEmailOnAddAffiliate) {
    this.sendEmailOnAddAffiliate = sendEmailOnAddAffiliate;
  }

  public boolean isSendEmailOnTerminateAffiliate() {
    return sendEmailOnTerminateAffiliate;
  }

  public void setSendEmailOnTerminateAffiliate(boolean sendEmailOnTerminateAffiliate) {
    this.sendEmailOnTerminateAffiliate = sendEmailOnTerminateAffiliate;
  }

  public boolean isSendEmailOnJoinAffiliate() {
    return sendEmailOnJoinAffiliate;
  }

  public void setSendEmailOnJoinAffiliate(boolean sendEmailOnJoinAffiliate) {
    this.sendEmailOnJoinAffiliate = sendEmailOnJoinAffiliate;
  }

  public boolean isSendEmailOnPayout() {
    return sendEmailOnPayout;
  }

  public void setSendEmailOnPayout(boolean sendEmailOnPayout) {
    this.sendEmailOnPayout = sendEmailOnPayout;
  }

  public boolean isSendEmailOnGoalConversion() {
    return sendEmailOnGoalConversion;
  }

  public void setSendEmailOnGoalConversion(boolean sendEmailOnGoalConversion) {
    this.sendEmailOnGoalConversion = sendEmailOnGoalConversion;
  }

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public List<String> getRolesToSync() {
    return rolesToSync;
  }

  public void setRolesToSync(List<String> rolesToSync) {
    this.rolesToSync = rolesToSync;
  }
}
