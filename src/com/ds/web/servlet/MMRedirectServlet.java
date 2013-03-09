package com.ds.web.servlet;

import com.ds.core.event.EventDispatcher;
import com.ds.core.event.MarketingMaterialServeEvent;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.visitor.VisitorInfo;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.impl.service.marketing.MarketingMaterialContext;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.utils.GeneralUtils;
import com.ds.utils.UserAgentParser;
import com.ds.constants.AppConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class MMRedirectServlet extends HttpServlet {


  private MarketingService marketingService;
  private EventDispatcher eventDispatcher;
  private AffiliateService affiliateService;
  private AdminService adminService;

  protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
    String requestURI = request.getRequestURI();

    //http://dev.healthkart.com/mm/2/999

    //TODO: do some try catch handling here
    String subRequest = requestURI.substring(requestURI.indexOf("/mmr/") + "/mmr/".length());
    String idArr[] = subRequest.split("/");

    Long marketingMaterialId = Long.parseLong(idArr[0]);
    Long affiliateId = Long.parseLong(idArr[1]);

    //TODO: make an entry for redirect thru affiliate
    MarketingMaterial marketingMaterial = getMarketingService().getMarektingMaterialById(marketingMaterialId);
    Affiliate affiliate = getAffiliateService().getAffiliate(affiliateId);
    VisitorInfo visitorInfo = getVisitorInfoFromRequest(request, marketingMaterial);

    getAdminService().saveOrUpdateEntity(visitorInfo);

    MarketingMaterialContext marketingMaterialContext = new MarketingMaterialContext(marketingMaterial.getId(), visitorInfo.getId(), affiliate.getId());

    getEventDispatcher().dispatchEvent(new MarketingMaterialServeEvent(marketingMaterial.getMarketingMaterialType().getId(), marketingMaterialContext));


    //TODO: replace this  campign id hard coding
    String valueInCoookie = "2" + "#" + marketingMaterial.getId() + "#" + affiliate.getId();
    //TODO: remove this hard coding of cookie name and separator in cookie
    Cookie cookie1 = new Cookie(AppConstants.CONVERSION_TRACK_COOKIE_NAME, valueInCoookie);

    cookie1.setPath("/");
    //TODO: remove hard coding of domain name
    cookie1.setDomain(AppConstants.APP_URL);
    cookie1.setMaxAge(3600);
    resp.addCookie(cookie1);

    String redirectUrl = marketingMaterial.getLandingPageUrl();

    //try {
    resp.setStatus(302);
    resp.setHeader("Location", redirectUrl);
    resp.setHeader("Connection", "close");


    //resp.sendRedirect(redirectUrl);
    // }
    /*catch (IOException ioe) {
      ioe.printStackTrace();
    }*/

  }


  public VisitorInfo getVisitorInfoFromRequest(HttpServletRequest request, MarketingMaterial marketingMaterial) {


    VisitorInfo visitorInfo = new VisitorInfo();
    visitorInfo.setOperation("AdRedirect");
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

  public AffiliateService getAffiliateService() {
    if (affiliateService == null) {
      affiliateService = (AffiliateService) ServiceLocatorFactory.getService(AffiliateService.class);
    }
    return affiliateService;
  }

  public AdminService getAdminService() {
    if (adminService == null) {
      adminService = (AdminService) ServiceLocatorFactory.getService(AdminService.class);
    }
    return adminService;
  }

  public static void main(String[] args) {
    String requestURI = "http://dev.healthkart.com/mmr/2/999";

    String subRequest = requestURI.substring(requestURI.indexOf("/mmr/") + "/mmr/".length());
    String idArr[] = subRequest.split("/");

    Long marketingMaterialId = Long.parseLong(idArr[0]);
    Long affiliateId = Long.parseLong(idArr[1]);
    System.out.println(marketingMaterialId + ": " + affiliateId);

  }


}
