package com.ds.core.event;

import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.mail.EmailTemplateService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public class UserLoginEmailConfirmationRequestEvent implements AsyncEvent {


  private static final String USER_LOGIN_EMAIL_CONFIRMATION_REQUEST_ID = "UserLoginConfirmationRequest.Id";
  private static final String USER_LOGIN_EVENT_TYPE = "UserLoginEvent.Type";

  private UserLoginConfirmationRequest userLoginConfirmationRequest;
  private EmailTemplateService.EmailEventType emailEventType;

  public UserLoginEmailConfirmationRequestEvent() {
  }

  public UserLoginEmailConfirmationRequestEvent(UserLoginConfirmationRequest userLoginConfirmationRequest,
                                                EmailTemplateService.EmailEventType emailEventType) {
    this.userLoginConfirmationRequest = userLoginConfirmationRequest;
    this.emailEventType = emailEventType;
  }

  /**
   * @return the userLoginConfirmationRequest
   */
  public UserLoginConfirmationRequest getUserLoginConfirmationRequest() {
    return userLoginConfirmationRequest;
  }

  /**
   * @return
   */
  public EmailTemplateService.EmailEventType getEmailEventType() {
    return emailEventType;
  }

  @Override
  public Map<String, String> getWireRepresentation() {
    Map<String, String> data = new HashMap<String, String>();
    data.put(USER_LOGIN_EMAIL_CONFIRMATION_REQUEST_ID, String.valueOf(userLoginConfirmationRequest.getId()));
    data.put(USER_LOGIN_EVENT_TYPE, emailEventType.toString());
    return data;
  }

  @Override
  public void prepareFromWireRepresentation(Map<String, String> values) {
    String userLoginConfirmationRequestId = values.get(USER_LOGIN_EMAIL_CONFIRMATION_REQUEST_ID);
    this.userLoginConfirmationRequest = ServiceLocatorFactory.getService(AdminService.class).loadUserLoginConfirmationRequest(Long.parseLong(userLoginConfirmationRequestId));
    /*this.emailEventType = values.get(USER_LOGIN_EVENT_TYPE).equals(EmailTemplateService.EmailEventType.UserLoggedInThirdPartyEmailConfirmation.toString()) ?
        EmailTemplateService.EmailEventType.UserLoggedInThirdPartyEmailConfirmation : EmailTemplateService.EmailEventType.UserRegistrationConfirmation;*/

      this.emailEventType = EmailTemplateService.EmailEventType.UserRegistrationConfirmation;
  }

}
