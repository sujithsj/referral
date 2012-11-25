package com.ds.pact.service.affiliate;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.company.Company;
import com.ds.domain.user.UserSettings;
import com.ds.exception.DSException;
import com.ds.web.action.Page;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.dto.affiliate.AffiliateDTO;

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


	/**
	 *
	 * @param companyAffiliate
	 * @return
	 */
	public CompanyAffiliate saveCompanyAffiliate(CompanyAffiliate companyAffiliate);



	/**
	 * Returns a affiliate corresponding to the specified affiliateId
	 *
	 * @param companyAffiliateId
	 * @return
	 */
	public CompanyAffiliate getCompanyAffiliate(Long companyAffiliateId);

	public Page companySearchAffiliate(String login, String email, String companyShortName, int pageNo, int perPage);

	public CompanyAffiliate saveAffiliateCompany(Affiliate affiliate, String companyShortName);

	public void sendWelcomeEmail(Affiliate affiliate);

	public Page searchAffiliateGroup(String name, String companyShortName, int pageNo, int perPage);

	public List<CompanyAffiliate> getCompanyAffiliatesExcludingSelf (Long companyAffiliateId, String companyShortName);

/*
	public CompanyAffiliate updateCompanyAffiliate(CompanyAffiliateDTO companyAffiliateDTO, AffiliateDTO affiliateDTO, String companyShortName, Long companyAffiliateId);
*/

	public CompanyAffiliate createOrUpdateCompanyAffiliate(CompanyAffiliateDTO companyAffiliateDTO, AffiliateDTO affiliateDTO, String companyShortName);
}
