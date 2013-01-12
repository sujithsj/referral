package com.ds.action.aff;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.dto.affiliate.AffiliateDTO;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.exception.CompositeValidationException;
import com.ds.exception.ValidationConstants;
import com.ds.exception.ValidationException;
import com.ds.exception.FeatureNotAccessibleException;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.utils.BaseUtils;
import com.ds.web.action.BaseAction;
import com.ds.web.locale.AffiliateLocaleContext;
import com.ds.web.locale.AffiliateLocaleContextHolder;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 29, 2012
 * Time: 6:54:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateSignUpAction extends BaseAction {

	private Logger logger = LoggerFactory.getLogger(AffiliateSignUpAction.class);
	private AffiliateDTO affiliateDTO;

	private CompanyAffiliateDTO companyAffiliateDTO;

	@Autowired
	private AffiliateService affiliateService;

	private AffiliateLocaleContext affiliateLocaleContext = AffiliateLocaleContextHolder.getAffiliateLocaleContext();

	@Autowired
	CompanyAffiliateService companyAffiliateService;

	@ValidationMethod()
	public void isValidAffiliateEmail() {
		if (!BaseUtils.isValidEmail(affiliateDTO.getEmail())) {
			getContext().getValidationErrors().add("invalidEmail", new LocalizableError("/Signup.action.InvalidEmail"));
		}
		if (StringUtils.isBlank(affiliateDTO.getFirstName())) {
			getContext().getValidationErrors().add("firstNameMandatory", new LocalizableError("/Signup.action.FirstNameMandatory"));
		}
		if (StringUtils.isBlank(affiliateDTO.getPasswordChecksum())) {
			getContext().getValidationErrors().add("passwordMandatory", new LocalizableError("/Signup.action.PasswordMandatory"));
		}

	}

	@DefaultHandler
	public Resolution registerAffiliate() {
		System.out.println(affiliateLocaleContext.getCompanyShortName());


		//create affiliate (check if email already there then only need to create compnay affiliate)
		try {
			Affiliate affiliate = getAffiliateService().createAffiliate(affiliateDTO);

			CompanyAffiliate companyAffiliate = getCompanyAffiliateService().createCompanyAffiliate(affiliate, affiliateLocaleContext.getCompanyShortName());

		} catch (CompositeValidationException cve) {
			List<ValidationException> validationExceptions = cve.getValidationExceptions();
			for (ValidationException validationException : validationExceptions) {
				if (validationException.getFieldName().equals(ValidationConstants.INVALID_AFFILIATE_EMAIL)) {
					getContext().getValidationErrors().add("invalidEmail", new LocalizableError("/Signup.action.InvalidEmail"));
				} else if (validationException.getFieldName().equals(ValidationConstants.AFFILIATE_LOGIN_EXISTS)) {
					addValidationError("userExists", new LocalizableError("/Signup.action.email.id.already.exists"));
					return new ForwardResolution(getContext().getSourcePage());
				}
			}
			logger.error("user exists with this email id " + affiliateDTO.getLogin());
		}

		addRedirectAlertMessage(new SimpleMessage("Successfully Signed Up, Please login to continue"));
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

	public AffiliateService getAffiliateService() {
		return affiliateService;
	}

	public void setAffiliateService(AffiliateService affiliateService) {
		this.affiliateService = affiliateService;
	}

	public CompanyAffiliateService getCompanyAffiliateService() {
		return companyAffiliateService;
	}

	public void setCompanyAffiliateService(CompanyAffiliateService companyAffiliateService) {
		this.companyAffiliateService = companyAffiliateService;
	}
}
