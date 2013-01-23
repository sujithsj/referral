package com.ds.pact.service.mail;

import com.ds.domain.affiliate.Affiliate;

import javax.mail.internet.MimeMessage;

/**
 * @author adlakha.vaibhav
 */
public interface EmailTemplateService {

  public enum EmailEventType {
        PostedEmployeeEvent, PostCommentedEmployeeEvent, FeedbackAnswered, PostCommented, PostStatusChanged,
        UserLoggedInThirdPartyEmailConfirmation, UserThirdPartyAssociationConfirmation,UserPasswordResetConfirmation,UserRegistrationConfirmation,
        ClaimReward, WelcomeAffiliate, AffiliatePasswordResetConfirmation, AffiliateInvitationEmail, CompanyAffiliatePendingForApproval, AffiliateWaitingApproval
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
