package com.ds.core.event;

import com.ds.exception.DSException;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.impl.service.mail.AffiliateContext;
import com.ds.impl.service.mail.CompanyAffiliateInvEmailContext;
import com.ds.impl.service.mail.UserAuthEmailContext;
import com.ds.impl.service.mail.UserContext;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.mail.EmailContext;
import com.ds.pact.service.mail.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public class EmailEvent implements AsyncEvent {


    private Logger logger = LoggerFactory.getLogger(EmailEvent.class);

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
            case UserRegistrationConfirmation:
                UserAuthEmailContext userThirdPartyAuthEmailContext = (UserAuthEmailContext) context;
                mimeMessageHelper.setTo(userThirdPartyAuthEmailContext.getUserEmail());
                break;
            case UserPasswordResetConfirmation:
                UserContext userContext = (UserContext) context;
                mimeMessageHelper.setTo(userContext.getUser().getEmail());
                break;
            case WelcomeAffiliate:
                AffiliateContext affiliateContext = (AffiliateContext) context;
                mimeMessageHelper.setTo(affiliateContext.getAffiliate().getEmail());
                break;
            case CompanyAffiliateInvitationEmail:
                CompanyAffiliateInvEmailContext companyAffiliateInvEmailContext = (CompanyAffiliateInvEmailContext) context;
                mimeMessageHelper.setTo(companyAffiliateInvEmailContext.getCompanyAffiliateInvite().getAffiliateEmail());
                break;
            case AffiliateWaitingApproval:
                affiliateContext = (AffiliateContext) context;
                mimeMessageHelper.setTo(affiliateContext.getAffiliate().getEmail());
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
            logger.error("ERROR_IN_DESERIALIZATION", e);
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