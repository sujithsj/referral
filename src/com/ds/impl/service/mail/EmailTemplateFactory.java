package com.ds.impl.service.mail;

import com.ds.core.event.UserLoginEmailConfirmationRequestEvent;
import com.ds.pact.service.mail.EmailTemplateService;

import java.util.Map;
import java.util.HashMap;

/**
 * @author adlakha.vaibhav
 */
public class EmailTemplateFactory {

  private Map<String, EmailTemplate> emailTemplates = new HashMap<String, EmailTemplate>();


  public static EmailTemplate getEmailTemplate(String templateKey){
    if("UserPasswordResetConfirmation".equals(templateKey)){
      EmailTemplate emailTemplate = new EmailTemplate();
      emailTemplate.setSubject("DS Password Reset Confirmation!");
      emailTemplate.setHtml(true);
      emailTemplate.setBodyTemplateName("velocity/UserPasswordResetConfirmation.vm");
      return emailTemplate;
    } else if("WelcomeAffiliate".equals(templateKey)){
      EmailTemplate emailTemplate = new EmailTemplate();
      emailTemplate.setSubject("Welcome to affiliate Program!!");
      emailTemplate.setHtml(true);
      emailTemplate.setBodyTemplateName("velocity/WelcomeAffiliate.vm");
      return emailTemplate;
    } else if("CompanyAffiliateInvitationEmail".equals(templateKey)){
      EmailTemplate emailTemplate = new EmailTemplate();
      emailTemplate.setSubject("Signup for affiliate Program!!");
      emailTemplate.setHtml(true);
      emailTemplate.setBodyTemplateName("velocity/AffiliateInvitation.vm");
      return emailTemplate;
    } else if("AffiliateWaitingApproval".equals(templateKey)){
      EmailTemplate emailTemplate = new EmailTemplate();
      emailTemplate.setSubject("Thanks for signing up. Your request is waiting for approval");
      emailTemplate.setHtml(true);
      emailTemplate.setBodyTemplateName("velocity/AffiliateWaitingApproval.vm");
      return emailTemplate;
    }
    else if(EmailTemplateService.EmailEventType.UserRegistrationConfirmation.toString().equals(templateKey)){
      EmailTemplate emailTemplate = new EmailTemplate();
      emailTemplate.setSubject("Thanks for signing up. Please verify your account");
      emailTemplate.setHtml(true);
      emailTemplate.setBodyTemplateName("velocity/"+ EmailTemplateService.EmailEventType.UserRegistrationConfirmation.toString() + ".vm");
      return emailTemplate;
    }  

    return null;
  }
}
