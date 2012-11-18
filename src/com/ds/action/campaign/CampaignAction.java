package com.ds.action.campaign;

import com.ds.domain.campaign.Campaign;
import com.ds.dto.commission.CommissionPlanDTO;
import com.ds.pact.service.campaign.CampaignService;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
@Component
public class CampaignAction extends BaseAction {

  private String name;
  private Date startDate, endDate;
  private boolean visibleToAll;

  private Long campaignId;
  private Long campaignTypeId;

  private CommissionPlanDTO commissionPlanDTO;

  @Autowired
  private CampaignService campaignService;

  /*private List<CampaignType> allCampaignTypes;*/


  @DefaultHandler
  public Resolution createOrEditMarketingMaterial() {
    if (campaignId != null) {
      Campaign campaign = getCampaignService().getCampaignById(campaignId);
      this.name = campaign.getName();
      this.startDate = campaign.getStartDate();
      this.endDate = campaign.getEndDate();
      this.visibleToAll = campaign.isPrivate();
      this.campaignTypeId = campaign.getCampaignType().getId();
      this.commissionPlanDTO = new CommissionPlanDTO(campaign.getCommissionPlan());
    }

    return new ForwardResolution("/pages/campaign/campaignCrud.jsp");
  }


  public Resolution saveCampaign() {
    Campaign campaign;

    if (campaignId != null) {
      campaign = getCampaignService().getCampaignById(campaignId);
    } else {
      campaign = new Campaign();
    }

    /*if (campaign != null) {

      User loggedInUser = SecurityHelper.getLoggedInUser();
      String companyShortName = loggedInUser.getCompanyShortName();

      
      marketingMaterial.setBody(body);
      marketingMaterial.setCompanyShortName(companyShortName);
      marketingMaterial.setLandingPageUrl(landingPageURL);
      marketingMaterial.setTitle(title);
      MarketingMaterialType marketingMaterialType = EnumMarketingMaterialType.getById(type).asMarketingMaterialType();
      marketingMaterial.setMarketingMaterialType(marketingMaterialType);

      marketingMaterial = getMarketingService().saveMarketingMaterial(marketingMaterial);

      addRedirectAlertMessage(new SimpleMessage("Changes saved successfully.Please upload a banner"));
      return new RedirectResolution(MarketingMaterialAction.class, "createOrEditMarketingMaterial").addParameter("marketingMaterialId", marketingMaterial.getId());
    } else {
      addRedirectAlertMessage(new SimpleMessage("Could not find/create marketing material"));
      return new RedirectResolution(MarketingMaterialAction.class, "createOrEditMarketingMaterial");
    }*/

    return null;
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

  public boolean isVisibleToAll() {
    return visibleToAll;
  }

  public void setVisibleToAll(boolean visibleToAll) {
    this.visibleToAll = visibleToAll;
  }


  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }

  public Long getCampaignTypeId() {
    return campaignTypeId;
  }

  public void setCampaignTypeId(Long campaignTypeId) {
    this.campaignTypeId = campaignTypeId;
  }

  public CommissionPlanDTO getCommissionPlanDTO() {
    return commissionPlanDTO;
  }

  public void setCommissionPlanDTO(CommissionPlanDTO commissionPlanDTO) {
    this.commissionPlanDTO = commissionPlanDTO;
  }
}
