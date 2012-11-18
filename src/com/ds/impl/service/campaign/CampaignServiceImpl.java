package com.ds.impl.service.campaign;

import com.ds.constants.EnumCampaignType;
import com.ds.domain.campaign.Campaign;
import com.ds.domain.campaign.CampaignType;
import com.ds.exception.InvalidParameterException;
import com.ds.exception.ValidationException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.campaign.CampaignService;
import com.ds.pact.service.core.SearchService;
import com.ds.search.impl.CampaignQuery;
import com.ds.web.action.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
  public Page searchCampaign(String name, String companyShortName, Date startDate, Date endDate, Long campaignTypeId, boolean active, int pageNo, int perPage) {
    CampaignQuery campaignQuery = new CampaignQuery();
    campaignQuery.setCompanyShortName(companyShortName);
    campaignQuery.setName(name).setStartDate(startDate).setEndDate(endDate).setCampaignType(EnumCampaignType.getById(campaignTypeId));

    campaignQuery.setOrderByField("nm").setPageNo(pageNo).setRows(perPage);

    return getSearchService().list(campaignQuery);
  }

  @Override
  public Campaign saveCampaign(Campaign campaign) {
    if (campaign.getEndDate().compareTo(campaign.getStartDate()) == -1) {
      throw new ValidationException("endDate", "End Date cannot be less than start date");
    }


  }


  private CampaignType getCampaignTypeById(Long campaignTypeId) {
    if (campaignTypeId == null) {
      throw new InvalidParameterException("CAMPAIGN_TYPE_ID_CANNOT_BE_BLANK");
    }
    return (CampaignType) getBaseDao().findUniqueByNamedQueryAndNamedParam("getCampaignTypeById", new String[]{"campaignTypeId"}, new Object[]{campaignTypeId});
  }

  public SearchService getSearchService() {
    return searchService;
  }

  public BaseDao getBaseDao() {
    return baseDao;
  }
}
