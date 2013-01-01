package com.ds.action.aff;

import com.ds.web.action.BaseAction;
import com.ds.web.locale.AffiliateLocaleContext;
import com.ds.web.locale.AffiliateLocaleContextHolder;
import com.ds.dto.affiliate.AffiliateDTO;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 29, 2012
 * Time: 6:54:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateSignUpAction extends BaseAction {

	private AffiliateDTO affiliateDTO;

	private CompanyAffiliateDTO companyAffiliateDTO;

	AffiliateLocaleContext affiliateLocaleContext = AffiliateLocaleContextHolder.getAffiliateLocaleContext();


	@DefaultHandler
	public Resolution registerAffiliate(){
		System.out.println(affiliateLocaleContext.getCompanyShortName());
		return new RedirectResolution("http://www.google.com");
	}
	public AffiliateDTO getAffiliateDTO() {
		return affiliateDTO;
	}

	public void setAffiliateDTO(AffiliateDTO affiliateDTO) {
		this.affiliateDTO = affiliateDTO;
	}

	public AffiliateLocaleContext getAffiliateLocaleContext() {
		return affiliateLocaleContext;
	}

	public void setAffiliateLocaleContext(AffiliateLocaleContext affiliateLocaleContext) {
		this.affiliateLocaleContext = affiliateLocaleContext;
	}

	public CompanyAffiliateDTO getCompanyAffiliateDTO() {
		return companyAffiliateDTO;
	}

	public void setCompanyAffiliateDTO(CompanyAffiliateDTO companyAffiliateDTO) {
		this.companyAffiliateDTO = companyAffiliateDTO;
	}
}
