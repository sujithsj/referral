package com.ds.rest.resource;

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
  Long affiliateId){

    String sharingCode = getMarketingService().getMarketingMaterialSharingCode(marketingMaterialId, affiliateId);
    if(StringUtils.isBlank(sharingCode)){
      return new JSONResponseBuilder().addField("exception", false).addField("sc", "No Sharing code could be loaded for this ad").build();

    } else{
       return new JSONResponseBuilder().addField("exception", false).addField("sc", sharingCode).build();
    }

    
  }

  public MarketingService getMarketingService() {
    return marketingService;
  }
}
