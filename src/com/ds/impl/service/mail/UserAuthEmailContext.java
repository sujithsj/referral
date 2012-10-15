package com.ds.impl.service.mail;

import com.ds.pact.service.mail.EmailContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public class UserAuthEmailContext extends EmailContext {

  private static final String USER_EMAIL = "userEmail";

  private static final String PROVIDER_NAME = "providerName";

  private static final String PREFERRED_USER_NAME = "preferredUserName";

  private static final String CONFIRMATION_LINK = "confirmationLink";

  private static final String FULL_NAME = "fullName";

  private String fullName;

  private String providerName;

  private String preferredUserName;

  private String confirmationLink;

  private String userEmail;

  /**
   * @return the fullName
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * @param fullName the fullName to set
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * @return the providerName
   */
  public String getProviderName() {
    return providerName;
  }

  /**
   * @param providerName the providerName to set
   */
  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  /**
   * @return the preferredUserName
   */
  public String getPreferredUserName() {
    return preferredUserName;
  }

  /**
   * @param preferredUserName the preferredUserName to set
   */
  public void setPreferredUserName(String preferredUserName) {
    this.preferredUserName = preferredUserName;
  }

  /**
   * @return the confirmationLink
   */
  public String getConfirmationLink() {
    return confirmationLink;
  }

  /**
   * @param confirmationLink the confirmationLink to set
   */
  public void setConfirmationLink(String confirmationLink) {
    this.confirmationLink = confirmationLink;
  }

  /**
   * @return the userEmail
   */
  public String getUserEmail() {
    return userEmail;
  }

  /**
   * @param userEmail the userEmail to set
   */
  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  @Override
  public Map<String, String> getWireRepresentation() {
    Map<String, String> values = new HashMap<String, String>();
    values.put(FULL_NAME, getFullName());
    values.put(CONFIRMATION_LINK, getConfirmationLink());
    values.put(PREFERRED_USER_NAME, getPreferredUserName());
    values.put(PROVIDER_NAME, getProviderName());
    values.put(USER_EMAIL, getUserEmail());
    return values;
  }

  @Override
  public void prepareFromWireRepresentation(Map<String, String> values) {
    this.fullName = values.get(FULL_NAME);
    this.confirmationLink = values.get(CONFIRMATION_LINK);
    this.preferredUserName = values.get(PREFERRED_USER_NAME);
    this.providerName = values.get(PROVIDER_NAME);
    this.userEmail = values.get(USER_EMAIL);
  }

}
