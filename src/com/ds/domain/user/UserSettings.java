package com.ds.domain.user;

import com.ds.domain.BaseDataObject;

/**
 * @author adlakha.vaibhav
 */
public class UserSettings extends BaseDataObject {

  private long id;
  private boolean sendEmailOnAddAffiliate;
  private boolean sendEmailOnTerminateAffiliate;
  private boolean sendEmailOnJoinAffiliate;
  private boolean sendEmailOnPayout;
  private boolean sendEmailOnGoalConversion;
  private String username;

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }


  public void syncWith(UserSettings userSettings) {
    this.sendEmailOnAddAffiliate = userSettings.isSendEmailOnAddAffiliate();
    this.sendEmailOnTerminateAffiliate = userSettings.isSendEmailOnTerminateAffiliate();
    this.sendEmailOnJoinAffiliate = userSettings.isSendEmailOnJoinAffiliate();
    this.sendEmailOnPayout = userSettings.isSendEmailOnPayout();
    this.sendEmailOnGoalConversion = userSettings.isSendEmailOnGoalConversion();

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
}
