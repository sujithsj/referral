package com.ds.core.event;

import com.ds.impl.service.marketing.MarketingMaterialContext;

/**
 * @author adlakha.vaibhav
 */
public class MarketingMaterialImpressionEvent extends MarketingMaterialServeEvent{

  public MarketingMaterialImpressionEvent(Long marketingMaterialTypeId, MarketingMaterialContext marketingMaterialContext) {
    super(marketingMaterialTypeId, marketingMaterialContext);
  }

  public MarketingMaterialImpressionEvent() {
  }
}
