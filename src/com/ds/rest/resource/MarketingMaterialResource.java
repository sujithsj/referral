package com.ds.rest.resource;

import com.ds.constants.EnumMarketingMaterialType;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.exception.InvalidParameterException;
import com.ds.impl.service.marketing.MarketingMaterialSharingTemplate;
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


  @Autowired
  private MarketingService marketingService;

  @GET
  @Path("/{mmId}/share/{affiliateId}")
  @Produces("application/json")
  public String getSharingCode(@PathParam("mmId")
  Long marketingMaterialId, @PathParam("affiliateId")
  Long affiliateId) {
    validateAdParams(marketingMaterialId, affiliateId);
    String sharingCode = null;

    MarketingMaterial marketingMaterial = getMarketingService().getMarektingMaterialById(marketingMaterialId);

    Long mmTypeId = marketingMaterial.getMarketingMaterialType().getId();

    if (EnumMarketingMaterialType.Banner.getId().equals(mmTypeId)) {
      if (marketingMaterial.getImage() != null) {
        sharingCode = MarketingMaterialSharingTemplate.getSharingCodeForBanner("dev.healthkart.com", marketingMaterial.getId(), affiliateId, marketingMaterial.getImage().getId());
      }

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

    validateAdParams(marketingMaterialId, affiliateId);

    String jsContent = "";

    MarketingMaterial marketingMaterial = getMarketingService().getMarektingMaterialById(marketingMaterialId);

    //TODO: handle null marketingmaterial
    Long mmTypeId = marketingMaterial.getMarketingMaterialType().getId();

    if (EnumMarketingMaterialType.Banner.getId().equals(mmTypeId)) {
      if (marketingMaterial.getImage() != null) {
        jsContent = MarketingMaterialSharingTemplate.getBannerByJS("dev.healthkart.com", marketingMaterial.getId(), affiliateId, marketingMaterial.getImage().getId());
      }

    }
    return jsContent;

  }

  public MarketingService getMarketingService() {
    return marketingService;
  }
}
