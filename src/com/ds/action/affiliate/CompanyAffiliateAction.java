package com.ds.action.affiliate;

import com.ds.api.CacheAPI;
import com.ds.api.FeatureAPI;
import com.ds.constants.FeatureType;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateInvite;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.dto.affiliate.AffiliateDTO;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.exception.FeatureNotAccessibleException;
import com.ds.exception.InvalidParameterException;
import com.ds.exception.ValidationException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.security.api.SecurityAPI;
import com.ds.security.helper.SecurityHelper;
import com.ds.security.service.UserService;
import com.ds.web.action.BaseAction;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import net.sourceforge.stripes.action.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:00:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateAction extends BaseAction {

	private String DEFAULT_USER_PWD = "password";

	private String companyShortName;
	private CompanyAffiliateDTO companyAffiliateDTO;
	private AffiliateDTO affiliateDTO;

	//private String employeeId;
	private Long affiliateId;
	private Long companyAffiliateId;
	private List<CompanyAffiliate> companyAffiliateListExcludingSelf;


	private Page companyAffiliateInvitePage;
	private List<CompanyAffiliateInvite> companyAffiliateInvites;

	//private Set<Affiliate> companyAffiliates;
	private User loggedInUser;
	private Company company;

	@Autowired
	private AdminService adminService;
	@Autowired
	private AffiliateService affiliateService;
	@Autowired
	private CompanyAffiliateService companyAffiliateService;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private FeatureAPI featureAPI;
	@Autowired
	private SecurityAPI securityAPI;
	@Autowired
	private UserService userService;
	@Autowired
	private CacheAPI cacheAPI;


/*	@DefaultHandler
	public Resolution createOrEditCompanyAffiliate() {
		loggedInUser = SecurityHelper.getLoggedInUser();
		companyShortName = loggedInUser.getCompanyShortName();
		company = getAdminService().getCompany(companyShortName);
		if (companyAffiliateDTO == null) {
			companyAffiliateDTO = new CompanyAffiliateDTO();
		}
		if (affiliateDTO == null) {
			affiliateDTO = new AffiliateDTO();
		}
		if (companyAffiliateId != null) {
			CompanyAffiliate companyAffiliate = getCompanyAffiliateService().getCompanyAffiliate(companyAffiliateId);
			companyAffiliateDTO.bindCompanyAffiliate(companyAffiliate);
			affiliateDTO.bindAffiliate(companyAffiliate.getAffiliate());
		} else {
			if (!checkAccessToCreateAffiliate()) {
				addRedirectAlertMessage(new SimpleMessage("Could not add affiliate please check your plan"));
				return new RedirectResolution(CompanyAffiliateAction.class, "createOrEditCompanyAffiliate");
			}
		}
		return setParamsForView();
	}
	*/


	@SuppressWarnings("unchecked")
	private Resolution setParamsForView() {
		companyAffiliateListExcludingSelf = companyAffiliateService.getCompanyAffiliatesExcludingSelf(companyAffiliateId, companyShortName);
		return new ForwardResolution("/pages/affiliate/companyAffiliateCrud.jsp");
	}

	public boolean checkAccessToCreateAffiliate() {
		try {
			getFeatureAPI().doesCompanyHaveAccessTo(company, FeatureType.AFFILIATE_COUNT, getAffiliateService().affiliatesCount(companyShortName) + 1);
		} catch (FeatureNotAccessibleException fnae) {
			fnae.printStackTrace();
			return false;
		}
		return true;
	}

	public Resolution saveAffiliate() {
		CompanyAffiliate companyAffiliate;
		try {
			companyAffiliate = companyAffiliateService.createOrUpdateCompanyAffiliate(companyAffiliateDTO, affiliateDTO, companyShortName);
		} catch (ValidationException vae) {
			addRedirectAlertMessage(new SimpleMessage(vae.getMessage()));
			return new RedirectResolution(CompanyAffiliateAction.class, "createOrEditCompanyAffiliate");
		} catch (InvalidParameterException ipe) {
			addRedirectAlertMessage(new SimpleMessage(ipe.getMessage()));
			return new RedirectResolution(CompanyAffiliateAction.class, "createOrEditCompanyAffiliate");
		}

		if (companyAffiliate == null) {
			addRedirectAlertMessage(new SimpleMessage("Could not find/create affiliate"));
			return new RedirectResolution(CompanyAffiliateAction.class, "createOrEditCompanyAffiliate");

		} else {
			addRedirectAlertMessage(new SimpleMessage("Affiliate Updated Successfully"));
			return new RedirectResolution(CompanyAffiliateSearchAction.class, "searchCompanyAffiliates");

		}
	}

	public Resolution resendWelcomeEmail() {
		CompanyAffiliate companyAffiliate = getCompanyAffiliateService().getCompanyAffiliate(companyAffiliateId);
		getAffiliateService().sendWelcomeEmail(companyAffiliate.getAffiliate());
		return new ForwardResolution("/pages/setup.jsp");
	}


	public AdminService getAdminService() {
		return adminService;
	}

	public FeatureAPI getFeatureAPI() {
		return featureAPI;
	}

	public UserService getUserService() {
		return userService;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public SecurityAPI getSecurityAPI() {
		return securityAPI;
	}

	public CompanyAffiliateDTO getCompanyAffiliateDTO() {
		return companyAffiliateDTO;
	}

	public void setCompanyAffiliateDTO(CompanyAffiliateDTO companyAffiliateDTO) {
		this.companyAffiliateDTO = companyAffiliateDTO;
	}

	public AffiliateDTO getAffiliateDTO() {
		return affiliateDTO;
	}

	public void setAffiliateDTO(AffiliateDTO affiliateDTO) {
		this.affiliateDTO = affiliateDTO;
	}

	public Long getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}

	public Long getCompanyAffiliateId() {
		return companyAffiliateId;
	}

	public void setCompanyAffiliateId(Long companyAffiliateId) {
		this.companyAffiliateId = companyAffiliateId;
	}

	public List<CompanyAffiliate> getCompanyAffiliateListExcludingSelf() {
		return companyAffiliateListExcludingSelf;
	}

	public void setCompanyAffiliateListExcludingSelf(List<CompanyAffiliate> companyAffiliateListExcludingSelf) {
		this.companyAffiliateListExcludingSelf = companyAffiliateListExcludingSelf;
	}

	public CompanyAffiliateService getCompanyAffiliateService() {
		return companyAffiliateService;
	}

	public void setCompanyAffiliateService(CompanyAffiliateService companyAffiliateService) {
		this.companyAffiliateService = companyAffiliateService;
	}

	public AffiliateService getAffiliateService() {
		return affiliateService;
	}

	public void setAffiliateService(AffiliateService affiliateService) {
		this.affiliateService = affiliateService;
	}

	public Page getCompanyAffiliateInvitePage() {
		return companyAffiliateInvitePage;
	}

	public void setCompanyAffiliateInvitePage(Page companyAffiliateInvitePage) {
		this.companyAffiliateInvitePage = companyAffiliateInvitePage;
	}

	public List<CompanyAffiliateInvite> getCompanyAffiliateInvites() {
		return companyAffiliateInvites;
	}

	public void setCompanyAffiliateInvites(List<CompanyAffiliateInvite> companyAffiliateInvites) {
		this.companyAffiliateInvites = companyAffiliateInvites;
	}
}
