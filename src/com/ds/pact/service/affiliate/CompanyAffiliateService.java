package com.ds.pact.service.affiliate;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateInvite;
import com.ds.domain.company.Company;
import com.ds.domain.user.UserSettings;
import com.ds.exception.DSException;
import com.ds.web.action.Page;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.dto.affiliate.AffiliateDTO;
import com.ds.search.impl.AffiliateGroupQuery;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 18, 2012
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyAffiliateService {

	public CompanyAffiliate saveCompanyAffiliate(CompanyAffiliate companyAffiliate);

	public CompanyAffiliate getCompanyAffiliate(Long companyAffiliateId);

	public Page searchCompanyAffiliate(String login, String email, String companyShortName, int pageNo, int perPage);

	public CompanyAffiliate saveCompanyAffiliate(Affiliate affiliate, String companyShortName);

	public void sendWelcomeEmail(Affiliate affiliate);

	public List<CompanyAffiliate> getCompanyAffiliatesExcludingSelf (Long companyAffiliateId, String companyShortName);

	public CompanyAffiliate createOrUpdateCompanyAffiliate(CompanyAffiliateDTO companyAffiliateDTO, AffiliateDTO affiliateDTO, String companyShortName);

	public List<CompanyAffiliate> getAllCompanyAffiliates(String companyShortName);

	public Page searchCompanyAffiliatePendingInvites(String companyShortName, int pageNo, int perPage);

	public CompanyAffiliateInvite addCompanyAffiliateInvite(String companyShortName, String affiliateEmail);

	public CompanyAffiliateInvite getCompanayAffiliateInvite(String companyShortName, String affiliateEmail);

	public void sendCompanyAffiliateInvitationEmail(CompanyAffiliateInvite companyAffiliateInvite);

	public CompanyAffiliateInvite getCompanyAffiliateInvite(Long companyAffiliateInviteId);

	public CompanyAffiliateInvite deleteCompanyAffiliateInvite(CompanyAffiliateInvite companyAffiliateInvite);

	public CompanyAffiliateInvite saveCompanyAffiliateInvite(CompanyAffiliateInvite companyAffiliateInvite);

}
