package com.ds.rest.resource;

import com.ds.constants.EnumMarketingMaterialType;
import com.ds.core.event.EventDispatcher;
import com.ds.core.event.MarketingMaterialImpressionEvent;
import com.ds.domain.company.Company;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.exception.InvalidParameterException;
import com.ds.impl.service.marketing.MMTemplateBuilder;
import com.ds.impl.service.marketing.MarketingMaterialContext;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.utils.JSONResponseBuilder;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
  @Autowired
  private EventDispatcher eventDispatcher;


  @GET
  @Path("/{mmId}/preview/")
  @Produces("application/json")
  public String getPreview(@PathParam("mmId")
  Long marketingMaterialId) {
    String previewCode = null;

    MarketingMaterial marketingMaterial = getMarketingService().getMarektingMaterialById(marketingMaterialId);

    Long mmTypeId = marketingMaterial.getMarketingMaterialType().getId();

    if (EnumMarketingMaterialType.Banner.getId().equals(mmTypeId)) {
      if (marketingMaterial.getImage() != null) {
        previewCode = MMTemplateBuilder.getPreviewForBanner(BASE_URL, marketingMaterial.getId(), marketingMaterial.getTitle(), marketingMaterial.getImage().getId());
      }
    }
    if (EnumMarketingMaterialType.TextAd.getId().equals(mmTypeId)) {
      Company company = getAdminService().getCompany(marketingMaterial.getCompanyShortName());
      previewCode = MMTemplateBuilder.getPreviewForTextAd(BASE_URL, marketingMaterial.getId(), marketingMaterial.getTitle(), marketingMaterial.getBody(), company.getName());
    }


    if (StringUtils.isBlank(previewCode)) {
      return new JSONResponseBuilder().addField("exception", false).addField("sc", "No Preview could be loaded for this ad").build();
    } else {
      return new JSONResponseBuilder().addField("exception", false).addField("sc", previewCode).build();
    }
  }

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
        sharingCode = MMTemplateBuilder.getSharingCodeForBanner(BASE_URL, marketingMaterial.getId(), affiliateId, marketingMaterial.getTitle(), marketingMaterial.getImage().getId());
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
        jsContent = MMTemplateBuilder.getBannerByJS(BASE_URL, marketingMaterial.getId(), affiliateId, marketingMaterial.getTitle(), marketingMaterial.getImage().getId());
      }
    }

    if (EnumMarketingMaterialType.TextAd.getId().equals(mmTypeId)) {
      Company company = getAdminService().getCompany(marketingMaterial.getCompanyShortName());
      jsContent = MMTemplateBuilder.getTextAdByJS(BASE_URL, marketingMaterial.getId(), affiliateId, marketingMaterial.getTitle(), marketingMaterial.getBody(), company.getName());
    }

    try {
      MarketingMaterialContext marketingMaterialContext = new MarketingMaterialContext(marketingMaterial.getId(), null, affiliateId);

      getEventDispatcher().dispatchEvent(new MarketingMaterialImpressionEvent(marketingMaterial.getMarketingMaterialType().getId(), marketingMaterialContext));
    } catch (Exception e) {
      //TODO: logger for failure of impression event wherever we are using event dispatcher use in try catch
    }


    return jsContent;

  }

  public MarketingService getMarketingService() {
    return marketingService;
  }

  public AdminService getAdminService() {
    return adminService;
  }


  public EventDispatcher getEventDispatcher() {
    return eventDispatcher;
  }
}
