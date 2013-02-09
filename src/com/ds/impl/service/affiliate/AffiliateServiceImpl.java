package com.ds.impl.service.affiliate;

import com.ds.api.AdminAPI;
import com.ds.api.CacheAPI;
import com.ds.api.FeatureAPI;
import com.ds.core.event.EmailEvent;
import com.ds.core.event.EventDispatcher;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateInvite;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.domain.core.Role;
import com.ds.dto.affiliate.AffiliateDTO;
import com.ds.dto.affiliate.AffiliateSignupResponse;
import com.ds.exception.CompositeValidationException;
import com.ds.exception.ValidationConstants;
import com.ds.exception.ValidationException;
import com.ds.exception.FeatureNotAccessibleException;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.impl.service.mail.AffiliateContext;
import com.ds.pact.dao.AdminDAO;
import com.ds.pact.dao.affiliate.AffiliateDao;
import com.ds.pact.service.HttpService;
import com.ds.pact.service.admin.LoadPropertyService;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.mail.EmailTemplateService;
import com.ds.pact.service.mail.MailService;
import com.ds.search.impl.AffiliateQuery;
import com.ds.security.api.SecurityAPI;
import com.ds.web.action.Page;
import com.ds.constants.AppConstants;
import com.ds.constants.FeatureType;
import com.ds.constants.EnumCompanyAffiliateSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:42:00 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AffiliateServiceImpl implements AffiliateService {


    private Logger logger = LoggerFactory.getLogger(AffiliateServiceImpl.class);

    @Autowired
    private AffiliateDao affiliateDao;
    @Autowired
    private MailService mailService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private FeatureAPI featureAPI;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyAffiliateService companyAffiliateService;


    @Transactional
    public AffiliateSignupResponse signupAffiliate(AffiliateDTO affiliateDTO, String companyShortName, boolean isAutoCreate) {
        AffiliateSignupResponse response = new AffiliateSignupResponse();

        String affiliateLogin = affiliateDTO.getEmail();
        User user = null;
        boolean isUserIdTaken = getAdminService().isUserIdTaken(affiliateLogin);

        if (isUserIdTaken) {
            user = getAdminService().getUser(affiliateLogin);
        }

        //TODO: validate company exists and return error if not
        Company company = getAdminService().getCompany(companyShortName);
        Affiliate affiliate = getAffiliateByLogin(affiliateLogin);

        boolean newAffAdded = false, affExistsForCompany = false, affInviteConverted = false, companyAffAdded = false;

        if (user == null) {
            // user does not exist with his email id, so we will create a new one

            String userFullName = StringUtils.isNotBlank(affiliateDTO.getFirstName()) ? affiliateDTO.getFirstName().concat(affiliateDTO.getLastName()) : null;
            user = new User();

            user.setUsername(affiliateLogin);
            if (StringUtils.isNotBlank(userFullName)) {
                user.setFullName(userFullName);
            } else {
                user.setFullName(affiliateLogin);
            }
            user.setPassword(affiliateDTO.getPassword());
            user.setEmail(affiliateLogin);
            user.setCompanyShortName(AppConstants.SYS_COMPANY_CODE);

            getAdminService().addUser(user, new Role.RoleType[]{Role.RoleType.affiliate});
        }

        if (affiliate == null) {
            // user does exist, check is he an affiliate
            affiliate = affiliateDTO.extractAffiliate(affiliate);
            affiliate = (Affiliate) getAffiliateDAO().save(affiliate);
            newAffAdded = true;
        }

        // by now we have user and affiliate created for sure , so check if affiliate already exists for company
        CompanyAffiliate companyAffiliate = null;
        if (company != null) {
            companyAffiliate = getCompanyAffiliateService().getCompanyAffiliate(affiliate.getId(), companyShortName);

            if (companyAffiliate != null) {
                affExistsForCompany = true;
            } else {
                // since aff is not added for company we need to check for existing invite and add it

                try {
                    /*if (companyAffiliate.getActive()) {*/
                    getFeatureAPI().doesCompanyHaveAccessTo(company, FeatureType.AFFILIATE_COUNT,
                            getCompanyAffiliateService().getActiveCompanyAffiliateCount(companyShortName) + 1);
                    //}
                } catch (FeatureNotAccessibleException fnae) {
                    System.out.println(fnae.getI18nMessage().getMessageParams().toString());
                    fnae.printStackTrace();
                    //TODO: logger
                }

                companyAffiliate = new CompanyAffiliate();
                companyAffiliate.setAffiliate(affiliate);
                companyAffiliate.setCompanyShortName(companyShortName);

                if(isAutoCreate){
                    companyAffiliate.setSource(EnumCompanyAffiliateSource.REFERAL.getId());
                }

                CompanyAffiliateInvite companyAffiliateInvite = getCompanyAffiliateService().getCompanayAffiliateInvite(companyShortName, affiliateLogin);

                if (companyAffiliateInvite != null) {
                    // mark an existing invite as converted
                    companyAffiliateInvite.setConverted(true);
                    affInviteConverted = true;
                }

                //aff is active either on invite or auto creation
                if (isAutoCreate || companyAffiliateInvite != null) {
                    companyAffiliate.setActive(true);
                } else {
                    companyAffiliate.setActive(false);
                }

                getAffiliateDAO().save(companyAffiliate);
                if (companyAffiliateInvite != null) {
                    getAffiliateDAO().save(companyAffiliateInvite);
                }
                companyAffAdded = true;

            }
        }

        boolean isCompanyAffActive = companyAffiliate != null ? companyAffiliate.getActive() : false;

        if (affInviteConverted) {
            //TODO: add a notification that aff invite converted

        }

        if (companyAffAdded) {
            //TODO: add a notification new aff added to company
        }

        if (affExistsForCompany) {
            //TODO: shoot email you are already an affiliate for company, so use existing login/pwd
        } else if (newAffAdded && companyAffAdded && isCompanyAffActive) {
            //TODO: send welcome email with login/pwd
        } else if (newAffAdded && companyAffAdded && !isCompanyAffActive) {
            //TODO: send auth pending email with login/pwd
            //TODO: add a notification for aff auth pending
        } else if (!newAffAdded && companyAffAdded && isCompanyAffActive) {
            //TODO: send welcome email  use existing login/pwd
        } else if (!newAffAdded && companyAffAdded && !isCompanyAffActive) {
            //TODO: send auth pending  use existing login/pwd
            //TODO: add a notification for aff auth pending
        }


        response.setAffiliate(affiliate);
        return response;

    }


    @Override
    public Affiliate getAffiliateByLogin(String login) {
        return getAffiliateDAO().getAffiliateByLogin(login);
    }


    @Override
    public void sendWelcomeEmail(Affiliate affiliate) {
        AffiliateContext affiliateContext = new AffiliateContext(affiliate);
        EmailEvent emailEvent = new EmailEvent(EmailTemplateService.EmailEventType.WelcomeAffiliate, affiliateContext);
        getMailService().sendAsyncMail(emailEvent);

    }

    @Override
    public void sendAffiliateWaitingApprovalEmail(Affiliate affiliate) {
        AffiliateContext affiliateContext = new AffiliateContext(affiliate);
        EmailEvent emailEvent = new EmailEvent(EmailTemplateService.EmailEventType.AffiliateWaitingApproval, affiliateContext);
        getMailService().sendAsyncMail(emailEvent);
    }


    @Override
    public Affiliate getAffiliate(Long affiliateId) {
        Affiliate affiliate = getAffiliateDAO().load(Affiliate.class, affiliateId);

        if (affiliate == null) {
            logger.error("No such affiliate found in system: " + affiliateId);
            throw new InvalidParameterException("INVALID_AFFILIATE");
        }
        return affiliate;
    }

    @Override
    public long affiliatesCount(String companyShortName) {
        return getAffiliateDAO().affiliatesCount(companyShortName);
    }


    @Override
    public Page searchAffiliate(String login, String email, String companyShortName, int pageNo, int perPage) {

        AffiliateQuery affiliateQuery = new AffiliateQuery();
        affiliateQuery.setCompanyShortName(companyShortName);
        affiliateQuery.setEmail(email);
        affiliateQuery.setLogin(login).setOrderByField("login").setPageNo(pageNo).setRows(perPage);
        return getSearchService().list(affiliateQuery);

    }


    /*@Override
    @Transactional
    public Affiliate saveNewAffiliate(Affiliate affiliate) throws CompositeValidationException {

        CompositeValidationException compositeValidationException = new CompositeValidationException();
        validateAffiliate(affiliate, compositeValidationException, null);
        if (!compositeValidationException.getValidationExceptions().isEmpty()) {
            logger.error("Error while adding affiliate ", compositeValidationException);
            throw compositeValidationException;
        }
        return affiliateDao.saveAffiliate(affiliate);
    }*/

    /*@Override
     @Transactional
     public Affiliate updateAffiliate(Affiliate affiliate) {
         return affiliateDao.saveAffiliate(affiliate);
     }*/


    /*@Transactional
     public void updateEntity(Object entity) {
         getAdminDAO().update(entity);
     }*/


    /*@Override
    @Transactional
    public CompanyAffiliate saveAffiliateCompany(Affiliate affiliate, String companyShortName) {
        CompanyAffiliate companyAffiliate = new CompanyAffiliate();
        companyAffiliate.setAffiliate(affiliate);
        companyAffiliate.setCompanyShortName(companyShortName);
        return affiliateDao.saveAffiliateCompany(companyAffiliate);

    }*/

    /*private void validateAffiliate(Affiliate affiliate, CompositeValidationException compositeValidationException, Object[] recaptchaParams) {

        if (!isEmailIdValid(affiliate.getEmail())) {
            compositeValidationException.getValidationExceptions().add(new ValidationException(ValidationConstants.INVALID_AFFILIATE_EMAIL, "not a valid email"));
        } else if (isAffiliateLoginTaken(affiliate.getLogin())) {
            compositeValidationException.getValidationExceptions().add(new ValidationException(ValidationConstants.AFFILIATE_LOGIN_EXISTS, "login has already been taken"));
        }

        if (recaptchaParams != null) {
            String remoteip = recaptchaParams.length >= 0 ? (String) recaptchaParams[0] : null;
            String recaptchaChallengeField = recaptchaParams.length > 0 ? (String) recaptchaParams[1] : null;
            String recaptchaResponsefield = recaptchaParams.length > 1 ? (String) recaptchaParams[2] : null;
            boolean validateCaptcha = true;

            if (remoteip == null || StringUtils.isEmpty(remoteip)) {
                compositeValidationException.getValidationExceptions().add(new ValidationException("remoteip", "User ip cannot be blank"));
                validateCaptcha = false;
            }
            if (recaptchaChallengeField == null || StringUtils.isEmpty(recaptchaChallengeField)) {
                compositeValidationException.getValidationExceptions().add(new ValidationException("recaptchaChallengeField", "reCaptcha challenge can not be left blank."));
                validateCaptcha = false;
            }
            if (recaptchaResponsefield == null || StringUtils.isEmpty(recaptchaResponsefield)) {
                compositeValidationException.getValidationExceptions().add(new ValidationException("recaptchaResponsefield", "reCaptcha response can not be left blank."));
                validateCaptcha = false;
            }
            *//*if (validateCaptcha) {
                         List<String> captchaResponse = getRecaptchaService().validateRecatchaResponse(remoteip, recaptchaChallengeField, recaptchaResponsefield);
                         String isResponseValid = captchaResponse.get(0);
                         boolean isValid = false;
                         if (isResponseValid != null && StringUtils.isNotEmpty(isResponseValid))
                           isValid = Boolean.parseBoolean(isResponseValid);

                         if (!isValid) {
                           String errorMsg = captchaResponse.get(1);
                           compositeValidationException.getValidationExceptions().add(
                               new ValidationException("recaptchaValidation", "reCaptcha response does not match, please try again."));
                         }

                       }*//*

        }
    }*/


    /*@Override
    @Transactional
    public Affiliate createAffiliate(AffiliateDTO affiliateDTO) throws CompositeValidationException {

        Affiliate affiliate = null;
        affiliate = affiliateDTO.extractAffiliate(affiliate);
        affiliate = saveNewAffiliate(affiliate);
        return affiliate;
    }*/

    public FeatureAPI getFeatureAPI() {
        return featureAPI;
    }


    public AffiliateDao getAffiliateDAO() {
        return affiliateDao;
    }

    public void setAffiliateDAO(AffiliateDao affiliateDao) {
        this.affiliateDao = affiliateDao;
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public AdminService getAdminService() {
        return adminService;
    }


    public CompanyAffiliateService getCompanyAffiliateService() {
        return companyAffiliateService;
    }
}
