package com.ds.impl.service.mail;

import com.ds.domain.user.User;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.mail.EmailContext;
import com.ds.utils.SystemSettings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public class UserContext extends EmailContext {

  private static final String USER_CONTEXT_USER_EMAIL = "userContext.user.email";
  private static final String USER_CONTEXT_GENERATED_PASSWORD = "userContext.user.generated.password";

  private User user;
  private String generatedPassword;

  // default constructor to make it serializable
  public UserContext() {

  }

  public UserContext(User user) {
    this.user = user;
  }

  public UserContext(User user, String generatedPassword) {
    this.user = user;
    this.generatedPassword = generatedPassword;
  }

  public User getUser() {
    return user;
  }

  /**
   * @return the generatedPassword
   */
  public String getPassword() {
    return generatedPassword;
  }

  public String getUserName() {
    return this.user != null ? user.getFullName() : null;
  }

  public String getAdminLoginUrl() {
    return SystemSettings.getBaseUrl() + "admin/login";
  }

  @Override
  public Map<String, String> getWireRepresentation() {
    Map<String, String> data = new HashMap<String, String>();

    if (getUser() != null) {
      data.put(USER_CONTEXT_USER_EMAIL, getUser().getEmail());
    }
    data.put(USER_CONTEXT_GENERATED_PASSWORD, generatedPassword);
    return data;
  }

  @Override
  public void prepareFromWireRepresentation(Map<String, String> data) {
    if (data.containsKey(USER_CONTEXT_USER_EMAIL)) {
      this.user = ServiceLocatorFactory.getService(AdminService.class).getUserByEmail(data.get(USER_CONTEXT_USER_EMAIL));
    }
    this.generatedPassword = data.get(USER_CONTEXT_GENERATED_PASSWORD);

  }

}

