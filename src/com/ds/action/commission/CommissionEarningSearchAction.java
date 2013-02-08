package com.ds.action.commission;

import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.commission.CommissionEarning;
import com.ds.domain.user.User;
import com.ds.pact.service.commission.CommissionEarningService;
import com.ds.security.helper.SecurityHelper;
import com.ds.constants.EnumCampaignType;
import com.ds.constants.EnumCommissionEarningStatus;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 24, 2013
 * Time: 1:19:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CommissionEarningSearchAction extends BasePaginatedAction {

    private Long affiliateId;
    private Date startDate, endDate;

    private Long commissionEarningStatusId;
    private String companyShortName, customer;


    private Page commissionEarningPage;
    private List<CommissionEarning> commissionEarnings;


    @Autowired
    private CommissionEarningService commissionEarningService;


    @DefaultHandler
    public Resolution pre() {

        return searchCommissionEarnings();
    }

    @SuppressWarnings("unchecked")
    public Resolution searchCommissionEarnings() {
        User loggedInUser = SecurityHelper.getLoggedInUser();
        companyShortName = loggedInUser.getCompanyShortName();

        commissionEarningPage = getCommissionEarningService().searchCommissionEarning(affiliateId,
                companyShortName, startDate, endDate,
                commissionEarningStatusId, customer, getPageNo(), getPerPage());

        if (commissionEarningPage != null) {
            commissionEarnings = commissionEarningPage.getList();
        }

        if (commissionEarningStatusId == null) {
            commissionEarningStatusId = EnumCommissionEarningStatus.ALL.getId();
        }

        return new ForwardResolution("/pages/commission/commissionEarning.jsp");

    }

    @Override
    public int getPageCount() {
        return commissionEarningPage != null ? commissionEarningPage.getTotalPages() : 0;
    }

    @Override
    public int getResultCount() {
        return commissionEarningPage != null ? commissionEarningPage.getTotalResults() : 0;
    }

    @Override
    public Set<String> getParamSet() {
        HashSet<String> params = new HashSet<String>();
        params.add("affiliateId");
        params.add("companyShortName");
        params.add("customer");
        params.add("startDate");
        params.add("endDate");
        params.add("commissionEarningStatusId");

        return params;
    }

    public List<CommissionEarning> getCommissionEarnings() {
        return commissionEarnings;
    }

    public void setCommissionEarnings(List<CommissionEarning> commissionEarnings) {
        this.commissionEarnings = commissionEarnings;
    }

    public CommissionEarningService getCommissionEarningService() {
        return commissionEarningService;
    }

    public Long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(Long affiliateId) {
        this.affiliateId = affiliateId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCommissionEarningStatusId() {
        return commissionEarningStatusId;
    }

    public void setCommissionEarningStatusId(Long commissionEarningStatusId) {
        this.commissionEarningStatusId = commissionEarningStatusId;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
