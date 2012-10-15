package com.ds.impl.service.admin;

import com.ds.api.AdminAPI;
import com.ds.api.CacheAPI;
import com.ds.api.FeatureAPI;
import com.ds.core.event.EmailEvent;
import com.ds.core.event.EventDispatcher;
import com.ds.core.event.UserLoginEmailConfirmationRequestEvent;
import com.ds.core.transaction.RequiresNewTemplate;
import com.ds.domain.company.Company;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.domain.user.UserLoginConfirmationRequest;
import com.ds.domain.user.UserSettings;
import com.ds.domain.visitor.VisitorInfo;
import com.ds.exception.CompositeValidationException;
import com.ds.exception.DSException;
import com.ds.exception.ValidationException;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.impl.service.mail.UserContext;
import com.ds.pact.dao.AdminDAO;
import com.ds.pact.service.HttpService;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.admin.LoadPropertyService;
import com.ds.pact.service.mail.EmailTemplateService;
import com.ds.pact.service.mail.MailService;
import com.ds.security.api.SecurityAPI;
import com.ds.utils.GeneralUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author adlakha.vaibhav
 */
@Service
public class AdminServiceImpl implements AdminService {

  private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

  @Autowired
  private MessageDigestPasswordEncoder messageDigestPasswordEncoder;
  @Autowired
  private AdminDAO adminDAO;
  @Autowired
  private HttpService httpService;
  @Autowired
  private MailService mailService;
  //private String activationLink;                                   // TODO : to be removed.
  @Autowired
  private SecurityAPI securityAPI;

  //      private PostService                  postService;
  // private ScheduleService scheduleService;
  @Autowired
  private LoadPropertyService loadPropertyService;
  //    private RPXService                   rpxService;
  //   private RecaptchaService             recaptchaService;

  @Autowired
  private AdminAPI adminAPI;
  @Autowired
  private CacheAPI cacheAPI;
  @Autowired
  private FeatureAPI featureAPI;

  private Map<String, Integer> defaultCompanyKarmaProfile;
  private Map<String, Integer> defaultBadges;
  private EventDispatcher eventDispatcher;

  @Override
  public void registerCompany(Company company) {
    CompositeValidationException compositeValidationException = new CompositeValidationException();

    validateCompany(company, compositeValidationException);

    company.setVerificationToken(String.valueOf(RandomUtils.nextInt()));
    // if no karma profile has not been specified we assign a default one.

    /* if (company.getKarmaProfile() == null) {
      company.setKarmaProfile(defaultCompanyKarmaProfile);
    }*/

    /*if (company.getBadges() == null) {
        company.setBadges(defaultBadges);
    }*/

    if (!compositeValidationException.getValidationExceptions().isEmpty()) {
      logger.error("Error registering company", compositeValidationException);
      throw compositeValidationException;
    }

    getAdminDAO().saveCompany(company);

  }

  @Override
  public void registerCompany(Company company, final User user, Object[] recaptchaParams) {
    CompositeValidationException compositeValidationException = new CompositeValidationException();

    validateCompany(company, compositeValidationException);
    validateUser(user, compositeValidationException, recaptchaParams);

    company.setVerificationToken(String.valueOf(RandomUtils.nextInt()));

    if (!compositeValidationException.getValidationExceptions().isEmpty()) {
      logger.error("Error registering company", compositeValidationException);
      throw compositeValidationException;
    }

    getAdminDAO().saveCompany(company);

    getFeatureAPI().grantCompanyAccessTo(company, "ENTERPRISE");

    user.setCompanyShortName(company.getShortName());
    addUser(user, new Role.RoleType[]{Role.RoleType.admin});
  }

