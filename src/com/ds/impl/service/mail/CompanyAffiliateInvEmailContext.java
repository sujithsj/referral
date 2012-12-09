package com.ds.impl.service.mail;

import com.ds.pact.service.mail.EmailContext;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliateInvite;
import com.ds.impl.service.ServiceLocatorFactory;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 9, 2012
 * Time: 1:09:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateInvEmailContext extends EmailContext {


	private CompanyAffiliateInvite companyAffiliateInvite;
	private static final String AFFILIATE_EMAIL = "AFFILIATE_EMAIL";

	public CompanyAffiliateInvEmailContext() {

	}


	public CompanyAffiliateInvEmailContext(CompanyAffiliateInvite companyAffiliateInvite) {
		this.companyAffiliateInvite = companyAffiliateInvite;
	}


	/**
	 * Map which represents Object State
	 *
	 * @return
	 */
	@Override
	public Map<String, String> getWireRepresentation() {

		Map<String, String> data = new HashMap<String, String>();

		if (getCompanyAffiliateInvite() != null) {
			data.put(AFFILIATE_EMAIL, getCompanyAffiliateInvite().getAffiliateEmail());
		}
		return data;

	}

	/**
	 * Resconstruct Object from Map
	 *
	 * @param data
	 */
	@Override
	public void prepareFromWireRepresentation(Map<String, String> data) {
		if (data.containsKey(AFFILIATE_EMAIL)) {
			//this.companyAffiliateInvite = ServiceLocatorFactory.getService(CompanyAffiliateService.class).getAffiliateByLogin(data.get(AFFILIATE_EMAIL));
		}
	}

	public CompanyAffiliateInvite getCompanyAffiliateInvite() {
		return companyAffiliateInvite;
	}

	public void setCompanyAffiliateInvite(CompanyAffiliateInvite companyAffiliateInvite) {
		this.companyAffiliateInvite = companyAffiliateInvite;
	}
}
