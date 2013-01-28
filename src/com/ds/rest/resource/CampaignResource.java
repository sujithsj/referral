package com.ds.rest.resource;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

import com.ds.rest.dto.CampaignConversionReq;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.visitor.VisitorInfo;
import com.ds.impl.service.marketing.MarketingMaterialContext;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.core.event.MarketingMaterialSaleEvent;
import com.ds.core.event.EventDispatcher;
import com.ds.constants.AppConstants;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.pact.service.admin.AdminService;
import com.ds.utils.GeneralUtils;
import com.ds.utils.UserAgentParser;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 28, 2013
 * Time: 8:30:28 PM
 * To change this template use File | Settings | File Templates.
 */

@Path("/cr")
@NoCache
@Component
public class CampaignResource {


    private EventDispatcher eventDispatcher;
    @Autowired
    private MarketingService marketingService;
    @Autowired
    private AdminService adminService;

    @POST
    @Path("/track")
    @Produces("application/json")
    public void handleConversionRequest(CampaignConversionReq campaignConversionReq, @Context HttpServletRequest request) {
        Cookie[] allCookies = request.getCookies();
        Cookie trackCookie = null;
        for (Cookie cookie : allCookies) {
            if (AppConstants.CONVERSION_TRACK_COOKIE_NAME.equalsIgnoreCase(cookie.getName())) {
                trackCookie = cookie;
                break;
            }
        }


        if (trackCookie != null) {
            trackCommissionForSale(trackCookie, campaignConversionReq, request);
        }

        //todo : create new affiliate 
    }


    public void trackCommissionForSale(Cookie trackCookie, CampaignConversionReq campaignConversionReq, HttpServletRequest request) {
        try {
            String trackingValue = trackCookie.getValue();

            if (StringUtils.isNotBlank(trackingValue)) {
                String trackerVal[] = trackingValue.split("#");

                Long campaignId = Long.parseLong(trackerVal[0]);
                Long marketingMaterialId = Long.parseLong(trackerVal[1]);
                Long affiliateId = Long.parseLong(trackerVal[2]);


                MarketingMaterial marketingMaterial = getMarketingService().getMarektingMaterialById(marketingMaterialId);
                VisitorInfo visitorInfo = getVisitorInfoFromRequest(request, marketingMaterial);
                getAdminService().saveOrUpdateEntity(visitorInfo);

                MarketingMaterialContext marketingMaterialContext = new MarketingMaterialContext(marketingMaterialId, visitorInfo.getId(), affiliateId);

                Double revenue = campaignConversionReq.getRf_revenue();
                String uniqueId = campaignConversionReq.getRf_tx_uid();
                String coustomerId = campaignConversionReq.getRf_uid();

                getEventDispatcher().dispatchEvent(new MarketingMaterialSaleEvent(marketingMaterial.getMarketingMaterialType().getId(), marketingMaterialContext, revenue, campaignId, coustomerId, uniqueId));


            }
        } catch (Exception e) {
            //TODO: add logger
            e.printStackTrace();
        }
    }


    public VisitorInfo getVisitorInfoFromRequest(HttpServletRequest request, MarketingMaterial marketingMaterial) {


        VisitorInfo visitorInfo = new VisitorInfo();
        visitorInfo.setOperation("Sale");
        visitorInfo.setEntityId(marketingMaterial.getId().toString());
        /*User user = SecurityHelper.getLoggedInUserOrAnonymousUser();
        visitorInfo.setUserName(user.getUsername());*/

        visitorInfo.setCompanyShortName(marketingMaterial.getCompanyShortName());
        visitorInfo.setEntity("MarketingMaterial");

        String remoteAddr = request.getRemoteAddr();
        visitorInfo.setIpAddress(remoteAddr);

        visitorInfo.setHostName(GeneralUtils.getHeaderElementFromReq(request, "Host"));


        /*  visitorInfo.setSearchQuery(query[0]);*/
        visitorInfo.setRefererURL(GeneralUtils.getRefererURL(request));


        String userAgent = request.getHeader("user-agent");
        List<String> result = UserAgentParser.parseUserAgent(userAgent);

        visitorInfo.setBrowserName(result.get(0));
        visitorInfo.setBrowserVersion(result.get(1));
        visitorInfo.setOsName(result.get(2));
        visitorInfo.setOsVersion(result.get(3));

        String httpMethod = request.getMethod();
        visitorInfo.setHttpMethodType(httpMethod);

        visitorInfo.setVisitorId("testId" + System.nanoTime()); //TODO: need to remove this once we start getting actual visitorId's i.e. utmAA value.

        return visitorInfo;
    }

    public EventDispatcher getEventDispatcher() {
        if (this.eventDispatcher == null) {
            this.eventDispatcher = (EventDispatcher) ServiceLocatorFactory.getService("EventDispatcher");
        }
        return eventDispatcher;
    }

    public MarketingService getMarketingService() {
        return marketingService;
    }

    public AdminService getAdminService() {
        return adminService;
    }
}
