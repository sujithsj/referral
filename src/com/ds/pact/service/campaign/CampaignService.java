package com.ds.pact.service.campaign;

import com.ds.web.action.Page;
import com.ds.domain.campaign.Campaign;

import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public interface CampaignService {


  public Page searchCampaign(String name, String companyShortName, Date startDate, Date endDate, Long campaignTypeId, boolean active, int pageNo, int perPage);

  public Campaign getCampaignById(Long campaignId);

  //public Campaign saveCampaign(CampaignDTO campaignDTO);
}
