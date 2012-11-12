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
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

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

  private Page campaignPage;
  private List<Campaign> campaigns;

  @Autowired
  private CampaignService campaignService;

  @DefaultHandler
  public Resolution pre() {
    return new ForwardResolution("/pages/company/campaign.jsp");
  }

  @SuppressWarnings("unchecked")
  public Resolution searchMarketingMaterial() {
    User loggedInUser = SecurityHelper.getLoggedInUser();
    companyShortName = loggedInUser.getCompanyShortName();
    campaignPage = getCampaignService().searchCampaign(name, companyShortName, getPageNo(), getPerPage());
    campaigns = campaignPage.getList();

    return new ForwardResolution("/pages/company/campaign.jsp");

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
}
