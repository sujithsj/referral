package com.ds.web.servlet;

import com.ds.core.event.EventDispatcher;
import com.ds.core.event.MarketingMaterialSaleEvent;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.visitor.VisitorInfo;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.impl.service.marketing.MarketingMaterialContext;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.utils.GeneralUtils;
import com.ds.utils.UserAgentParser;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class EventTrackingServlet extends HttpServlet {

  private EventDispatcher eventDispatcher;
  private MarketingService marketingService;
  private AdminService adminService;

  protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
    /**
     * http://healthkart.dolusmia.com/ets?rev=$100&cid=123&uid=abc
     */

    Cookie[] allCookies = request.getCookies();
    Cookie trackCookie = null;
    for (Cookie cookie : allCookies) {
      if ("_track".equalsIgnoreCase(cookie.getName())) {
        trackCookie = cookie;
        break;
      }
    }

    if (trackCookie != null) {
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

          Double revenue = Double.parseDouble(request.getParameter("rev"));
          String uniqueId = request.getParameter("uid");
          String coustomerId = request.getParameter("cid");

          getEventDispatcher().dispatchEvent(new MarketingMaterialSaleEvent(marketingMaterial.getMarketingMaterialType().getId(), marketingMaterialContext, revenue, campaignId, coustomerId, uniqueId));


        }
      } catch (Exception e) {
        //TODO: add logger
        e.printStackTrace();
      }

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


  public MarketingService getMarketingService() {
    if (marketingService == null) {
      marketingService = (MarketingService) ServiceLocatorFactory.getService(MarketingService.class);
    }
    return marketingService;
  }


  public EventDispatcher getEventDispatcher() {
    if (this.eventDispatcher == null) {
      this.eventDispatcher = (EventDispatcher) ServiceLocatorFactory.getService("EventDispatcher");
    }
    return eventDispatcher;
  }

  public AdminService getAdminService() {
    if (adminService == null) {
      adminService = ServiceLocatorFactory.getService(AdminService.class);
    }
    return adminService;

  }
}
