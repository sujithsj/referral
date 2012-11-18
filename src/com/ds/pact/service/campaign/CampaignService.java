package com.ds.pact.service.campaign;

import com.ds.domain.campaign.Campaign;
import com.ds.dto.campaign.CampaignDTO;
import com.ds.dto.commission.CommissionPlanDTO;
import com.ds.web.action.Page;

import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public interface CampaignService {


  public Page searchCampaign(String name, String companyShortName, Date startDate, Date endDate, Long campaignTypeId, boolean active, int pageNo, int perPage);

  public Campaign getCampaignById(Long campaignId);

  public Campaign createCampaign(CampaignDTO campaignDTO, CommissionPlanDTO commissionPlanDTO, String companyShortName);

  public Campaign updateCampaign(Long campaignId, CommissionPlanDTO commissionPlanDTO, CampaignDTO campaignDTO);

}
