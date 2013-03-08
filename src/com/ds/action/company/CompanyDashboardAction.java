package com.ds.action.company;

import com.ds.web.action.BaseAction;
import com.ds.pact.service.dashboard.CompanyDashboardService;
import com.ds.domain.user.User;
import com.ds.security.helper.SecurityHelper;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 9, 2013
 * Time: 6:11:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CompanyDashboardAction extends BaseAction {

  private int totalReferrers, referersInLastWeek, numberOfPendingCommission;
  private long totalRevenue, totalCommission;

  private String companyShortName;

  private Date startDate, endDate;

  @Autowired
  private CompanyDashboardService companyDashboardService;


  @DefaultHandler
  public Resolution pre() {
    User loggedInUser = SecurityHelper.getLoggedInUser();
    companyShortName = loggedInUser.getCompanyShortName();

    totalReferrers = getCompanyDashboardService().getTotalReferrersForCompany(companyShortName);
    referersInLastWeek = getCompanyDashboardService().getReferrersForCompanyInTimePeriod(companyShortName, null, null);
    numberOfPendingCommission = getCompanyDashboardService().getTotalNumberOfPendingCommissions(companyShortName);
    totalRevenue = getCompanyDashboardService().getTotalRevenueTrackedForCompany(companyShortName);
    totalCommission = getCompanyDashboardService().getPaidCommissionTrackedForCompany(companyShortName);

    return new ForwardResolution("/pages/company/dashboard.jsp");
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

  public CompanyDashboardService getCompanyDashboardService() {
    return companyDashboardService;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }
}
