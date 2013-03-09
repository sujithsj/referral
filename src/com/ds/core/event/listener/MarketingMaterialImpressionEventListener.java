package com.ds.core.event.listener;

import com.ds.core.event.Event;
import com.ds.core.event.EventListener;
import com.ds.core.event.MarketingMaterialImpressionEvent;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.marketing.MarketingMaterialType;
import com.ds.domain.tracking.ImpressionTracking;
import com.ds.exception.DSException;
import com.ds.impl.service.marketing.MarketingMaterialContext;
import com.ds.pact.dao.BaseDao;
import com.ds.executor.notification.NotificationRequest;
import com.ds.executor.AppRequestHandler;
import com.ds.constants.EnumNotificationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author adlakha.vaibhav
 */
@Component
public class MarketingMaterialImpressionEventListener implements EventListener {

  private Logger logger = LoggerFactory.getLogger(MarketingMaterialImpressionEventListener.class);


  @Autowired
  private BaseDao baseDao;

  @Override
  public void handleEvent(Event event) {
    ImpressionTracking impressionTracking = null;

    try {

      MarketingMaterialImpressionEvent mmImpressionEvent = (MarketingMaterialImpressionEvent) event;
      MarketingMaterialContext mmContext = mmImpressionEvent.getMarketingMaterialContext();

      MarketingMaterial marketingMaterial = getBaseDao().load(MarketingMaterial.class, mmContext.getMarketingMaterialId());


      impressionTracking = (ImpressionTracking) getBaseDao().findUniqueByNamedQueryAndNamedParam("getAdImpressionForCompanyAffiliateOnDate",
          new String[]{"mmId", "impressionDate", "affId", "companyShortName"},
          new Object[]{mmContext.getMarketingMaterialId(), new Date(),mmContext.getAffiliateId(),marketingMaterial.getCompanyShortName()});

      Affiliate affiliate = getBaseDao().load(Affiliate.class, mmContext.getAffiliateId());
      if (impressionTracking == null) {
        impressionTracking = new ImpressionTracking();


        MarketingMaterialType mmType = getBaseDao().load(MarketingMaterialType.class, mmImpressionEvent.getMarketingMaterialTypeId());
        impressionTracking.setAffiliate(affiliate);
        impressionTracking.setMarketingMaterial(marketingMaterial);
        impressionTracking.setMarketingMaterialType(mmType);
        impressionTracking.setCompanyShortName(marketingMaterial.getCompanyShortName());
        impressionTracking.setCount(1D);
        impressionTracking.setImpressionDate(new Date());
      } else {
        double prevCount = impressionTracking.getCount();
        impressionTracking.setCount(prevCount + 1);//TODO: change this to a single update statement

      }

      if (impressionTracking != null) {
        getBaseDao().save(impressionTracking);
      }

      NotificationRequest notificationRequest = new NotificationRequest();
      notificationRequest.setCompanyShortName(marketingMaterial.getCompanyShortName());
      notificationRequest.setUserId(affiliate.getEmail());
      notificationRequest.setNotificationType(EnumNotificationType.COMPANY_SALE_VIA_AFFILIATE.getId());
      notificationRequest.setParams(new String[]{affiliate.getEmail(), "Rs. 987"});
      AppRequestHandler.handleNotificationRequest(notificationRequest);


    } catch (Throwable t) {
      logger.error("Unable to save click tracking" + impressionTracking, t);
      throw new DSException("UNABLE_TO_SAVE_CLICK_TRACKING");
    }

  }


  public BaseDao getBaseDao() {
    return baseDao;
  }
}
