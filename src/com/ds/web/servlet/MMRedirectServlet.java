package com.ds.web.servlet;

import com.ds.domain.marketing.MarketingMaterial;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.marketing.MarketingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author adlakha.vaibhav
 */
public class MMRedirectServlet extends HttpServlet {


  private MarketingService marketingService;

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

    String redirectUrl = marketingMaterial.getLandingPageUrl();

    try {
      resp.sendRedirect(redirectUrl);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

  }

  public MarketingService getMarketingService() {
    if (marketingService == null) {
      marketingService = (MarketingService) ServiceLocatorFactory.getService(MarketingService.class);
    }
    return marketingService;
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