  private void validateUser(User user, CompositeValidationException compositeValidationException, Object[] recaptchaParams) {

    if (!isUserIdValid(user.getEmail())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("email", "not a valid username"));
    } else if (isUserIdTaken(user.getEmail())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("email", "username has already been taken"));
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
      /*if (validateCaptcha) {
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

      }*/

    }
  }

  private void validateCompany(Company company, CompositeValidationException compositeValidationException) {

    if (isCompanyShortNameTaken(company.getShortName())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("shortName", "shortname has already been taken"));
    } else if (!isCompanyShortNameAndURLRelated(company.getShortName(), company.getUrl())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("shortName", "shortname does not look similar to url"));
    }

    if (!isCompanyURLWellFormed(company.getUrl())) {
      compositeValidationException.getValidationExceptions().add(new ValidationException("url", "should look like an url"));
    }

  }

  @Override
  public boolean isCompanyShortNameTaken(String shortName) {
    return getAdminDAO().isCompanyShortNameTaken(shortName);
  }

  @Override
  public boolean isCompanyURLWellFormed(String url) {
    Pattern pattern = Pattern.compile("^(http)://[^/]+\\.[a-z]+");
    Matcher matcher = pattern.matcher(url);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isCompanyShortNameAndURLRelated(String shortName, String url) {
    return url.contains(shortName);
  }

  @Override
  public boolean isUserIdTaken(String userEmailId) {
    return getAdminDAO().isUserIdTaken(userEmailId);
  }

  @Override
  public boolean isUserIdValid(String userEmailId) {
    Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
    Matcher matcher = pattern.matcher(userEmailId);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean verifyCompanyActivation(String companyShortName) {
    Company company = getCompany(companyShortName);

    if (StringUtils.isBlank(company.getVerificationToken())) {
      return false;
    } else {
      StringBuilder url = new StringBuilder();

      url.append(company.getUrl()).append("/userrules-").append(company.getVerificationToken()).append(".html");
      try {
        String fileContents = getHttpService().getFile(url.toString());
        boolean result = fileContents.contains(company.getVerificationToken());

        if (result) {
          company.setEnabled(true);
          getAdminDAO().saveOrUpdate(company);
        }
        return result;
      } catch (IOException e) {
        logger.error("IO Error while activating company", e);
        return false;
      }
    }
  }

  public void addUser(final User user, Role.RoleType[] roleRoleTypes) {
    CompositeValidationException compositeValidationException = new CompositeValidationException();

    validateUser(user, compositeValidationException, null);

    if (!compositeValidationException.getValidationExceptions().isEmpty()) {
      logger.error("Error while adding new user", compositeValidationException);
      throw compositeValidationException;
    }

    /*UserKarmaProfile karmaProfile = new UserKarmaProfile();
    karmaProfile.setUserName(user.getUsername());
    karmaProfile.setKarmaPoints(new Long(0));
    getAdminDAO().save(karmaProfile);*/

    UserSettings userSettings = new UserSettings();
    // userSettings.setCreatedDate(new Date());
    //userSettings.setCreatedBy(user.getUsername());
    userSettings.setUsername(user.getUsername());
    userSettings.setSendEmailOnAssignedPost(true);
    userSettings.setSendEmailOnPost(true);
    getAdminDAO().save(userSettings);

    //user.setKarmaProfileId(karmaProfile.getId());
    user.setUserSettingsId(userSettings.getId());

    ServiceLocatorFactory.getService(RequiresNewTemplate.class).executeInNewTransaction(new TransactionCallback() {

      @Override
      public Object doInTransaction(TransactionStatus status) {
        user.setPassword(getMessageDigestPasswordEncoder().encodePassword(user.getPassword(), user.getUsername()));

        getAdminDAO().save(user);
        return null;
      }
    });

    getSecurityAPI().grantRolesToUser(user, roleRoleTypes);

    UserLoginConfirmationRequest userLoginConfirmationRequest = new UserLoginConfirmationRequest();
    userLoginConfirmationRequest.setConfirmationKey(GeneralUtils.getRandomAlphaNumericString(20, 5));
    userLoginConfirmationRequest.setProviderName("userrules");
    userLoginConfirmationRequest.setName(user.getFullName());
    userLoginConfirmationRequest.setAuthInfoJson("NA");
    userLoginConfirmationRequest.setIdentifier(userLoginConfirmationRequest.getConfirmationKey());
    userLoginConfirmationRequest.setConfirmed(false);

    associateEmailToUserLoginConfirmationRequest(userLoginConfirmationRequest, user.getEmail(), false);

  }

  public void updateEntity(Object entity) {
    getAdminDAO().update(entity);
  }

  public User getUser(String userId) {
    User user = (User) getAdminDAO().load(User.class, userId);

    if (user == null) {
      logger.error("No such user found in system: " + userId);
      throw new InvalidParameterException("INVALID_USER");
    }
    return user;
  }

  @Override
  public Company getCompany(String companyShortName) {
    Company company = getAdminAPI().getCompany(companyShortName);

    if (company == null) {
      logger.error("No such company found in system: " + companyShortName);
      throw new InvalidParameterException("INVALID_COMPANY");
    }
    return company;
  }

  /**
   * @return the adminDAO
   */
  public AdminDAO getAdminDAO() {
    return adminDAO;
  }

  /**
   * @param adminDAO the adminDAO to set
   */
  public void setAdminDAO(AdminDAO adminDAO) {
    this.adminDAO = adminDAO;
  }

  public MailService getMailService() {
    return mailService;
  }

  public void setMailService(MailService mailService) {
    this.mailService = mailService;
  }

  /**
   * @return the httpService
   */
  public HttpService getHttpService() {
    return httpService;
  }

  /**
   * @param httpService the httpService to set
   */
  public void setHttpService(HttpService httpService) {
    this.httpService = httpService;
  }

  /**
   * The activation link that will be sent out to the user for activation user account.
   *
   * @return
   */
  /*public String getActivationLink() {
    return activationLink;
  }

  public void setActivationLink(String activationLink) {
    this.activationLink = activationLink;
  }*/

  /*@Override
  public void createForm(Form form) {
    getAdminDAO().createForm(form);
  }

  @Override
  public void createIssueTracker(IssueTrackerConfig issueTrackerConfig) {
    if (getAdminDAO().existsIssueTracker(issueTrackerConfig.getCompany().getName(), issueTrackerConfig.getName())) {
      throw new UserrulesException("ISSUE_TRACKER_ALREADY_EXISTS");
    }
    IssueTracker issueTrackerAPI = getIssueTrackerAPI(issueTrackerConfig);
    issueTrackerAPI.vaildateConfiguration();

    getAdminDAO().createIssueTracker(issueTrackerConfig);
    reconfigure(issueTrackerConfig);
  }

  @Override
  public IssueTrackerConfig loadIssueTrackerConfig(String issueTrackerConfigName) {
    return getAdminDAO().loadIssueTrackerConfig(issueTrackerConfigName);
  }*/

  /*@Override
  public void reconfigure(IssueTrackerConfig issueTrackerConfig) {
    IssueTracker issueTrackerAPI = getIssueTrackerAPI(issueTrackerConfig);
    issueTrackerAPI.configure();
  }

  private IssueTracker getIssueTrackerAPI(IssueTrackerConfig issueTrackerConfig) {
    IssueTracker issueTrackerAPI = null;

    if (issueTrackerConfig.getIssueTrackerType().equals(IssueTrackerConfig.Type.RedMine.toString())) {
      issueTrackerAPI = new RedMine(issueTrackerConfig);
    } else if (issueTrackerConfig.getIssueTrackerType().equals(IssueTrackerConfig.Type.Jira.toString())) {
      issueTrackerAPI = JiraIssueTrackerFactory.getInstance().getIssueTrackerByIssueTrackerConfig(issueTrackerConfig);
    } else if (issueTrackerConfig.getIssueTrackerType().equals(IssueTrackerConfig.Type.BugZilla.toString())) {
      issueTrackerAPI = BugzillaIssueTrackerFactory.getInstance().getBugzillaClient(issueTrackerConfig);
    }

    if (issueTrackerAPI == null) {
      throw new InvalidParameterException("UNKNOWN_ISSUETRACKER_TYPE");
    }
    return issueTrackerAPI;
  }*/

  /*@Override
  public void saveOrUpdateForm(Form form) {
    getAdminDAO().saveOrUpdateForm(form);
  }

  @Override
  public void updateIssueTracker(IssueTrackerConfig issueTrackerConfig) {
    getAdminDAO().updateIssueTracker(issueTrackerConfig);
  }

  @Override
  public IssueTrackerConfig loadIssueTrackerConfig(long issueTrackerId) {
    return getAdminDAO().loadIssueTrackerConfig(issueTrackerId);
  }

  @Override
  public IssueTrackerConfig loadIssueTrackerConfig(String companyShortName, long issueTrackerId) {
    return getAdminDAO().loadIssueTrackerConfig(companyShortName, issueTrackerId);
  }*/

  @Override
  public List<Company> getAllCompanies() {
    return getAdminDAO().getAll(Company.class);
  }

  /**
   * @return the securityAPI
   */
  public SecurityAPI getSecurityAPI() {
    return securityAPI;
  }

  /**
   * @param securityAPI the securityAPI to set
   */
  public void setSecurityAPI(SecurityAPI securityAPI) {
    this.securityAPI = securityAPI;
  }

  /*@Override
  public List<IssueTrackerConfig> getIssueTrackers(String companyShortName) {
    return getAdminDAO().getIssueTrackers(companyShortName);
  }
*/
  @Override
  public void registerEmployee(String companyShortName, User user) {
    CompositeValidationException compositeValidationException = new CompositeValidationException();

    // validateUser(user, compositeValidationException);

    if (!compositeValidationException.getValidationExceptions().isEmpty()) {
      logger.error("error registering employee", compositeValidationException);
      throw compositeValidationException;
    }

    user.setPassword(getMessageDigestPasswordEncoder().encodePassword(user.getPassword(), user.getUsername()));

    user.setCompanyShortName(companyShortName);
    getAdminDAO().save(user);
    user.setEnabled(true);// temproray enabling it
    getSecurityAPI().grantRolesToUser(user, Role.RoleType.admin);
  }

  @Override
  public User findOrCreateUser(User user) {

    if (!getAdminDAO().existsUser(user.getUsername())) {
      getAdminDAO().saveOrUpdate(user);
    }

    return user;
  }

  /*@Override
  public Form findForm(IssueTrackerConfig issueTrackerConfig, String formName) {
    return getAdminDAO().findForm(issueTrackerConfig, formName);
  }

  @Override
  public Form getIssueCreateForm(IssueTrackerConfig issueTrackerConfig, Post post) {
    IssueTracker issueTrackerAPI = getIssueTrackerAPI(issueTrackerConfig);
    return issueTrackerAPI.getCreateIssueForm(post);
  }

  @Override
  public void createIssue(IssueTrackerConfig issueTrackerConfig, long postId, Map<String, String> data) {
    IssueTracker issueTrackerAPI = getIssueTrackerAPI(issueTrackerConfig);
    Post post = getPostService().loadPost(postId);

    IssueDetail externalIssueDetail = issueTrackerAPI.createIssue(data);

    post.setExternalIssueId(externalIssueDetail.getIssueId());
    post.setExternalIssueStatus(externalIssueDetail.getIssueStatus());
    post.setExternalIssueUrl(externalIssueDetail.getIssueUrl());
    post.setIssueTrackerConfigId(issueTrackerConfig.getId());

    getPostService().updatePost(post);

    getEventDispatcher().dispatchEvent(new IssueCreatedForPostEvent(post, issueTrackerConfig, externalIssueDetail.getIssueId(), externalIssueDetail.getIssueUrl()));
  }*/

  /**
   * @return the postService
   */
  /*public PostService getPostService() {
    return postService;
  }

  *//**
   * @param postService the postService to set
   *//*
  public void setPostService(PostService postService) {
    this.postService = postService;
  }*/

  /**
   * @return the scheduleService
   */
  /* public ScheduleService getScheduleService() {
    return scheduleService;
  }

  *//**
   * @param scheduleService the scheduleService to set
   *//*
  public void setScheduleService(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }*/

  /*@Override
  public Map<String, String> checkStatus(List<String> issueIds, IssueTrackerConfig issueTrackerConfig) {
    return getIssueTrackerAPI(issueTrackerConfig).checkStatus(issueIds);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    initIndexUpdateJob();
  }

  private void initIndexUpdateJob() {
    logger.info("Scheduling Check Issue Status Job");

    String cronExpr = (String) getLoadPropertyService().getProperty(Properties.SOLR_INDEX_UPDATE_CRON_EXPR.getPropertyRef());

    Schedule schedule = getScheduleService().load(IssueStatusCheckJob.class.getName());

    if (schedule == null) {
      schedule = new Schedule();
      schedule.setName(IssueStatusCheckJob.class.getName());
      schedule.setUserid(new String("admin"));
      schedule.setEnabled(true);
      schedule.setJobClass(IssueStatusCheckJob.class.getName());
      schedule.setDescription("Issue Status Check Job");

      schedule.setCronString(cronExpr);
      schedule.setScheduleType("Cron");
      schedule.setStartDate(new Date());
      schedule.setCreatedDate(new Date());
      schedule.setMisFireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
    }

    schedule.setUpdatedBy("admin");
    schedule.setUpdatedDate(new Date());

    getScheduleService().saveOrUpdate(schedule);

  }*/

  /**
   * @return the loadPropertyService
   */
  public LoadPropertyService getLoadPropertyService() {
    return loadPropertyService;
  }

  /**
   * @param loadPropertyService the loadPropertyService to set
   */
  public void setLoadPropertyService(LoadPropertyService loadPropertyService) {
    this.loadPropertyService = loadPropertyService;
  }

  /**
   * @return the defaultCompanyKarmaProfile
   */
  public Map<String, Integer> getDefaultCompanyKarmaProfile() {
    return defaultCompanyKarmaProfile;
  }

  /**
   * @param defaultCompanyKarmaProfile the defaultCompanyKarmaProfile to set
   */
  public void setDefaultCompanyKarmaProfile(Map<String, Integer> defaultCompanyKarmaProfile) {
    this.defaultCompanyKarmaProfile = defaultCompanyKarmaProfile;
  }

  @Override
  public void saveUserLoginConfirmationRequest(UserLoginConfirmationRequest userLoginConfirmationRequest) {
    getAdminDAO().saveUserLoginConfirmationRequest(userLoginConfirmationRequest);
  }

  @Override
  public UserLoginConfirmationRequest associateEmailToUserLoginConfirmationRequest(final UserLoginConfirmationRequest userLoginConfirmationRequest, final String userEmail,
                                                                                   boolean isThirdPartyConfirmation) {

    ServiceLocatorFactory.getService(RequiresNewTemplate.class).executeInNewTransaction(new TransactionCallback() {

      @Override
      public Object doInTransaction(TransactionStatus status) {
        userLoginConfirmationRequest.setVerifiedEmail(userEmail);
        getAdminDAO().saveOrUpdate(userLoginConfirmationRequest);
        return null;
      }

    });

    if (isThirdPartyConfirmation) {
      getEventDispatcher().dispatchEvent(new UserLoginEmailConfirmationRequestEvent(userLoginConfirmationRequest, EmailTemplateService.EmailEventType.UserLoggedInThirdPartyEmailConfirmation));
    } else {
      getEventDispatcher().dispatchEvent(new UserLoginEmailConfirmationRequestEvent(userLoginConfirmationRequest, EmailTemplateService.EmailEventType.UserRegistrationConfirmation));
    }

    return userLoginConfirmationRequest;
  }

  @Override
  public VisitorInfo getUserInfo(String userName, String postId) {
    List result = getAdminDAO().findByQuery("from VisitorInfo where userName = ? and entityId = ? ", new Object[]{userName, postId});

    if (result.size() > 0)
      return (VisitorInfo) result.get(0);
    else
      return null;
  }

  @Override
  public UserLoginConfirmationRequest loadUserLoginConfirmationRequest(long userLoginConfirmationRequestId) {
    return getAdminDAO().loadUserLoginConfirmationRequest(userLoginConfirmationRequestId);
  }

  /**
   * @return the eventDispatcher
   */
  public EventDispatcher getEventDispatcher() {
    if (this.eventDispatcher == null) {
      this.eventDispatcher = ServiceLocatorFactory.getService(EventDispatcher.class);
    }
    return eventDispatcher;
  }

  /**
   * @param eventDispatcher the eventDispatcher to set
   */
  public void setEventDispatcher(EventDispatcher eventDispatcher) {
    this.eventDispatcher = eventDispatcher;
  }

  /*@Override
  public void configureGoogleAnalytics(Company company, GAAuthInfo gaAuthInfo) {
    // if there is existing GAAuthInfo lets get rid of it
    if (company.getGaAuthInfo() != null) {
      getAdminDAO().delete(company.getGaAuthInfo());
    }
    company.setGaAuthInfo(gaAuthInfo);
    getAdminDAO().update(company);
  }*/

  @Override
  public void resetEmployeePassword(final String userEmail) {
    final String generatedPassword = GeneralUtils.getRandomAlphaNumericString(8, 4);
    ServiceLocatorFactory.getService(RequiresNewTemplate.class).executeInNewTransaction(new TransactionCallback() {

      @Override
      public Object doInTransaction(TransactionStatus status) {
        final User user = getAdminDAO().getUser(userEmail);

        // user does not exist
        if (user == null) {
          logger.error("No such user found in system: " + userEmail);
          throw new InvalidParameterException("INVALID_USER");
        }

        user.setPassword(getMessageDigestPasswordEncoder().encodePassword(generatedPassword, user.getUsername()));
        getAdminDAO().update(user);

        return null;
      }
    });

    UserContext userContext = new UserContext(getAdminDAO().getUser(userEmail), generatedPassword);

    EmailEvent emailEvent = new EmailEvent(EmailTemplateService.EmailEventType.UserPasswordResetConfirmation, userContext);
    getMailService().sendAsyncMail(emailEvent);

  }

  @Override
  public boolean confirmUserLoginConfirmationRequest(HttpServletRequest request, HttpServletResponse response, long userLoginConfirmationRequestId, String confirmationKey) {
    UserLoginConfirmationRequest userLoginConfirmationRequest = loadUserLoginConfirmationRequest(userLoginConfirmationRequestId);

    if (!userLoginConfirmationRequest.isConfirmed() && userLoginConfirmationRequest.getConfirmationKey().equalsIgnoreCase(confirmationKey)
        && !("userrules".equalsIgnoreCase(userLoginConfirmationRequest.getProviderName()))) {
      /*userLoginConfirmationRequest.setConfirmed(true);
      saveUserLoginConfirmationRequest(userLoginConfirmationRequest);
      getRpxService().confirmUserEmail(request, response, userLoginConfirmationRequest);*/
      return true;
    } else if (!userLoginConfirmationRequest.isConfirmed() && userLoginConfirmationRequest.getConfirmationKey().equalsIgnoreCase(confirmationKey)
        && "userrules".equalsIgnoreCase(userLoginConfirmationRequest.getProviderName())) {
      userLoginConfirmationRequest.setConfirmed(true);
      User user = getUser(userLoginConfirmationRequest.getVerifiedEmail());
      user.setEnabled(true);

      getAdminDAO().save(user);
      saveUserLoginConfirmationRequest(userLoginConfirmationRequest);
    }
    return false;
  }

  /**
   * @return the rpxService
   */
  /*public RPXService getRpxService() {
    if (this.rpxService == null) {
      this.rpxService = ServiceLocatorFactory.getService(RPXService.class);
    }
    return rpxService;
  }*/


  /*public void setRpxService(RPXService rpxService) {
    this.rpxService = rpxService;
  }

  @Override
  public List<FormFieldOption> getFormFieldOptions(IssueTrackerConfig issueTrackerConfig, String formName, String fieldName) {
    return getAdminDAO().getFormFieldOptions(issueTrackerConfig, formName, fieldName);
  }*/
  @Override
  public void deleteEmployee(String username) {
    User user = getAdminAPI().getUser(username);
    // user does not exist
    if (user == null) {
      logger.error("No such user found in system: " + username);
      throw new InvalidParameterException("INVALID_USER");
    }

    getAdminDAO().delete(user);

    getCacheAPI().remove(CacheAPI.CacheConfig.USER_CACHE, user.getUsername());

  }

  @Override
  public boolean changePassword(String emailId, String oldPassword, String newPassword) throws DSException {
    User user = getUser(emailId);

    // we need to encrypt and then company
    if (!user.getPassword().equals(getMessageDigestPasswordEncoder().encodePassword(oldPassword, user.getUsername())))

    {
      throw new DSException("INVALID_OLD_PASSWORD");
    }

    user.setPassword(newPassword);
    user.setPassword(getMessageDigestPasswordEncoder().encodePassword(user.getPassword(), user.getUsername()));

    updateEntity(user);

    return Boolean.TRUE;
  }

  @Override
  public UserSettings getUserSettings(String username) {
    return getAdminDAO().getUserSettings(username);
  }

  /**
   * @return the adminAPI
   */
  public AdminAPI getAdminAPI() {
    return adminAPI;
  }

  /**
   * @param adminAPI the adminAPI to set
   */
  public void setAdminAPI(AdminAPI adminAPI) {
    this.adminAPI = adminAPI;
  }

  @Override
  public long employeesCount(String companyShortName) {
    return getAdminDAO().employeesCount(companyShortName);
  }

  @Override
  public List<User> findEmployees(String companyShortName, int start, int rows, String sortBy, String sortHow) {
    return getAdminAPI().findEmployees(companyShortName, start, rows, sortBy, sortHow);
  }

  @Override
  public List<User> getAllEmployees(String companyShortName) {
    return getAdminDAO().getAllEmployees(companyShortName);
  }

  @Override
  public void updateUser(User user) {
    getAdminDAO().update(user);
    getCacheAPI().remove(CacheAPI.CacheConfig.USER_CACHE, user.getUsername());
  }

  /*@Override
  public Badge getBadgeForCompany(String badgeName, String companyShortName) {
    return getAdminDAO().getBadgeForCompany(badgeName, companyShortName);
  }*/

  /**
   * @return the cacheAPI
   */
  public CacheAPI getCacheAPI() {
    return cacheAPI;
  }

  /**
   * @param cacheAPI the cacheAPI to set
   */
  public void setCacheAPI(CacheAPI cacheAPI) {
    this.cacheAPI = cacheAPI;
  }

   /**//**
   * @return the recaptchaService
   *//*
  public RecaptchaService getRecaptchaService() {
    return recaptchaService;
  }

  *//**
   * @param recaptchaService the recaptchaService to set
   *//*
  public void setRecaptchaService(RecaptchaService recaptchaService) {
    this.recaptchaService = recaptchaService;
  }*/

  /**
   * @return the messageDigestPasswordEncoder
   */
  public MessageDigestPasswordEncoder getMessageDigestPasswordEncoder() {
    return messageDigestPasswordEncoder;
  }

  /**
   * @param messageDigestPasswordEncoder the messageDigestPasswordEncoder to set
   */
  public void setMessageDigestPasswordEncoder(MessageDigestPasswordEncoder messageDigestPasswordEncoder) {
    this.messageDigestPasswordEncoder = messageDigestPasswordEncoder;
  }

  @Override
  public String getEncryptedPassword(String username, String password) {

    return getMessageDigestPasswordEncoder().encodePassword(password, username);
  }

  /**
   * @return the featureAPI
   */
  public FeatureAPI getFeatureAPI() {
    return featureAPI;
  }

  /**
   * @param featureAPI the featureAPI to set
   */
  public void setFeatureAPI(FeatureAPI featureAPI) {
    this.featureAPI = featureAPI;
  }

  /**
   * @return the logger
   */
  public Logger getLogger() {
    return logger;
  }

   /**//**
   * @param logger the logger to set
   *//*
  public void setLogger(Logger logger) {
    this.logger = logger;
  }

  *//**
   * @return the defaultBadges
   *//*
  public Map<String, Integer> getDefaultBadges() {
    return defaultBadges;
  }

  *//**
   * @param defaultBadges the defaultBadges to set
   *//*
  public void setDefaultBadges(Map<String, Integer> defaultBadges) {
    this.defaultBadges = defaultBadges;
  }*/

  /*@Override
  public List<Company> getAllVendors(int start, int rows) {
    return getAdminDAO().getAllVendors(start, rows);
  }

  @Override
  public int getNoOfVendors() {
    return getAdminDAO().getNoOfVendors();
  }*/

}
