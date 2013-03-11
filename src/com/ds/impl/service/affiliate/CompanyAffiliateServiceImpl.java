package com.ds.impl.service.affiliate;

import com.ds.api.FeatureAPI;
import com.ds.core.event.EmailEvent;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateInvite;
import com.ds.domain.company.Company;
import com.ds.impl.service.mail.CompanyAffiliateInvEmailContext;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.mail.EmailTemplateService;
import com.ds.pact.service.mail.MailService;
import com.ds.pact.service.notification.NotificationService;
import com.ds.pact.dao.BaseDao;
import com.ds.search.impl.CompanyAffiliateInviteQuery;
import com.ds.search.impl.CompanyAffiliateQuery;
import com.ds.web.action.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private BaseDao baseDao;

    @Override
    public CompanyAffiliate getCompanyAffiliate(Long companyAffiliateId) {
        CompanyAffiliate companyAffiliate = getBaseDao().load(CompanyAffiliate.class, companyAffiliateId);

        if (companyAffiliate == null) {
            logger.error("No such affiliate found in system: " + companyAffiliateId);
            throw new InvalidParameterException("INVALID_AFFILIATE");
        }
        return companyAffiliate;
    }


    public CompanyAffiliate getCompanyAffiliate(Long affiliateId, String companyShortName) {
        return (CompanyAffiliate) getBaseDao().findUniqueByNamedQueryAndNamedParam("getCompanyAffiliateByAffIdAndCompany", new String[]{"affiliateId", "companyShortName"}, new Object[]{affiliateId, companyShortName});
    }


    @Override
    public Page searchCompanyAffiliate(String login, String email, String companyShortName, int pageNo, int perPage) {

        CompanyAffiliateQuery companyAffiliateQuery = new CompanyAffiliateQuery();
        companyAffiliateQuery.setCompanyShortName(companyShortName);
        companyAffiliateQuery.setEmail(email);
        companyAffiliateQuery.setLogin(login).setOrderByField("login").setPageNo(pageNo).setRows(perPage);
        return getSearchService().list(companyAffiliateQuery);
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

	public List<Company> getAllCompanyForAffiliate(String email) {


		return null;
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
        return (CompanyAffiliateInvite) getBaseDao().save(companyAffiliateInvite);
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
        EmailEvent emailEvent = new EmailEvent(EmailTemplateService.EmailEventType.CompanyAffiliateInvitationEmail, companyAffiliateInvEmailContext);
        getMailService().sendAsyncMail(emailEvent);

    }

    @Transactional
    @Override
    public CompanyAffiliateInvite deleteCompanyAffiliateInvite(CompanyAffiliateInvite companyAffiliateInvite) {
        companyAffiliateInvite.setDeleted(true);
        return (CompanyAffiliateInvite) getBaseDao().save(companyAffiliateInvite);
    }

    public CompanyAffiliateInvite getCompanyAffiliateInvite(Long companyAffiliateInviteId) {
        CompanyAffiliateInvite companyAffiliateInvite = getBaseDao().load(CompanyAffiliateInvite.class, companyAffiliateInviteId);
        if (companyAffiliateInvite == null) {
            logger.error("No such invitation found in system: " + companyAffiliateInviteId);
            throw new InvalidParameterException("INVALID_COMPANY_AFFILIATE_INVITE");
        }
        return companyAffiliateInvite;
    }

    @Override
    @Transactional
    public CompanyAffiliateInvite saveCompanyAffiliateInvite(CompanyAffiliateInvite companyAffiliateInvite) {
        return (CompanyAffiliateInvite) getBaseDao().save(companyAffiliateInvite);
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


    public AdminService getAdminService() {
        return adminService;
    }


    public NotificationService getNotificationService() {
        return notificationService;
    }

    public BaseDao getBaseDao() {
        return baseDao;
    }
}
