package com.ds.pact.service.affiliate;

import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateInvite;
import com.ds.domain.company.Company;
import com.ds.web.action.Page;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 18, 2012
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyAffiliateService {

	/*public CompanyAffiliate saveCompanyAffiliate(CompanyAffiliate companyAffiliate);*/

    /*public CompanyAffiliate createCompanyAffiliate(Affiliate affiliate, String companyShortName);*/

	public CompanyAffiliate getCompanyAffiliate(Long companyAffiliateId);

	public CompanyAffiliate getCompanyAffiliate(Long affiliateId, String companyShortName);

	public Page searchCompanyAffiliate(String login, String email, String companyShortName, int pageNo, int perPage);



	/*public void sendWelcomeEmail(Affiliate affiliate);*/

	public List<CompanyAffiliate> getCompanyAffiliatesExcludingSelf(Long companyAffiliateId, String companyShortName);

	//public CompanyAffiliate createOrUpdateCompanyAffiliate(CompanyAffiliateDTO companyAffiliateDTO, AffiliateDTO affiliateDTO, String companyShortName);

	public List<CompanyAffiliate> getAllCompanyAffiliates(String companyShortName);

	public List<CompanyAffiliate> getActiveCompanyAffiliates(String companyShortName);

	public long getActiveCompanyAffiliateCount(String companyShortName);

	public List<Company> getAllCompanyForAffiliate(String email);

	/**
	 * invite related methods
	 */

	/**
	 * @param companyShortName
	 * @param affiliateEmail
	 * @return
	 */
	public CompanyAffiliateInvite addCompanyAffiliateInvite(String companyShortName, String affiliateEmail);

	public CompanyAffiliateInvite getCompanayAffiliateInvite(String companyShortName, String affiliateEmail);

	public Page searchCompanyAffiliatePendingInvites(String companyShortName, int pageNo, int perPage);

	public CompanyAffiliateInvite getCompanyAffiliateInvite(Long companyAffiliateInviteId);

	public CompanyAffiliateInvite deleteCompanyAffiliateInvite(CompanyAffiliateInvite companyAffiliateInvite);

	public CompanyAffiliateInvite saveCompanyAffiliateInvite(CompanyAffiliateInvite companyAffiliateInvite);

	/**
	 * email methods
	 */

	/**
	 * @param companyAffiliateInvite
	 */
	public void sendCompanyAffiliateInvitationEmail(CompanyAffiliateInvite companyAffiliateInvite);
}
