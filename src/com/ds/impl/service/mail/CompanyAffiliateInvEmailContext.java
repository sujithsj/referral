package com.ds.impl.service.mail;

import com.ds.pact.service.mail.EmailContext;
import com.ds.pact.service.affiliate.AffiliateService;
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
	private static final String COMPANY_ = "AFFILIATE_CONTEXT_AFFILIATE_LOGIN";

	public CompanyAffiliateInvEmailContext() {

	}

	public Affiliate getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(Affiliate affiliate) {
		this.affiliate = affiliate;
	}

	public AffiliateContext(Affiliate affiliate) {
		this.affiliate = affiliate;
	}


	/**
	 * Map which represents Object State
	 *
	 * @return
	 */
	@Override
	public Map<String, String> getWireRepresentation() {

		Map<String, String> data = new HashMap<String, String>();

		if (getAffiliate() != null) {
			data.put(AFFILIATE_CONTEXT_AFFILIATE_LOGIN, getAffiliate().getLogin());
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
		if (data.containsKey(AFFILIATE_CONTEXT_AFFILIATE_LOGIN)) {
			this.affiliate = ServiceLocatorFactory.getService(AffiliateService.class).getAffiliateByLogin(data.get(AFFILIATE_CONTEXT_AFFILIATE_LOGIN));
		}
	}
}
