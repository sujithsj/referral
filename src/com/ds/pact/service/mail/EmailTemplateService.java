package com.ds.pact.service.mail;

import javax.mail.internet.MimeMessage;

/**
 * @author adlakha.vaibhav
 */
public interface EmailTemplateService {

  public enum EmailEventType {
        //PostedEmployeeEvent, PostCommentedEmployeeEvent, FeedbackAnswered,
        //PostCommented, PostStatusChanged,
        UserPasswordResetConfirmation,UserRegistrationConfirmation,
        //ClaimReward, 
        WelcomeAffiliate, CompanyAffiliateInvitationEmail, AffiliateWaitingApproval
    }

    /**
     * Creates an Email for the event and passed email context
     *
     * @param event
     * @param context
     * @return
     */
    public MimeMessage createEmail(EmailEventType event, EmailContext context);
}
