package com.ds.core.event;

import com.ds.exception.DSException;
import com.ds.impl.service.marketing.MarketingMaterialContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public class MarketingMaterialSaleEvent extends MarketingMaterialServeEvent {

  private Logger logger = LoggerFactory.getLogger(MarketingMaterialSaleEvent.class);

  private Long campaignId;
  private Double revenue;
  private String uniqueId;
  private String customerId;

  public MarketingMaterialSaleEvent(Long marketingMaterialTypeId, MarketingMaterialContext marketingMaterialContext, Double revenue, Long campaignId, String customerId, String uniqueId) {
    super(marketingMaterialTypeId, marketingMaterialContext);
    this.revenue = revenue;
    this.uniqueId = uniqueId;
    this.campaignId = campaignId;
    this.customerId = customerId;

  }

  public MarketingMaterialSaleEvent() {

  }

  public Double getRevenue() {
    return revenue;
  }

  public void setRevenue(Double revenue) {
    this.revenue = revenue;
  }

  public String getUniqueId() {
    return uniqueId;
  }


  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }

  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  @Override
  public Map<String, String> getWireRepresentation() {
    Map<String, String> data = new HashMap<String, String>();
    data.putAll(super.getWireRepresentation());
    data.put("revenue", revenue.toString());
    data.put("uniqueId", uniqueId);
    data.put("campaignId", campaignId.toString());
    data.put("customerId", customerId);
    return data;
  }

  @Override
  public void prepareFromWireRepresentation(Map<String, String> data) {

    try {
      super.prepareFromWireRepresentation(data);
      this.revenue = Double.parseDouble(data.get("revenue"));
      this.uniqueId = data.get("uniqueId");
      this.customerId = data.get("customerId");
      this.campaignId = Long.parseLong(data.get("campaignId"));
    } catch (Exception e) {
      logger.error("ERROR_IN_DESERIALIZATION", e);
      throw new DSException("ERROR_IN_DESERIALIZATION", e);
    }
  }
}
