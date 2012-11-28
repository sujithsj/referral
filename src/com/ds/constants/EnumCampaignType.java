package com.ds.constants;

import com.ds.domain.campaign.CampaignType;

import java.util.Arrays;
import java.util.List;

/**
 * @author adlakha.vaibhav
 *         this is only a logical grouping of campaigns may be to be used only in reporting and no effect on commisions.
 */
public enum EnumCampaignType {

  ALL (-999L,"All"), //to be used only on UI 
  SALE(10L,"Sale"),
  AFFILIATE_SIGN_UP(20L,"Affiliate Sign Up"),
  USER_SIGN_UP(30L,"User Sign up"),
  REGISTRATION(40L,"Registration"),
  EMAIL_OPT_IN(50L,"Email opt in"),
  SOCIAL_MEDIA(60L,"Social Media");


  private java.lang.String type;

  private java.lang.Long id;

  EnumCampaignType(Long id, java.lang.String type) {
    this.type = type;
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public java.lang.Long getId() {
    return id;
  }


  public static List<EnumCampaignType> getAllCampaignTypes() {
    return Arrays.asList(
        EnumCampaignType.SALE,
        EnumCampaignType.AFFILIATE_SIGN_UP,
        EnumCampaignType.USER_SIGN_UP,
        EnumCampaignType.REGISTRATION,
        EnumCampaignType.EMAIL_OPT_IN,
        EnumCampaignType.SOCIAL_MEDIA
    );

  }


  public CampaignType asCampaignType() {
    CampaignType campaignType = new CampaignType();
    campaignType.setId(this.getId());
    campaignType.setType(this.getType());
    return campaignType;
  }

  public static EnumCampaignType getById(Long typeId) {
    for (EnumCampaignType enumCampaignType : EnumCampaignType.values()) {
      if (enumCampaignType.getId().equals(typeId)) {
        return enumCampaignType;
      }
    }
    return null;
  }

}
