package com.ds.domain.core.event;

import com.ds.exception.DSException;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.mail.EmailContext;
import com.ds.pact.service.mail.EmailTemplateService;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public class EmailEvent implements AsyncEvent {

  private EmailTemplateService.EmailEventType eventType;
  private EmailContext context;
  //private PostDAO        postDAO;
  private AdminService adminService;

  // default constructor to make it serializable
  public EmailEvent() {

  }

  public EmailEvent(EmailTemplateService.EmailEventType eventType, EmailContext context) {
    this.eventType = eventType;
    this.context = context;
  }

  /**
   * @return the eventType
   */
  public EmailTemplateService.EmailEventType getEventType() {
    return eventType;
  }

  /**
   * @return the context
   */
  public EmailContext getContext() {
    return context;
  }

  public void setReceivers(MimeMessageHelper mimeMessageHelper) throws MessagingException {
    switch (eventType) {
      case PostStatusChanged:
      case PostCommented:
      case FeedbackAnswered:
        /* Post post1 = ((PostContext) context).getPost();
       List<String> followerEmails = getPostDAO().findFollowerEmails(post1.getId());
       mimeMessageHelper.setBcc(followerEmails.toArray(new String[followerEmails.size()]));*/
        break;
      case PostCommentedEmployeeEvent:
        /* Post post2 = ((PostContext) context).getPost();
        // It May happen that after an Email Event was queue and before it was processed Employee was unassigned
        // from Post
        if (post2.getEmployee() != null) {
          mimeMessageHelper.setTo(getAdminService().getUser(post2.getEmployee()).getEmail());
        } else {
          throw new UserrulesException("EMPLOYEE_UNASSIGNED_FROM_POST_AFTER_EMAIL_EVENT_RAISED", post2.getId());
        }*/
        break;
      case PostedEmployeeEvent:
        /*Post post3 = ((PostContext) context).getPost();
        List<String> employeeEmails = getPostDAO().findEmployeeEmailsInterestedInPostEmailNotifications(post3.getCompanyShortName());
        mimeMessageHelper.setBcc(employeeEmails.toArray(new String[employeeEmails.size()]));*/
        break;

      case UserRegistrationConfirmation:
      case UserLoggedInThirdPartyEmailConfirmation:
        /*UserAuthEmailContext userThirdPartyAuthEmailContext = (UserAuthEmailContext) context;
        mimeMessageHelper.setTo(userThirdPartyAuthEmailContext.getUserEmail());*/
        break;
      case UserPasswordResetConfirmation:
        /*UserContext userContext = (UserContext) context;
        mimeMessageHelper.setTo(userContext.getUser().getEmail());*/
        break;
      case ClaimReward:
        /*ClaimRewardContext claimRewardContext = (ClaimRewardContext) context;
        mimeMessageHelper.setTo(claimRewardContext.getUser().getEmail());*/
        break;
    }
  }

  @Override
  public Map<String, String> getWireRepresentation() {
    Map<String, String> data = new HashMap<String, String>();
    data.putAll(context.getWireRepresentation());
    data.put("EventType", eventType.toString());
    data.put("ContextClass", context.getClass().getName());
    return data;
  }

  @Override
  public void prepareFromWireRepresentation(Map<String, String> data) {
    this.eventType = EmailTemplateService.EmailEventType.valueOf(data.get("EventType"));
    try {
      Class emailContextClass = Class.forName(data.get("ContextClass"));
      this.context = (EmailContext) emailContextClass.newInstance();
      this.context.prepareFromWireRepresentation(data);
    } catch (Exception e) {
      throw new DSException("ERROR_IN_DESERIALIZATION", e);
    }
  }

  /* *//**
   * @return the postDAO
   *//*
    public PostDAO getPostDAO() {
        if (this.postDAO == null) {
            this.postDAO = ServiceLocatorFactory.getService(PostDAO.class);
        }
        return postDAO;
    }

    *//**
   * @param postDAO the postDAO to set
   *//*
    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }*/

  /**
   * @return the adminService
   */
  public AdminService getAdminService() {
    if (this.adminService == null) {
      this.adminService = ServiceLocatorFactory.getService(AdminService.class);
    }
    return adminService;
  }

  /**
   * @param adminService the adminService to set
   */
  public void setAdminService(AdminService adminService) {
    this.adminService = adminService;
  }
}