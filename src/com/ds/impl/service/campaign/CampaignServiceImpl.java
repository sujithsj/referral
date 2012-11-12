package com.ds.impl.service.campaign;

import com.ds.domain.campaign.Campaign;
import com.ds.exception.InvalidParameterException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.campaign.CampaignService;
import com.ds.pact.service.core.SearchService;
import com.ds.search.impl.CampaignQuery;
import com.ds.web.action.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adlakha.vaibhav
 */
@Service
public class CampaignServiceImpl implements CampaignService {

  @Autowired
  private SearchService searchService;

  @Autowired
  private BaseDao baseDao;


  @Override
  public Campaign getCampaignById(Long campaignId) {
    if (campaignId == null) {
      throw new InvalidParameterException("CAMPAIGN_ID_CANNOT_BE_BLANK");
    }
    return (Campaign) getBaseDao().findUniqueByNamedQueryAndNamedParam("getCampaignById", new String[]{"campaignId"}, new Object[]{campaignId});
  }


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

  public BaseDao getBaseDao() {
    return baseDao;
  }
}
