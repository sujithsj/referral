package com.ds.impl.service.affiliate;

import com.ds.core.event.EmailEvent;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateGroup;
import com.ds.dto.affiliate.AffiliateDTO;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.impl.service.mail.AffiliateContext;
import com.ds.pact.dao.affiliate.CompanyAffiliateDao;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.mail.EmailTemplateService;
import com.ds.pact.service.mail.MailService;
import com.ds.search.impl.AffiliateGroupQuery;
import com.ds.search.impl.CompanyAffiliateQuery;
import com.ds.search.impl.CompanyAffiliateGroupQuery;
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
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 18, 2012
 * Time: 3:56:37 PM
 * To change this template use File | Settings | File Templates.
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


	@Override
	@Transactional
	public CompanyAffiliate saveCompanyAffiliate(CompanyAffiliate companyAffiliate) {
		return getCompanyAffiliateDAO().saveCompanyAffiliate(companyAffiliate);
	}

	public CompanyAffiliate getCompanyAffiliate(Long companyAffiliateId) {
		CompanyAffiliate companyAffiliate = (CompanyAffiliate) getCompanyAffiliateDAO().load(CompanyAffiliate.class, companyAffiliateId);

		if (companyAffiliate == null) {
			logger.error("No such affiliate found in system: " + companyAffiliateId);
			throw new InvalidParameterException("INVALID_AFFILIATE");
		}
		return companyAffiliate;
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
	public Page searchCompanyAffiliate(String login, String email, String companyShortName, int pageNo, int perPage) {

		CompanyAffiliateQuery companyAffiliateQuery = new CompanyAffiliateQuery();
		companyAffiliateQuery.setCompanyShortName(companyShortName);
		companyAffiliateQuery.setEmail(email);
		companyAffiliateQuery.setLogin(login).setOrderByField("login").setPageNo(pageNo).setRows(perPage);
		return getSearchService().list(companyAffiliateQuery);

		//return null;  //To change body of implemented methods use File | Settings | File Templates.
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
		return getCompanyAffiliateDAO().saveCompanyAffiliate(companyAffiliate);

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
	 * @param companyAffiliateId
	 * @return
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

	@Transactional
	@Override
	public CompanyAffiliate createOrUpdateCompanyAffiliate(CompanyAffiliateDTO companyAffiliateDTO, AffiliateDTO affiliateDTO, String companyShortName) {
		if (companyAffiliateDTO == null){
			companyAffiliateDTO = new CompanyAffiliateDTO();
		}
		Long parentAffiliateId = companyAffiliateDTO.getParentCompanyAffiliateId();
		CompanyAffiliate parentCompanyAffiliate = null;
		if (parentAffiliateId != null) {
			parentCompanyAffiliate = getCompanyAffiliate(parentAffiliateId);
		}
		Affiliate affiliate = getAffiliateService().getAffiliateByLogin(affiliateDTO.getLogin());
		//affiliate already exists setting the Id and would update the first name last name as per the newly filled values
		if(affiliate != null) {
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

	public CompanyAffiliateDao getCompanyAffiliateDAO() {
		return companyAffiliateDao;
	}

	public AffiliateService getAffiliateService() {
		return affiliateService;
	}

	public MailService getMailService() {
		return mailService;
	}
}
