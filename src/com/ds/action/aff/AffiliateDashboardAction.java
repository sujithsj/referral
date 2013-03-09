package com.ds.action.aff;

/**
 * User: Rahul
 * Date: Feb 9, 2013
 * Time: 1:49:47 PM
 */

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


@Component
public class AffiliateDashboardAction extends BaseAction {

	private int totalReferrers, referersInLastWeek, numberOfPendingCommission;
	private long totalRevenue, totalCommission, numberOfPendingNotifications;

	private Date startDate, endDate;

	@Autowired
	private NotificationService notificationService;


	@DefaultHandler
	public Resolution pre() {
		//numberOfPendingNotifications = 500;
		User loggedInUser = SecurityHelper.getLoggedInUser();
		//String companyShortName = loggedInUser.getCompanyShortName();

		/*totalReferrers = getCompanyDashboardService().getTotalReferrersForCompany(companyShortName);*/
		numberOfPendingNotifications = notificationService.getPendingNotificationCountForUser(loggedInUser.getEmail());

		return new ForwardResolution("/pages/aff/affiliateDashboard.jsp");
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

	public long getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(long totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public long getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(long totalCommission) {
		this.totalCommission = totalCommission;
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
}
