package com.ds.action.aff;

import com.ds.action.affiliate.CompanyAffiliateInviteAction;
import com.ds.domain.affiliate.CompanyAffiliateInvite;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
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
 * User: Rahul
 * Date: Feb 9, 2013
 * Time: 4:14:18 PM
 */
public class CompanyAffiliateNotificationAction extends BasePaginatedAction {

	private String companyShortName;

	@Validate(required = true)
	private String affiliateEmail;


	private Page companyAffiliateNotificationPage;
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
	private UserService userService;


	@DefaultHandler
	@DontValidate
	public Resolution displayAllNotifications() {

		loggedInUser = SecurityHelper.getLoggedInUser();
		companyShortName = loggedInUser.getCompanyShortName();

		companyAffiliateNotificationPage = getCompanyAffiliateService().searchCompanyAffiliatePendingInvites(companyShortName, getPageNo(), getPerPage());
		companyAffiliateInvites = companyAffiliateNotificationPage.getList();
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

			} else if (companyAffiliateInvite.getDeleted()){ //someone trying to send email to already existing invite
				companyAffiliateInvite.setDeleted(false);
				companyAffiliateInvite = getCompanyAffiliateService().saveCompanyAffiliateInvite(companyAffiliateInvite);
				getCompanyAffiliateService().sendCompanyAffiliateInvitationEmail(companyAffiliateInvite);
			}
			else { //someone trying to send email to already existing invite
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
		return companyAffiliateNotificationPage != null ? companyAffiliateNotificationPage.getTotalPages() : 0;
	}

	@Override
	public int getResultCount() {
		return companyAffiliateNotificationPage != null ? companyAffiliateNotificationPage.getTotalResults() : 0;
	}

	@Override
	public Set<String> getParamSet() {
		return null;
	}


	public AdminService getAdminService() {
		return adminService;
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

	public Page getCompanyAffiliateNotificationPage() {
		return companyAffiliateNotificationPage;
	}

	public void setCompanyAffiliateNotificationPage(Page companyAffiliateNotificationPage) {
		this.companyAffiliateNotificationPage = companyAffiliateNotificationPage;
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
