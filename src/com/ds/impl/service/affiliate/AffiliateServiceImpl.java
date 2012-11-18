package com.ds.impl.service.affiliate;

import com.ds.api.AdminAPI;
import com.ds.api.CacheAPI;
import com.ds.api.FeatureAPI;
import com.ds.core.event.EmailEvent;
import com.ds.core.event.EventDispatcher;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.domain.user.UserSettings;
import com.ds.exception.CompositeValidationException;
import com.ds.exception.DSException;
import com.ds.exception.ValidationException;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.impl.service.admin.AdminServiceImpl;
import com.ds.impl.service.mail.AffiliateContext;
import com.ds.pact.dao.AdminDAO;
import com.ds.pact.dao.affiliate.AffiliateDAO;
import com.ds.pact.service.HttpService;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.admin.LoadPropertyService;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.mail.EmailTemplateService;
import com.ds.pact.service.mail.MailService;
import com.ds.search.impl.AffiliateQuery;
import com.ds.search.impl.AffiliateGroupQuery;
import com.ds.security.api.SecurityAPI;
import com.ds.web.action.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private SearchService searchService;

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
	public Affiliate saveAffiliate(Affiliate affiliate) {

		CompositeValidationException compositeValidationException = new CompositeValidationException();
		validateAffiliate(affiliate, compositeValidationException, null);
		if(!compositeValidationException.getValidationExceptions().isEmpty()){
			logger.error("Error while adding affiliate ", compositeValidationException);
			throw compositeValidationException;
		}
		return affiliateDAO.saveAffiliate(affiliate);
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

	/**
	 * @param login
	 * @param email
	 * @param companyShortName
	 * @param pageNo
	 * @param perPage
	 * @return
	 */
	@Override
	public Page searchAffiliate(String login, String email, String companyShortName, int pageNo, int perPage) {

		AffiliateQuery affiliateQuery = new AffiliateQuery();
		affiliateQuery.setCompanyShortName(companyShortName);
		affiliateQuery.setEmail(email);
		affiliateQuery.setLogin(login).setOrderByField("login").setPageNo(pageNo).setRows(perPage);
		return getSearchService().list(affiliateQuery);

		//return null;  //To change body of implemented methods use File | Settings | File Templates.
	}


	@Override
	public Page searchAffiliateGroup(String name, String companyShortName, int pageNo, int perPage) {

		AffiliateGroupQuery affiliateGroupQuery = new AffiliateGroupQuery();
		affiliateGroupQuery.setCompanyShortName(companyShortName).setName(name).setOrderByField("name").setPageNo(pageNo).setRows(perPage);
		return getSearchService().list(affiliateGroupQuery);
	}

	/**
	 * @param affiliate
	 * @param companyShortName
	 */
	@Override
	@Transactional
	public CompanyAffiliate saveAffiliateCompany(Affiliate affiliate, String companyShortName) {
		CompanyAffiliate companyAffiliate = new CompanyAffiliate();
		companyAffiliate.setAffiliate(affiliate);
		companyAffiliate.setCompanyShortName(companyShortName);
		return affiliateDAO.saveAffiliateCompany(companyAffiliate);

	}

	private void validateAffiliate(Affiliate affiliate, CompositeValidationException compositeValidationException, Object[] recaptchaParams) {

		if (!isEmailIdValid(affiliate.getEmail())) {
			compositeValidationException.getValidationExceptions().add(new ValidationException("email", "not a valid email"));
		} else if (isAffiliateLoginTaken(affiliate.getLogin())) {
			compositeValidationException.getValidationExceptions().add(new ValidationException("login", "login has already been taken"));
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

	@Override
	public boolean isEmailIdValid(String emailId) {
		Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher matcher = pattern.matcher(emailId);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAffiliateLoginTaken(String login) {
		return getAffiliateDAO().isAffiliateLoginTaken(login);
	}


	/**
	 * @param affiliate
	 */
	public void sendWelcomeEmail(Affiliate affiliate) {

		AffiliateContext affiliateContext = new AffiliateContext(affiliate);

		EmailEvent emailEvent = new EmailEvent(EmailTemplateService.EmailEventType.WelcomeAffiliate, affiliateContext);
		getMailService().sendAsyncMail(emailEvent);



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


	@Override
	public Affiliate getAffiliateByLogin(String login) {
		return getAffiliateDAO().getAffiliateByLogin(login);
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

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
}
