package com.ds.rest.resource;

import com.ds.constants.EnumMarketingMaterialType;
import com.ds.domain.company.Company;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.exception.InvalidParameterException;
import com.ds.impl.service.marketing.MMTemplateBuilder;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.utils.JSONResponseBuilder;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.io.IOException;

/**
 * @author adlakha.vaibhav
 */
@Path("/mm")
@NoCache
@Component
public class MarketingMaterialResource {

  //TODO: neeed to build this from company like pricemia.zferal.com  
  private static final String BASE_URL = "dev.healthkart.com";

  @Autowired
  private MarketingService marketingService;
  @Autowired
  private AdminService adminService;


  

  @GET
  @Path("/{mmId}/share/{affiliateId}")
  @Produces("application/json")
  public String getSharingCode(@PathParam("mmId")
  Long marketingMaterialId, @PathParam("affiliateId")
  Long affiliateId) {

    //TODO: can log here which all affiliates looked at sharing code for an ad for a company.
    validateAdParams(marketingMaterialId, affiliateId);
    String sharingCode = null;

    MarketingMaterial marketingMaterial = getMarketingService().getMarektingMaterialById(marketingMaterialId);

    //TODO: handle null marketing material
    Long mmTypeId = marketingMaterial.getMarketingMaterialType().getId();

    if (EnumMarketingMaterialType.Banner.getId().equals(mmTypeId)) {
      if (marketingMaterial.getImage() != null) {
        sharingCode = MMTemplateBuilder.getSharingCodeForBanner(BASE_URL, marketingMaterial.getId(), affiliateId, marketingMaterial.getImage().getId());
      }
    }
    if (EnumMarketingMaterialType.TextAd.getId().equals(mmTypeId)) {
      Company company = getAdminService().getCompany(marketingMaterial.getCompanyShortName());
      sharingCode = MMTemplateBuilder.getSharingCodeForTextAd(BASE_URL, marketingMaterial.getId(), affiliateId, marketingMaterial.getTitle(), marketingMaterial.getBody(), company.getName());
    }


    if (StringUtils.isBlank(sharingCode)) {
      return new JSONResponseBuilder().addField("exception", false).addField("sc", "No Sharing code could be loaded for this ad").build();
    } else {
      return new JSONResponseBuilder().addField("exception", false).addField("sc", sharingCode).build();
    }
  }

  private void validateAdParams(Long marketingMaterialId, Long affiliateId) {
    if (marketingMaterialId == null) {
      throw new InvalidParameterException("MM_ID_CANNOT_BE_BLANK");
    }
    if (affiliateId == null) {
      throw new InvalidParameterException("AFF_ID_CANNOT_BE_BLANK");
    }
  }

  @GET
  @Path("/js/{mmId}/{affiliateId}")
  public String serverAdThroughJS(@PathParam("mmId")
  Long marketingMaterialId, @PathParam("affiliateId")
  Long affiliateId) {

    //TODO: count ad impressions here 
    validateAdParams(marketingMaterialId, affiliateId);

    String jsContent = "";

    MarketingMaterial marketingMaterial = getMarketingService().getMarektingMaterialById(marketingMaterialId);

    //TODO: handle null marketingmaterial
    Long mmTypeId = marketingMaterial.getMarketingMaterialType().getId();

    if (EnumMarketingMaterialType.Banner.getId().equals(mmTypeId)) {
      if (marketingMaterial.getImage() != null) {
        jsContent = MMTemplateBuilder.getBannerByJS(BASE_URL, marketingMaterial.getId(), affiliateId, marketingMaterial.getImage().getId());
      }
    }

    if (EnumMarketingMaterialType.TextAd.getId().equals(mmTypeId)) {
      Company company = getAdminService().getCompany(marketingMaterial.getCompanyShortName());
      jsContent = MMTemplateBuilder.getTextAdByJS(BASE_URL, marketingMaterial.getId(), affiliateId, marketingMaterial.getTitle(), marketingMaterial.getBody(), company.getName());
    }

    return jsContent;

  }

  public MarketingService getMarketingService() {
    return marketingService;
  }

  public AdminService getAdminService() {
    return adminService;
  }
}
