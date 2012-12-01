package com.ds.impl.service.marketing;

import com.ds.core.event.SmartSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public class MarketingMaterialContext implements SmartSerializable {

  private Long marketingMaterialId;
  private Long visitorInfoId;
  private Long affiliateId;

  public MarketingMaterialContext() {
  }

  public MarketingMaterialContext(Long marketingMaterialId, Long visitorInfoId, Long affiliateId) {
    this.marketingMaterialId = marketingMaterialId;
    this.visitorInfoId = visitorInfoId;
    this.affiliateId = affiliateId;
  }

  public Long getMarketingMaterialId() {
    return marketingMaterialId;
  }

  public void setMarketingMaterialId(Long marketingMaterialId) {
    this.marketingMaterialId = marketingMaterialId;
  }

  public Long getVisitorInfoId() {
    return visitorInfoId;
  }

  public void setVisitorInfoId(Long visitorInfoId) {
    this.visitorInfoId = visitorInfoId;
  }

  public Long getAffiliateId() {
    return affiliateId;
  }

  public void setAffiliateId(Long affiliateId) {
    this.affiliateId = affiliateId;
  }

  @Override
  public Map<String, String> getWireRepresentation() {
    Map<String, String> data = new HashMap<String, String>();

    data.put("marketingMaterialId", marketingMaterialId.toString());
    data.put("affiliateId", affiliateId.toString());
    data.put("visitorInfoId", visitorInfoId.toString());

    return data;
  }

  @Override
  public void prepareFromWireRepresentation(Map<String, String> values) {
    this.marketingMaterialId = Long.parseLong(values.get("marketingMaterialId"));
    this.affiliateId = Long.parseLong(values.get("affiliateId"));
    this.visitorInfoId = Long.parseLong(values.get("visitorInfoId"));
  }
}
