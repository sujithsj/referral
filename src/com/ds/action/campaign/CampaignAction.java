package com.ds.action.campaign;

import com.ds.domain.campaign.Campaign;
import com.ds.domain.user.User;
import com.ds.dto.campaign.CampaignDTO;
import com.ds.dto.commission.CommissionPlanDTO;
import com.ds.pact.service.campaign.CampaignService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class CampaignAction extends BaseAction {

  private Long campaignId;

  private CampaignDTO campaignDTO;
  private CommissionPlanDTO commissionPlanDTO;

  @Autowired
  private CampaignService campaignService;

  /*private List<CampaignType> allCampaignTypes;*/


  @DefaultHandler
  public Resolution createOrEditCampaign() {
    if (campaignId != null) {
      Campaign campaign = getCampaignService().getCampaignById(campaignId);
      this.campaignDTO = new CampaignDTO(campaign);
      this.commissionPlanDTO = new CommissionPlanDTO(campaign.getCommissionPlan());
    } else {
      campaignDTO = new CampaignDTO();
      commissionPlanDTO = new CommissionPlanDTO();
    }

    return new ForwardResolution("/pages/campaign/campaignCrud.jsp");
  }


  public Resolution saveCampaign() {
    Campaign campaign;

    if (campaignId != null) {
      campaign = getCampaignService().updateCampaign(campaignId, commissionPlanDTO, campaignDTO);
    } else {
      User loggedInUser = SecurityHelper.getLoggedInUser();
      String companyShortName = loggedInUser.getCompanyShortName();
      campaign = getCampaignService().createCampaign(campaignDTO, commissionPlanDTO, companyShortName);
    }

    if (campaign != null) {
      addRedirectAlertMessage(new SimpleMessage("Campaign updated successfully.Please upload a banner"));
      return new RedirectResolution(CampaignAction.class, "createOrEditCampaign").addParameter("campaignId", campaign.getId());
    } else {
      addRedirectAlertMessage(new SimpleMessage("Could not find/create campaign"));
      return new RedirectResolution(CampaignAction.class, "createOrEditCampaign");
    }
  }

  public CampaignService getCampaignService() {
    return campaignService;
  }

  public CampaignDTO getCampaignDTO() {
    return campaignDTO;
  }

  public void setCampaignDTO(CampaignDTO campaignDTO) {
    this.campaignDTO = campaignDTO;
  }

  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }


  public CommissionPlanDTO getCommissionPlanDTO() {
    return commissionPlanDTO;
  }

  public void setCommissionPlanDTO(CommissionPlanDTO commissionPlanDTO) {
    this.commissionPlanDTO = commissionPlanDTO;
  }
}
