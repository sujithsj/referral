package com.ds.core.event.listener;

import com.ds.core.event.EventListener;
import com.ds.core.event.Event;
import com.ds.pact.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class MarketingMaterialSaleEventListener implements EventListener{

  private Logger logger = LoggerFactory.getLogger(MarketingMaterialSaleEventListener.class);


  @Autowired
  private BaseDao baseDao;

  @Override
  public void handleEvent(Event event) {
    System.out.println("test");
  }
}
