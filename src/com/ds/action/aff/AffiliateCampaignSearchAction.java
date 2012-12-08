package com.ds.action.aff;

import com.ds.domain.campaign.Campaign;
import com.ds.pact.service.campaign.CampaignService;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import com.ds.web.locale.AffiliateLocaleContext;
import com.ds.web.locale.AffiliateLocaleContextHolder;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * @author adlakha.vaibhav
 */
@Component
public class AffiliateCampaignSearchAction extends BasePaginatedAction {

  private Page campaignPage;
  private List<Campaign> campaigns;

  @Autowired
  private CampaignService campaignService;

  @DefaultHandler
  public Resolution pre() {
    
    return searchCampaign();
  }

  @SuppressWarnings("unchecked")
  public Resolution searchCampaign() {
    String companyShortNameForLoggedInAffiliate = null;
    AffiliateLocaleContext  affiliateLocaleContext = AffiliateLocaleContextHolder.getAffiliateLocaleContext();
    if(affiliateLocaleContext !=null){
      companyShortNameForLoggedInAffiliate = affiliateLocaleContext.getCompanyShortName();
    }

    if(StringUtils.isNotBlank(companyShortNameForLoggedInAffiliate)){
      campaignPage = getCampaignService().getCampaignsVisibleToAffiliate(companyShortNameForLoggedInAffiliate,getPageNo(), getPerPage() );
      campaigns = campaignPage.getList();
    }

    return new ForwardResolution("/pages/aff/affiliateCampaign.jsp");
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
    params.add("companyShortName");

    return params;
  }

  public Page getCampaignPage() {
    return campaignPage;
  }

  public List<Campaign> getCampaigns() {
    return campaigns;
  }

  public CampaignService getCampaignService() {
    return campaignService;
  }
}
