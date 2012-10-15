package com.ds.domain.user;

import com.ds.domain.BaseDataObject;

/**
 * @author adlakha.vaibhav
 */
public class UserSettings extends BaseDataObject {

  private long id;
  private boolean sendEmailOnPost;
  private boolean sendEmailOnAssignedPost;
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

  /**
   * @return the sendEmailOnPost
   */
  public boolean isSendEmailOnPost() {
    return sendEmailOnPost;
  }

  /**
   * @param sendEmailOnPost the sendEmailOnPost to set
   */
  public void setSendEmailOnPost(boolean sendEmailOnPost) {
    this.sendEmailOnPost = sendEmailOnPost;
  }

  /**
   * @return the sendEmailOnAssignedPost
   */
  public boolean isSendEmailOnAssignedPost() {
    return sendEmailOnAssignedPost;
  }

  /**
   * @param sendEmailOnAssignedPost the sendEmailOnAssignedPost to set
   */
  public void setSendEmailOnAssignedPost(boolean sendEmailOnAssignedPost) {
    this.sendEmailOnAssignedPost = sendEmailOnAssignedPost;
  }

  public void syncWith(UserSettings userSettings) {
    this.sendEmailOnAssignedPost = userSettings.isSendEmailOnAssignedPost();
    this.sendEmailOnPost = userSettings.isSendEmailOnPost();

  }
}
