package com.ds.impl.service.campaign;

import com.ds.pact.service.campaign.CampaignService;
import com.ds.pact.service.core.SearchService;
import com.ds.search.impl.CampaignQuery;
import com.ds.web.action.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author adlakha.vaibhav
 */
public class CampaignServiceImpl implements CampaignService{

  @Autowired
  private SearchService searchService;

  @Override
  public Page searchCampaign(String name, String companyShortName, int pageNo, int perPage) {
    CampaignQuery campaignQuery = new CampaignQuery();
    campaignQuery.setCompanyShortName(companyShortName);
    campaignQuery.setName(name);

    campaignQuery.setOrderByField("nm").setPageNo(pageNo).setRows(perPage);

    return getSearchService().list(campaignQuery);
  }
  


  public SearchService getSearchService() {
    return searchService;
  }
}
