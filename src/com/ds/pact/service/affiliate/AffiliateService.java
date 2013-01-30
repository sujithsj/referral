package com.ds.pact.service.affiliate;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.company.Company;
import com.ds.domain.user.UserSettings;
import com.ds.exception.DSException;
import com.ds.web.action.Page;
import com.ds.dto.affiliate.AffiliateDTO;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:40:30 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AffiliateService {


    /*public Affiliate createAffiliate(AffiliateDTO affiliateDTO);

	*//**
	 * Saves a new Affiliate Record
	 *
	 * @param affiliate
	 *//*
	public Affiliate saveNewAffiliate(Affiliate affiliate);*/

	/**
	 *
	 * @param affiliate
	 * @return
	 */
	/*public Affiliate updateAffiliate(Affiliate affiliate);*/

	/**
	 * Returns a affiliate corresponding to the specified affiliateId
	 *
	 * @param affiliateId
	 * @return
	 */
	public Affiliate getAffiliate(Long affiliateId);


	/**
	 * Employee Count for a Company Identified by Company Short Name
	 *
	 * @param companyShortName
	 * @return No of Employees of Company
	 */
	public long affiliatesCount(String companyShortName);

	public Page searchAffiliate(String login, String email, String companyShortName, int pageNo, int perPage);

	/*public CompanyAffiliate saveAffiliateCompany(Affiliate affiliate, String companyShortName);*/

	public Affiliate getAffiliateByLogin(String login);


    /**
     * email related methods
      * @param affiliate
     */
    public void sendWelcomeEmail(Affiliate affiliate);

	public void sendAffiliateWaitingApprovalEmail(Affiliate affiliate);


}
