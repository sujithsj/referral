package com.ds.impl.service.affiliate;

import com.ds.api.FeatureAPI;
import com.ds.constants.FeatureType;
import com.ds.constants.EnumNotificationType;
import com.ds.core.event.EmailEvent;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateInvite;
import com.ds.domain.company.Company;
import com.ds.dto.affiliate.AffiliateDTO;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.exception.FeatureNotAccessibleException;
import com.ds.impl.service.mail.AffiliateContext;
import com.ds.impl.service.mail.CompanyAffiliateInvEmailContext;
import com.ds.pact.dao.affiliate.CompanyAffiliateDao;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.mail.EmailTemplateService;
import com.ds.pact.service.mail.MailService;
import com.ds.pact.service.notification.NotificationService;
import com.ds.search.impl.CompanyAffiliateInviteQuery;
import com.ds.search.impl.CompanyAffiliateQuery;
import com.ds.web.action.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * User: Rahul
 * Date: Nov 18, 2012
 * Time: 3:56:37 PM
 */
@Service
public class CompanyAffiliateServiceImpl implements CompanyAffiliateService {


	private Logger logger = LoggerFactory.getLogger(CompanyAffiliateServiceImpl.class);

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;
	@Autowired
	private CompanyAffiliateDao companyAffiliateDao;
	@Autowired
	private MailService mailService;
	@Autowired
	private SearchService searchService;
	@Autowired
	private AffiliateService affiliateService;
	@Autowired
	private FeatureAPI featureAPI;
	@Autowired
	private AdminService adminService;
	@Autowired
	private NotificationService notificationService;

	@Override
	@Transactional
	public CompanyAffiliate saveCompanyAffiliate(CompanyAffiliate companyAffiliate) {
		return getCompanyAffiliateDao().saveCompanyAffiliate(companyAffiliate);
	}

	public CompanyAffiliate getCompanyAffiliate(Long companyAffiliateId) {
		CompanyAffiliate companyAffiliate = getCompanyAffiliateDao().load(CompanyAffiliate.class, companyAffiliateId);

		if (companyAffiliate == null) {
			logger.error("No such affiliate found in system: " + companyAffiliateId);
			throw new InvalidParameterException("INVALID_AFFILIATE");
		}
		return companyAffiliate;
	}

