package com.ds.pact.service.campaign;

import com.ds.web.action.Page;
import com.ds.domain.campaign.Campaign;

/**
 * @author adlakha.vaibhav
 */
public interface CampaignService {

  public Page searchCampaign(String name, String companyShortName, int pageNo, int perPage);

  public Campaign getCampaignById(Long campaignId);
}
