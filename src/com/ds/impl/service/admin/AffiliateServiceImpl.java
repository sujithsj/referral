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
import com.ds.domain.affiliate.Affiliate;
import com.ds.exception.CompositeValidationException;
import com.ds.exception.DSException;
import com.ds.exception.ValidationException;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.impl.service.mail.UserContext;
import com.ds.pact.dao.AdminDAO;
import com.ds.pact.dao.affiliate.AffiliateDAO;
import com.ds.pact.service.HttpService;
import com.ds.pact.service.admin.AffiliateService;
import com.ds.pact.service.admin.LoadPropertyService;
import com.ds.pact.service.mail.EmailTemplateService;
import com.ds.pact.service.mail.MailService;
import com.ds.security.api.SecurityAPI;
import com.ds.utils.GeneralUtils;
import com.ds.web.action.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
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


	private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;
	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private AffiliateDAO affiliateDAO;
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
	@Transactional
	public Affiliate saveAffiliate(Affiliate affiliate){
		return affiliateDAO.saveAffiliate(affiliate);
	}
	@Override
	@Transactional
	public void registerCompany(Company company) {
		CompositeValidationException compositeValidationException = new CompositeValidationException();

		validateCompany(company, compositeValidationException);

		company.setVerificationToken(String.valueOf(RandomUtils.nextInt()));

		if (!compositeValidationException.getValidationExceptions().isEmpty()) {
			logger.error("Error registering company", compositeValidationException);
			throw compositeValidationException;
		}

		getAdminDAO().saveCompany(company);

	}

	@Override
	@Transactional
	public void registerCompany(Company company, final User user, Object[] recaptchaParams) {
		CompositeValidationException compositeValidationException = new CompositeValidationException();

		validateCompany(company, compositeValidationException);
		validateUser(user, compositeValidationException, recaptchaParams);

		company.setVerificationToken(String.valueOf(RandomUtils.nextInt()));

		if (!compositeValidationException.getValidationExceptions().isEmpty()) {
			logger.error("Error registering company", compositeValidationException);
			throw compositeValidationException;
		}

		company = getAdminDAO().saveCompany(company);

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

	@Transactional
	public void addUser(User user, Role.RoleType[] roleRoleTypes) {
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
		userSettings.setSendEmailOnAddAffiliate(true);
		userSettings.setSendEmailOnGoalConversion(true);
		userSettings.setSendEmailOnJoinAffiliate(true);
		userSettings.setSendEmailOnPayout(true);
		userSettings.setSendEmailOnTerminateAffiliate(true);
		userSettings = (UserSettings) getAdminDAO().save(userSettings);

		//user.setKarmaProfileId(karmaProfile.getId());
		user.setUserSettingsId(userSettings.getId());

		user.setPassword(getMessageDigestPasswordEncoder().encodePassword(user.getPassword(), user.getUsername()));

		user = (User) getAdminDAO().save(user);


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

	@Transactional
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

		public Affiliate getAffiliate(Long affiliateId) {
		Affiliate affiliate = (Affiliate) getAffiliateDAO().load(Affiliate.class, affiliateId);

		if (affiliate == null) {
			logger.error("No such affiliate found in system: " + affiliateId);
			throw new InvalidParameterException("INVALID_AFFILIATE");
		}
		return affiliate;
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
	public long affiliatesCount(String companyShortName) {
		return getAffiliateDAO().affiliatesCount(companyShortName);
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
	@Transactional
	public void updateUser(User user) {
		getAdminDAO().update(user);
		getCacheAPI().remove(CacheAPI.CacheConfig.USER_CACHE, user.getUsername());
	}

	/**
	 *
	 * @param userName
	 * @param email
	 * @param companyShortName
	 * @param pageNo
	 * @param perPage
	 * @return
	 */
	@Override
	public Page searchAffiliate(String userName, String email, String companyShortName, int pageNo, int perPage) {

		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

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

	public AffiliateDAO getAffiliateDAO() {
		return affiliateDAO;
	}

	public void setAffiliateDAO(AffiliateDAO affiliateDAO) {
		this.affiliateDAO = affiliateDAO;
	}
}