	/**
	 * @param login            Login
	 * @param email            Email
	 * @param companyShortName CompanyShortName
	 * @param pageNo           PageNumber
	 * @param perPage          PerPage
	 * @return Page
	 */
	@Override
	public Page searchCompanyAffiliate(String login, String email, String companyShortName, int pageNo, int perPage) {

		CompanyAffiliateQuery companyAffiliateQuery = new CompanyAffiliateQuery();
		companyAffiliateQuery.setCompanyShortName(companyShortName);
		companyAffiliateQuery.setEmail(email);
		companyAffiliateQuery.setLogin(login).setOrderByField("login").setPageNo(pageNo).setRows(perPage);
		return getSearchService().list(companyAffiliateQuery);

		//return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * @param affiliate        Affiliate
	 * @param companyShortName Company Short Name
	 */
	@Override
	@Transactional
	public CompanyAffiliate createCompanyAffiliate(Affiliate affiliate, String companyShortName) {
		Company company = getAdminService().getCompany(companyShortName);
		CompanyAffiliate companyAffiliate = new CompanyAffiliate();
		CompanyAffiliateInvite companyAffiliateInvite;
		companyAffiliate.setAffiliate(affiliate);
		companyAffiliate.setCompanyShortName(companyShortName);


		companyAffiliateInvite = getCompanayAffiliateInvite(companyShortName, affiliate.getLogin());
		if (companyAffiliateInvite != null) {
			companyAffiliateInvite.setConverted(true);
			companyAffiliateInvite = saveCompanyAffiliateInvite(companyAffiliateInvite);
			companyAffiliate.setActive(true);
		} else {
			companyAffiliate.setActive(false);
		}
		try {
			if (companyAffiliate.getActive()) {
				getFeatureAPI().doesCompanyHaveAccessTo(company, FeatureType.AFFILIATE_COUNT, getActiveCompanyAffiliateCount(companyShortName) + 1);
			}
		} catch (FeatureNotAccessibleException fnae) {
			System.out.println(fnae.getI18nMessage().getMessageParams().toString());
			companyAffiliate.setActive(false);
		}
		companyAffiliate = getCompanyAffiliateDao().saveCompanyAffiliate(companyAffiliate);
		if (companyAffiliate.getActive()) {
			affiliateService.sendWelcomeEmail(affiliate);
			//send affiliate signed up email to company

		} else {
			affiliateService.sendAffiliateWaitingApprovalEmail(affiliate);
			notificationService.createCompanyNotification(companyShortName, EnumNotificationType.COMPANY_AFFILIATE_APPROVAL_PENDING);
			//todo : add approval pending email to company users (need to decide whom to send emails)
			//send approval pending email to both affiliate and company
		}
		return companyAffiliate;

	}

	/**
	 * @param affiliate Affiliate
	 */
	public void sendWelcomeEmail(Affiliate affiliate) {

		AffiliateContext affiliateContext = new AffiliateContext(affiliate);

		EmailEvent emailEvent = new EmailEvent(EmailTemplateService.EmailEventType.WelcomeAffiliate, affiliateContext);
		getMailService().sendAsyncMail(emailEvent);


	}

	/**
	 * @param companyAffiliateId CompanyAffiliateId
	 * @param companyShortName   CompanyShortName
	 * @return List of Company Affiliates
	 */
	@Override
	public List<CompanyAffiliate> getCompanyAffiliatesExcludingSelf(Long companyAffiliateId, String companyShortName) {
		CompanyAffiliateQuery companyAffiliateQuery = new CompanyAffiliateQuery();
		companyAffiliateQuery.setCompanyShortName(companyShortName);
		companyAffiliateQuery.setExcludingSelfId(companyAffiliateId);
		return getSearchService().executeSearch(companyAffiliateQuery);

	}

	@Override
	public List<CompanyAffiliate> getAllCompanyAffiliates(String companyShortName) {
		CompanyAffiliateQuery companyAffiliateQuery = new CompanyAffiliateQuery();
		companyAffiliateQuery.setCompanyShortName(companyShortName);
		return getSearchService().executeSearch(companyAffiliateQuery);
	}

	@Override
	public List<CompanyAffiliate> getActiveCompanyAffiliates(String companyShortName) {
		CompanyAffiliateQuery companyAffiliateQuery = new CompanyAffiliateQuery();
		companyAffiliateQuery.setCompanyShortName(companyShortName);
		companyAffiliateQuery.setActive(true);
		return getSearchService().executeSearch(companyAffiliateQuery);
	}

	@Override
	public long getActiveCompanyAffiliateCount(String companyShortName) {
		List<CompanyAffiliate> companyAffiliateList = getActiveCompanyAffiliates(companyShortName);
		return companyAffiliateList == null ? 0 : companyAffiliateList.size();
	}


	@Transactional
	@Override
	public CompanyAffiliate createOrUpdateCompanyAffiliate(CompanyAffiliateDTO companyAffiliateDTO, AffiliateDTO affiliateDTO, String companyShortName) {
		if (companyAffiliateDTO == null) {
			companyAffiliateDTO = new CompanyAffiliateDTO();
		}
		Long parentAffiliateId = companyAffiliateDTO.getParentCompanyAffiliateId();
		CompanyAffiliate parentCompanyAffiliate = null;
		if (parentAffiliateId != null) {
			parentCompanyAffiliate = getCompanyAffiliate(parentAffiliateId);
		}
		Affiliate affiliate = getAffiliateService().getAffiliateByLogin(affiliateDTO.getLogin());
		//affiliate already exists setting the Id and would update the first name last name as per the newly filled values
		if (affiliate != null) {
			affiliateDTO.setAffiliateId(affiliate.getId());
		}
		affiliate = affiliateDTO.extractAffiliate(affiliate);
		if (affiliate.getId() == null) {
			affiliate = affiliateService.saveNewAffiliate(affiliate);
		} else {
			affiliate = affiliateService.updateAffiliate(affiliate);
		}

		CompanyAffiliate companyAffiliate = null;
		companyAffiliate = companyAffiliateDTO.extractCompanyAffiliate(companyAffiliate);
		if (parentCompanyAffiliate != null) {
			companyAffiliate.setParentCompanyAffiliate(parentCompanyAffiliate);
		}
		if (companyShortName != null) {
			companyAffiliate.setCompanyShortName(companyShortName);
		}
		if (affiliate != null) {
			companyAffiliate.setAffiliate(affiliate);
		}
		companyAffiliate = saveCompanyAffiliate(companyAffiliate);
		return companyAffiliate;
	}

	@Override
	public Page searchCompanyAffiliatePendingInvites(String companyShortName, int pageNo, int perPage) {
		CompanyAffiliateInviteQuery companyAffiliateInviteQuery = new CompanyAffiliateInviteQuery();
		companyAffiliateInviteQuery.setCompanyShortName(companyShortName);
		companyAffiliateInviteQuery.setConverted(false);
		companyAffiliateInviteQuery.setDeleted(false);
		companyAffiliateInviteQuery.setOrderByField("id").setPageNo(pageNo).setRows(perPage);
		return getSearchService().list(companyAffiliateInviteQuery);
	}

	@Transactional
	@Override
	public CompanyAffiliateInvite addCompanyAffiliateInvite(String companyShortName, String affiliateEmail) {
		CompanyAffiliateInvite companyAffiliateInvite = new CompanyAffiliateInvite();
		companyAffiliateInvite.setCompanyShortName(companyShortName);
		companyAffiliateInvite.setAffiliateEmail(affiliateEmail);
		return getCompanyAffiliateDao().addCompanyAffiliateInvite(companyAffiliateInvite);
	}

	public CompanyAffiliateInvite getCompanayAffiliateInvite(String companyShortName, String affiliateEmail) {
		CompanyAffiliateInviteQuery companyAffiliateInviteQuery = new CompanyAffiliateInviteQuery();
		companyAffiliateInviteQuery.setCompanyShortName(companyShortName);
		companyAffiliateInviteQuery.setAffiliateEmail(affiliateEmail);
		List<CompanyAffiliateInvite> companyAffiliateInviteList = getSearchService().executeSearch(companyAffiliateInviteQuery);
		if (companyAffiliateInviteList != null && companyAffiliateInviteList.size() == 1) {
			return companyAffiliateInviteList.get(0);
		}
		return null;
	}

	public void sendCompanyAffiliateInvitationEmail(CompanyAffiliateInvite companyAffiliateInvite) {

		CompanyAffiliateInvEmailContext companyAffiliateInvEmailContext = new CompanyAffiliateInvEmailContext(companyAffiliateInvite);

		EmailEvent emailEvent = new EmailEvent(EmailTemplateService.EmailEventType.AffiliateInvitationEmail, companyAffiliateInvEmailContext);
		getMailService().sendAsyncMail(emailEvent);

	}

	@Transactional
	@Override
	public CompanyAffiliateInvite deleteCompanyAffiliateInvite(CompanyAffiliateInvite companyAffiliateInvite) {
		companyAffiliateInvite.setDeleted(true);
		return getCompanyAffiliateDao().addCompanyAffiliateInvite(companyAffiliateInvite);
	}

	public CompanyAffiliateInvite getCompanyAffiliateInvite(Long companyAffiliateInviteId) {
		CompanyAffiliateInvite companyAffiliateInvite = (CompanyAffiliateInvite) getCompanyAffiliateDao().load(CompanyAffiliateInvite.class, companyAffiliateInviteId);
		if (companyAffiliateInvite == null) {
			logger.error("No such invitation found in system: " + companyAffiliateInviteId);
			throw new InvalidParameterException("INVALID_COMPANY_AFFILIATE_INVITE");
		}
		return companyAffiliateInvite;
	}

	@Override
	@Transactional
	public CompanyAffiliateInvite saveCompanyAffiliateInvite(CompanyAffiliateInvite companyAffiliateInvite) {
		return getCompanyAffiliateDao().saveCompanyAffiliateInvite(companyAffiliateInvite);
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public AffiliateService getAffiliateService() {
		return affiliateService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public FeatureAPI getFeatureAPI() {
		return featureAPI;
	}

	public void setFeatureAPI(FeatureAPI featureAPI) {
		this.featureAPI = featureAPI;
	}

	public CompanyAffiliateDao getCompanyAffiliateDao() {
		return companyAffiliateDao;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public MessageDigestPasswordEncoder getMessageDigestPasswordEncoder() {
		return messageDigestPasswordEncoder;
	}

}
