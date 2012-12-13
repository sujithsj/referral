package com.ds.action.affiliate;

import com.ds.api.FeatureAPI;
import com.ds.domain.affiliate.CompanyAffiliateInvite;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.security.api.SecurityAPI;
import com.ds.security.helper.SecurityHelper;
import com.ds.security.service.UserService;
import com.ds.utils.BaseUtils;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 8, 2012
 * Time: 11:23:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateInviteAction extends BasePaginatedAction {


	private String companyShortName;

	@Validate(required = true)
	private String affiliateEmail;


	private Page companyAffiliateInvitePage;
	private List<CompanyAffiliateInvite> companyAffiliateInvites;

	private User loggedInUser;
	private Company company;
	private Long companyAffiliateInviteId;

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


	@DefaultHandler
	@DontValidate
	public Resolution displayAllCompanyInvites() {

		loggedInUser = SecurityHelper.getLoggedInUser();
		companyShortName = loggedInUser.getCompanyShortName();

		companyAffiliateInvitePage = getCompanyAffiliateService().searchCompanyAffiliatePendingInvites(companyShortName, getPageNo(), getPerPage());
		companyAffiliateInvites = companyAffiliateInvitePage.getList();
		return new ForwardResolution("/pages/affiliate/companyAffiliateInvites.jsp");

	}

	@ValidationMethod()
	public void isValidAffiliateEmail() {
		if (!BaseUtils.isValidEmail(affiliateEmail)) {
			getContext().getValidationErrors().add("invalidEmail", new LocalizableError("/Signup.action.InvalidEmail"));
		}
	}


	public Resolution sendInviteEmail() {

		CompanyAffiliateInvite companyAffiliateInvite = null;
		loggedInUser = SecurityHelper.getLoggedInUser();
		companyShortName = loggedInUser.getCompanyShortName();
		if (companyAffiliateInviteId != null) {
			companyAffiliateInvite = getCompanyAffiliateService().getCompanyAffiliateInvite(companyAffiliateInviteId);
			if (companyAffiliateInvite != null) {
				getCompanyAffiliateService().sendCompanyAffiliateInvitationEmail(companyAffiliateInvite);
			} else {
				addValidationError("inviteAlreadyExists", new LocalizableError("/Invite.action.invite.id.error"));
				return new ForwardResolution(getContext().getSourcePage());
			}
		} else {
			companyAffiliateInvite = getCompanyAffiliateService().getCompanayAffiliateInvite(companyShortName, affiliateEmail);
			if (companyAffiliateInvite == null) {
				companyAffiliateInvite = getCompanyAffiliateService().addCompanyAffiliateInvite(companyShortName, affiliateEmail);
				getCompanyAffiliateService().sendCompanyAffiliateInvitationEmail(companyAffiliateInvite);

			} else {
				addValidationError("inviteAlreadyExists", new LocalizableError("/Invite.action.email.id.already.exists"));
				return new ForwardResolution(getContext().getSourcePage());
			}
		}
		return new RedirectResolution(CompanyAffiliateInviteAction.class);
	}

	@DontValidate
	public Resolution deleteInvite() {

		loggedInUser = SecurityHelper.getLoggedInUser();
		companyShortName = loggedInUser.getCompanyShortName();

		CompanyAffiliateInvite companyAffiliateInvite = getCompanyAffiliateService().getCompanyAffiliateInvite(companyAffiliateInviteId);
		if (companyAffiliateInvite != null) {
			getCompanyAffiliateService().deleteCompanyAffiliateInvite(companyAffiliateInvite);
		}

		return new RedirectResolution(CompanyAffiliateInviteAction.class);

	}


	@Override
	public int getPageCount() {
		return company != null ? companyAffiliateInvitePage.getTotalPages() : 0;
	}

	@Override
	public int getResultCount() {
		return companyAffiliateInvitePage != null ? companyAffiliateInvitePage.getTotalResults() : 0;
	}

	@Override
	public Set<String> getParamSet() {
		return null;
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

	public String getAffiliateEmail() {
		return affiliateEmail;
	}

	public void setAffiliateEmail(String affiliateEmail) {
		this.affiliateEmail = affiliateEmail;
	}

	public Long getCompanyAffiliateInviteId() {
		return companyAffiliateInviteId;
	}

	public void setCompanyAffiliateInviteId(Long companyAffiliateInviteId) {
		this.companyAffiliateInviteId = companyAffiliateInviteId;
	}
}
