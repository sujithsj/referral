package com.ds.rest.resource;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
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
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.utils.GeneralUtils;
import com.ds.utils.UserAgentParser;
import com.ds.utils.JSONResponseBuilder;
import com.ds.dto.affiliate.AffiliateDTO;

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
    @Autowired
    private AffiliateService affiliateService;


    private CampaignConversionReq getCampaignConversionReqFromHttpReq(HttpServletRequest request) {
        CampaignConversionReq campaignConversionReq = new CampaignConversionReq();
        String rf_c_short_code = request.getParameter("rf_c_short_code");
        String rf_campaign_uid = request.getParameter("rf_campaign_uid");
        String rf_u_email = request.getParameter("rf_u_email");
        String rf_u_f_name = request.getParameter("rf_u_f_name");
        String rf_u_l_name = request.getParameter("rf_u_l_name");

        //TODO: validate mandatory params

        campaignConversionReq.setRf_c_short_code(rf_c_short_code);
        campaignConversionReq.setRf_campaign_uid(rf_campaign_uid);
        campaignConversionReq.setRf_u_email(rf_u_email);
        campaignConversionReq.setRf_u_f_name(rf_u_f_name);
        campaignConversionReq.setRf_u_l_name(rf_u_l_name);

        String revenue = request.getParameter("rf_revenue");

        if (StringUtils.isNotBlank(revenue)) {
            Double rf_revenue = Double.parseDouble(revenue);
            campaignConversionReq.setRf_revenue(rf_revenue);
        }

        String rf_tx_uid = request.getParameter("rf_tx_uid");
        campaignConversionReq.setRf_tx_uid(rf_tx_uid);


        String emailNewReferrer = request.getParameter("rf_email_new_referrer");
        String disableNewReferrer = request.getParameter("rf_disable_new_referrer");
        String autoCreateReferrer = request.getParameter("rf_auto_create");

        if (StringUtils.isNotBlank(emailNewReferrer)) {
            boolean rf_email_new_referrer = Boolean.parseBoolean(emailNewReferrer);
            campaignConversionReq.setRf_email_new_referrer(rf_email_new_referrer);
        }
        if (StringUtils.isNotBlank(disableNewReferrer)) {
            boolean rf_disable_new_referrer = Boolean.parseBoolean(disableNewReferrer);
            campaignConversionReq.setRf_disable_new_referrer(rf_disable_new_referrer);
        }
        if (StringUtils.isNotBlank(autoCreateReferrer)) {
            boolean rf_auto_create = Boolean.parseBoolean(autoCreateReferrer);
            campaignConversionReq.setRf_auto_create(rf_auto_create);
        }


        return campaignConversionReq;

    }

    @GET
    @Path("/track")
    @Produces("application/json")
    public String handleConversionRequest(@Context HttpServletRequest request) {
        CampaignConversionReq campaignConversionReq = getCampaignConversionReqFromHttpReq(request);
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

        if (campaignConversionReq.isRf_auto_create()) {
            AffiliateDTO affiliateDTO = new AffiliateDTO();
            affiliateDTO.setLogin(campaignConversionReq.getRf_u_email());
            affiliateDTO.setEmail(campaignConversionReq.getRf_u_email());
            affiliateDTO.setFirstName(campaignConversionReq.getRf_u_f_name());
            affiliateDTO.setFirstName(campaignConversionReq.getRf_u_l_name());

            //TOD: change this
            if (campaignConversionReq.isRf_disable_new_referrer()) {
                affiliateDTO.setDeleted(true);
            } else {
                affiliateDTO.setDeleted(false);
            }

            //TODO: set aff pwd

            getAffiliateService().signupAffiliate(affiliateDTO, campaignConversionReq.getRf_c_short_code(), true);

            if (campaignConversionReq.isRf_email_new_referrer()) {
                //todo: send email with credentials
            }
        }

        return new JSONResponseBuilder().addField("test", "test2").build();


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

    public AffiliateService getAffiliateService() {
        return affiliateService;
    }
}
