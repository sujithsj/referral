package com.ds.core.event.listener;

import com.ds.core.event.Event;
import com.ds.core.event.EventListener;
import com.ds.core.event.MarketingMaterialServeEvent;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.campaign.Campaign;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.marketing.MarketingMaterialType;
import com.ds.domain.tracking.ClickTracking;
import com.ds.domain.visitor.VisitorInfo;
import com.ds.impl.service.marketing.MarketingMaterialContext;
import com.ds.pact.dao.BaseDao;
import com.ds.exception.DSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */

@Component
public class MarketingMaterialServeEventListener implements EventListener {

    private Logger logger = LoggerFactory.getLogger(MarketingMaterialServeEventListener.class);

    @Autowired
    private BaseDao baseDao;

    @Override
    public void handleEvent(Event event) {
        ClickTracking clickTracking = new ClickTracking();
        try {

            MarketingMaterialServeEvent mmServeEvent = (MarketingMaterialServeEvent) event;
            MarketingMaterialContext mmContext = mmServeEvent.getMarketingMaterialContext();


            MarketingMaterial marketingMaterial = getBaseDao().load(MarketingMaterial.class, mmContext.getMarketingMaterialId());
            Affiliate affiliate = getBaseDao().load(Affiliate.class, mmContext.getAffiliateId());
            MarketingMaterialType mmType = getBaseDao().load(MarketingMaterialType.class, mmServeEvent.getMarketingMaterialTypeId());
            VisitorInfo visitorInfo = getBaseDao().load(VisitorInfo.class, mmContext.getVisitorInfoId());


            
            //Campaign campaign = getBaseDao().get(Campaign.class, 2L);

            Campaign campaign = marketingMaterial.getCampaign();

            clickTracking.setMarketingMaterial(marketingMaterial);
            clickTracking.setAffiliate(affiliate);
            clickTracking.setMarketingMaterialType(mmType);
            clickTracking.setVisitorInfo(visitorInfo);
            clickTracking.setCampaign(campaign);
            clickTracking.setCompanyShortName(marketingMaterial.getCompanyShortName());

            getBaseDao().save(clickTracking);

        } catch (Throwable t) {
            logger.error("Unable to save click tracking" + clickTracking, t);
            throw new DSException("UNABLE_TO_SAVE_CLICK_TRACKING");
        }


    }

    public BaseDao getBaseDao() {
        return baseDao;
    }
}
