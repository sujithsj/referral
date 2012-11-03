package com.ds.impl.service.mail;

import com.ds.domain.affiliate.Affiliate;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.admin.AffiliateService;
import com.ds.pact.service.mail.EmailContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 3, 2012
 * Time: 4:23:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateContext extends EmailContext {


	private Affiliate affiliate;
	private static final String AFFILIATE_CONTEXT_AFFILIATE_LOGIN = "AFFILIATE_CONTEXT_AFFILIATE_LOGIN";

	public AffiliateContext() {

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
