package com.ds.action.aff;

import com.ds.web.action.BaseAction;
import com.ds.web.locale.AffiliateLocaleContext;
import com.ds.web.locale.AffiliateLocaleContextHolder;
import com.ds.dto.affiliate.AffiliateDTO;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.utils.BaseUtils;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.validation.ValidationMethod;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import org.apache.commons.lang.StringUtils;

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

	@ValidationMethod()
	public void isValidAffiliateEmail() {
		if (!BaseUtils.isValidEmail(affiliateDTO.getEmail())) {
			getContext().getValidationErrors().add("invalidEmail", new LocalizableError("/Signup.action.InvalidEmail"));
		}
		if (StringUtils.isBlank(affiliateDTO.getFirstName())){
			getContext().getValidationErrors().add("firstNameMandatory", new LocalizableError("/Signup.action.FirstNameMandatory"));
		}
		if (StringUtils.isBlank(affiliateDTO.getPasswordChecksum())){
			getContext().getValidationErrors().add("passwordMandatory", new LocalizableError("/Signup.action.PasswordMandatory"));
		}

	}

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
