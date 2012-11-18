package com.ds.action.campaign;

import com.ds.domain.campaign.Campaign;
import com.ds.domain.user.User;
import com.ds.pact.service.campaign.CampaignService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
@Component
public class CampaignSearchAction extends BasePaginatedAction {

  private String name;
  private String companyShortName;

  private boolean active = true;
  private Date startDate;
  private Date endDate;
  private Long campaignTypeId;

  private Page campaignPage;
  private List<Campaign> campaigns;

  @Autowired
  private CampaignService campaignService;

  @DefaultHandler
  public Resolution pre() {
    return new ForwardResolution("/pages/campaign/campaign.jsp");
  }

  @SuppressWarnings("unchecked")
  public Resolution searchCampaign() {
    User loggedInUser = SecurityHelper.getLoggedInUser();
    companyShortName = loggedInUser.getCompanyShortName();
    campaignPage = getCampaignService().searchCampaign(name, companyShortName,startDate, endDate, campaignTypeId, active, getPageNo(), getPerPage());
    campaigns = campaignPage.getList();

    return new ForwardResolution("/pages/campaign/campaign.jsp");

  }

  @Override
  public int getPageCount() {
    return campaignPage != null ? campaignPage.getTotalPages() : 0;
  }

  @Override
  public int getResultCount() {
    return campaignPage != null ? campaignPage.getTotalResults() : 0;
  }

  @Override
  public Set<String> getParamSet() {
    HashSet<String> params = new HashSet<String>();
    params.add("name");
    params.add("companyShortName");
    params.add("active");
    params.add("startDate");
    params.add("endDate");
    params.add("campaignTypeId");

    return params;
  }

  public CampaignService getCampaignService() {
    return campaignService;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public Page getCampaignPage() {
    return campaignPage;
  }

  public void setCampaignPage(Page campaignPage) {
    this.campaignPage = campaignPage;
  }

  public List<Campaign> getCampaigns() {
    return campaigns;
  }

  public void setCampaigns(List<Campaign> campaigns) {
    this.campaigns = campaigns;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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

  public Long getCampaignTypeId() {
    return campaignTypeId;
  }

  public void setCampaignTypeId(Long campaignTypeId) {
    this.campaignTypeId = campaignTypeId;
  }
}
