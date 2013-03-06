package com.ds.action.aff;

import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.pact.service.notification.NotificationService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * User: Rahul
 * Date: 3/6/13
 * Time: 11:25 AM
 */
@Component
public class AffiliateAdsAction extends BaseAction {

	private int totalReferrers, referersInLastWeek, numberOfPendingCommission;
	private long numberOfPendingNotifications;
	private String companyShortName;

	private List<Company> allEligibleCompanies;
	private Date startDate, endDate;

	@Autowired
	private NotificationService notificationService;


	@DefaultHandler
	public Resolution pre() {

		User loggedInUser = SecurityHelper.getLoggedInUser();
		String companyShortName = loggedInUser.getCompanyShortName();

		//allEligibleCompanies =

		numberOfPendingNotifications = notificationService.getPendingNotificationForAffiliate(loggedInUser.getEmail());

		return new ForwardResolution("/pages/aff/affiliateAds.jsp");
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getTotalReferrers() {
		return totalReferrers;
	}

	public void setTotalReferrers(int totalReferrers) {
		this.totalReferrers = totalReferrers;
	}

	public int getReferersInLastWeek() {
		return referersInLastWeek;
	}

	public void setReferersInLastWeek(int referersInLastWeek) {
		this.referersInLastWeek = referersInLastWeek;
	}

	public int getNumberOfPendingCommission() {
		return numberOfPendingCommission;
	}

	public void setNumberOfPendingCommission(int numberOfPendingCommission) {
		this.numberOfPendingCommission = numberOfPendingCommission;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public long getNumberOfPendingNotifications() {
		return numberOfPendingNotifications;
	}

	public void setNumberOfPendingNotifications(long numberOfPendingNotifications) {
		this.numberOfPendingNotifications = numberOfPendingNotifications;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public List<Company> getAllEligibleCompanies() {
		return allEligibleCompanies;
	}

	public void setAllEligibleCompanies(List<Company> allEligibleCompanies) {
		this.allEligibleCompanies = allEligibleCompanies;
	}
}
