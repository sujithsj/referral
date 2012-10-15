package com.ds.domain.user;

import com.ds.domain.BaseDataObject;

/**
 * @author adlakha.vaibhav
 */
public class UserLoginConfirmationRequest extends BaseDataObject {

  private long id;

  // google, facebook, twitter
  private String providerName;
  // compulsory
  private String verifiedEmail;

  private String name;

  private String identifier;

  private String preferredUsername;

  private String confirmationKey;

  private String authInfoJson;

  private boolean confirmed;

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * ~
   *
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
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
   * @return the verifiedEmail
   */
  public String getVerifiedEmail() {
    return verifiedEmail;
  }

  /**
   * @param verifiedEmail the verifiedEmail to set
   */
  public void setVerifiedEmail(String verifiedEmail) {
    this.verifiedEmail = verifiedEmail;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the identifier
   */
  public String getIdentifier() {
    return identifier;
  }

  /**
   * @param identifier the identifier to set
   */
  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  /**
   * @return the confirmationKey
   */
  public String getConfirmationKey() {
    return confirmationKey;
  }

  /**
   * @param confirmationKey the confirmationKey to set
   */
  public void setConfirmationKey(String confirmationKey) {
    this.confirmationKey = confirmationKey;
  }

  /**
   * @return the confirmed
   */
  public boolean isConfirmed() {
    return confirmed;
  }

  /**
   * @param confirmed the confirmed to set
   */
  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }

  /**
   * @return the preferredUsername
   */
  public String getPreferredUsername() {
    return preferredUsername;
  }

  /**
   * @param preferredUsername the preferredUsername to set
   */
  public void setPreferredUsername(String preferredUsername) {
    this.preferredUsername = preferredUsername;
  }

  public String getAuthInfoJson() {
    return authInfoJson;
  }

  public void setAuthInfoJson(String authInfoJson) {
    this.authInfoJson = authInfoJson;
  }

}
