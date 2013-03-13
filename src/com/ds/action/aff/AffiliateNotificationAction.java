package com.ds.action.aff;

import com.ds.domain.company.Company;
import com.ds.domain.notification.Notification;
import com.ds.domain.user.User;
import com.ds.pact.service.notification.NotificationService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import net.sourceforge.stripes.action.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * User: Rahul
 * Date: Feb 9, 2013
 * Time: 4:14:18 PM
 */
@SuppressWarnings("unchecked")
public class AffiliateNotificationAction extends BasePaginatedAction {

	/*private String companyShortName;

	@Validate(required = true)
	private String affiliateEmail;*/


	private Page affiliateNotificationPage;
	private List<Notification> notificationList;

	private User loggedInUser;
	private Company company;
	private Long notificationId;

	/*@Autowired
	private AdminService adminService;
	@Autowired
	private AffiliateService affiliateService;
	@Autowired
	private CompanyAffiliateService companyAffiliateService;
	@Autowired
	private UserService userService;*/
	@Autowired
	private NotificationService notificationService;


	@DefaultHandler
	@DontValidate
	public Resolution displayAllNotifications() {

		loggedInUser = SecurityHelper.getLoggedInUser();
		//companyShortName = loggedInUser.getCompanyShortName();

		affiliateNotificationPage = getNotificationService().searchUserPendingNotification(loggedInUser.getUsername(), pageNo, perPage);
		notificationList = affiliateNotificationPage.getList();
		return new ForwardResolution("/pages/aff/affiliateNotification.jsp");

	}

	/*@ValidationMethod()
	public void isValidAffiliateEmail() {
		if (!BaseUtils.isValidEmail(affiliateEmail)) {
			getContext().getValidationErrors().add("invalidEmail", new LocalizableError("/Signup.action.InvalidEmail"));
		}
	}


	public Resolution sendInviteEmail() {

		CompanyAffiliateInvite companyAffiliateInvite = null;
		loggedInUser = SecurityHelper.getLoggedInUser();
		companyShortName = loggedInUser.getCompanyShortName();
		if (notificationId != null) {
			companyAffiliateInvite = getCompanyAffiliateService().getCompanyAffiliateInvite(notificationId);
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

			} else if (companyAffiliateInvite.getDeleted()) { //someone trying to send email to already existing invite
				companyAffiliateInvite.setDeleted(false);
				companyAffiliateInvite = getCompanyAffiliateService().saveCompanyAffiliateInvite(companyAffiliateInvite);
				getCompanyAffiliateService().sendCompanyAffiliateInvitationEmail(companyAffiliateInvite);
			} else { //someone trying to send email to already existing invite
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

		*//*CompanyAffiliateInvite companyAffiliateInvite = getCompanyAffiliateService().getCompanyAffiliateInvite(notificationId);
		if (companyAffiliateInvite != null) {
			getCompanyAffiliateService().deleteCompanyAffiliateInvite(companyAffiliateInvite);
		}*//*

		return new RedirectResolution(AffiliateNotificationAction.class);

	}*/

	public Resolution notificationRead() {

			notificationService.markNotificationRead(notificationId);
			return new RedirectResolution(AffiliateNotificationAction.class);

		}


	@Override
	public int getPageCount() {
		return affiliateNotificationPage != null ? affiliateNotificationPage.getTotalPages() : 0;
	}

	@Override
	public int getResultCount() {
		return affiliateNotificationPage != null ? affiliateNotificationPage.getTotalResults() : 0;
	}

	@Override
	public Set<String> getParamSet() {
		return null;
	}


	/*public AdminService getAdminService() {
		return adminService;
	}

	public UserService getUserService() {
		return userService;
	}*/

	/*public String getCompanyShortName() {
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
	}*/

	public Page getAffiliateNotificationPage() {
		return affiliateNotificationPage;
	}

	public void setAffiliateNotificationPage(Page affiliateNotificationPage) {
		this.affiliateNotificationPage = affiliateNotificationPage;
	}

	/*public String getAffiliateEmail() {
		return affiliateEmail;
	}

	public void setAffiliateEmail(String affiliateEmail) {
		this.affiliateEmail = affiliateEmail;
	}*/

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}
}
