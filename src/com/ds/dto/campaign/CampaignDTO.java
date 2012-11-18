package com.ds.dto.campaign;

import com.ds.domain.campaign.Campaign;

import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
public class CampaignDTO {

  private String name;
  private String description;
  private Date startDate, endDate;
  private Boolean visibleToAll;
  private Long campaignTypeId;


  public CampaignDTO(Campaign campaign) {
    this.name = campaign.getName();
    this.description = campaign.getDescription();
    this.startDate = campaign.getStartDate();
    this.endDate = campaign.getEndDate();
    this.visibleToAll = campaign.isPrivate();
    this.campaignTypeId = campaign.getCampaignType().getId();
  }


  public void syncToCampaign(Campaign campaign) {
    campaign.setName(this.name);
    campaign.setDescription(this.description);
    campaign.setStartDate(this.startDate);
    campaign.setEndDate(this.endDate);
    campaign.setPrivate(this.visibleToAll);
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

  public Boolean isVisibleToAll() {
    return visibleToAll;
  }

  public void setVisibleToAll(Boolean visibleToAll) {
    this.visibleToAll = visibleToAll;
  }

  public Long getCampaignTypeId() {
    return campaignTypeId;
  }

  public void setCampaignTypeId(Long campaignTypeId) {
    this.campaignTypeId = campaignTypeId;
  }
}
