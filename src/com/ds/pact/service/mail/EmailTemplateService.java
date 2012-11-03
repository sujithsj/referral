package com.ds.pact.service.mail;

import javax.mail.internet.MimeMessage;

/**
 * @author adlakha.vaibhav
 */
public interface EmailTemplateService {

  public enum EmailEventType {
        PostedEmployeeEvent, PostCommentedEmployeeEvent, FeedbackAnswered, PostCommented, PostStatusChanged,
        UserLoggedInThirdPartyEmailConfirmation, UserThirdPartyAssociationConfirmation,UserPasswordResetConfirmation,UserRegistrationConfirmation,
        ClaimReward, WelcomeAffiliate, AffiliatePasswordResetConfirmation
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
