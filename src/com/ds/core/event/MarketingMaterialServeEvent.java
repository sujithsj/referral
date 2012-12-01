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
public class MarketingMaterialServeEvent implements AsyncEvent {

  private Logger logger = LoggerFactory.getLogger(EmailEvent.class);

  private Long marketingMaterialTypeId;
  private MarketingMaterialContext marketingMaterialContext;


  public MarketingMaterialServeEvent(Long marketingMaterialTypeId, MarketingMaterialContext marketingMaterialContext) {
    this.marketingMaterialTypeId = marketingMaterialTypeId;
    this.marketingMaterialContext = marketingMaterialContext;
  }

  public MarketingMaterialServeEvent() {

  }


  public Long getMarketingMaterialTypeId() {
    return marketingMaterialTypeId;
  }

  public void setMarketingMaterialTypeId(Long marketingMaterialTypeId) {
    this.marketingMaterialTypeId = marketingMaterialTypeId;
  }

  public MarketingMaterialContext getMarketingMaterialContext() {
    return marketingMaterialContext;
  }

  public void setMarketingMaterialContext(MarketingMaterialContext marketingMaterialContext) {
    this.marketingMaterialContext = marketingMaterialContext;
  }

  @Override
  public Map<String, String> getWireRepresentation() {
    Map<String, String> data = new HashMap<String, String>();
    data.putAll(marketingMaterialContext.getWireRepresentation());
    data.put("EventType", marketingMaterialTypeId.toString());
    data.put("ContextClass", marketingMaterialContext.getClass().getName());
    return data;
  }

  @Override
  public void prepareFromWireRepresentation(Map<String, String> data) {
     this.marketingMaterialTypeId = Long.parseLong(data.get("EventType"));
    try {
      Class marketingMaterialClass = Class.forName(data.get("ContextClass"));
      this.marketingMaterialContext = (MarketingMaterialContext) marketingMaterialClass.newInstance();
      this.marketingMaterialContext.prepareFromWireRepresentation(data);
    } catch (Exception e) {
      logger.error("ERROR_IN_DESERIALIZATION", e);
      throw new DSException("ERROR_IN_DESERIALIZATION", e);
    }
  }
}
