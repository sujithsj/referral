package com.ds.action.employee;

import com.ds.domain.user.User;
import com.ds.security.service.UserService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BaseAction;
import com.ds.exception.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import net.sourceforge.stripes.action.*;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 13, 2013
 * Time: 11:47:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MyProfileAction extends BaseAction {

  private String fullName;
  private String oldPassword;
  private String newPassword;
  private String confirmPassword;

  @Autowired
  private MessageDigestPasswordEncoder messageDigestPasswordEncoder;
  @Autowired
  private UserService userService;


  @DefaultHandler
  @DontValidate
  public Resolution pre(){
     User loggedOnUser = SecurityHelper.getLoggedInUser();
    this.fullName = loggedOnUser.getFullName();

    return new ForwardResolution("/pages/myProfile.jsp");
  }

  public Resolution updateUserProfile() {

    if (!newPassword.equals(confirmPassword)) {
      throw new InvalidParameterException("PASSWORDS_DONT_MATCH");
    }

    User loggedOnUser = SecurityHelper.getLoggedInUser();
    User userToUpdate = getUserService().getUser(loggedOnUser.getUsername());

    String oldPasswordEncoded = getMessageDigestPasswordEncoder().encodePassword(oldPassword, userToUpdate.getUsername());

    if (!userToUpdate.getPassword().equals(oldPasswordEncoded)) {
      throw new InvalidParameterException("OLD_PASSWORD_IS_INCORRECT");
    }

    String newPasswordEncoded = getMessageDigestPasswordEncoder().encodePassword(newPassword, userToUpdate.getUsername());
    userToUpdate.setPassword(newPasswordEncoded);
    userToUpdate.setFullName(fullName);

    getUserService().updateUser(userToUpdate);

    //TODO: fire an email to user for pwd change
    return new RedirectResolution(MyProfileAction.class);

  }


  public MessageDigestPasswordEncoder getMessageDigestPasswordEncoder() {
    return messageDigestPasswordEncoder;
  }

  public UserService getUserService() {
    return userService;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
