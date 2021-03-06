package com.ds.core.event.listener;

import com.ds.core.event.Event;
import com.ds.core.event.EventListener;
import com.ds.core.event.MarketingMaterialSaleEvent;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.campaign.Campaign;
import com.ds.domain.commission.CommissionEarning;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.marketing.MarketingMaterialType;
import com.ds.domain.tracking.EventTracking;
import com.ds.domain.visitor.VisitorInfo;
import com.ds.exception.DSException;
import com.ds.impl.service.marketing.MarketingMaterialContext;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.commission.CommissionEarningService;
import com.ds.pact.service.commission.EventTrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class MarketingMaterialSaleEventListener implements EventListener {

  private Logger logger = LoggerFactory.getLogger(MarketingMaterialSaleEventListener.class);


  @Autowired
  private BaseDao baseDao;
  @Autowired
  private EventTrackingService eventTrackingService;
  @Autowired
  private CommissionEarningService commissionEarningService;


  @Override
  public void handleEvent(Event event) {
    EventTracking eventTracking = new EventTracking();

    try {

      MarketingMaterialSaleEvent mmSaleEvent = (MarketingMaterialSaleEvent) event;
      MarketingMaterialContext mmContext = mmSaleEvent.getMarketingMaterialContext();


      MarketingMaterial marketingMaterial = getBaseDao().load(MarketingMaterial.class, mmContext.getMarketingMaterialId());
      Affiliate affiliate = getBaseDao().load(Affiliate.class, mmContext.getAffiliateId());
      MarketingMaterialType mmType = getBaseDao().load(MarketingMaterialType.class, mmSaleEvent.getMarketingMaterialTypeId());
      VisitorInfo visitorInfo = getBaseDao().load(VisitorInfo.class, mmContext.getVisitorInfoId());


      //Long campaignId = 2L;
      //Long campaignId = marketingMaterial.getCampaign().getId();
      //Campaign campaign = getBaseDao().get(Campaign.class, campaignId);

      Campaign campaign = marketingMaterial.getCampaign();

      eventTracking.setRevenue(mmSaleEvent.getRevenue());
      eventTracking.setCustomerId(mmSaleEvent.getCustomerId());
      eventTracking.setUniqueId(mmSaleEvent.getUniqueId());
      eventTracking.setMarketingMaterial(marketingMaterial);
      eventTracking.setAffiliate(affiliate);
      eventTracking.setMarketingMaterialType(mmType);
      eventTracking.setVisitorInfo(visitorInfo);
      eventTracking.setCampaign(campaign);
      eventTracking.setCompanyShortName(marketingMaterial.getCompanyShortName());

      eventTracking = getEventTrackingService().saveEventTracking(eventTracking);


      /**
       * now that we have captured event tracking , we will capture the commission earning , may be we can
       * do this via quartz.
       */


      /**
       * see if this affiliate already got a commission for this marketing material
       *
       */

      CommissionEarning commissionEarning = new MMCommissionProcessor(eventTracking.getRevenue(), marketingMaterial.getId(), affiliate, campaign).process();

      if (commissionEarning != null) {
        commissionEarning.setMarketingMaterial(marketingMaterial);
        commissionEarning.setEventTracking(eventTracking);
        getCommissionEarningService().saveCommissionEarning(commissionEarning);
      }


    } catch (Throwable t) {
      logger.error("Unable to save sale event tracking" + eventTracking, t);
      throw new DSException("UNABLE_TO_SAVE_SALE_EVENT_TRACKING");
    }

  }

  public BaseDao getBaseDao() {
    return baseDao;
  }

  public EventTrackingService getEventTrackingService() {
    return eventTrackingService;
  }

  public CommissionEarningService getCommissionEarningService() {
    return commissionEarningService;
  }
}
