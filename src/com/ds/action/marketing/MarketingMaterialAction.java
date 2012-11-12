package com.ds.action.marketing;

import com.ds.constants.EnumMarketingMaterialType;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.marketing.MarketingMaterialType;
import com.ds.domain.user.User;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class MarketingMaterialAction extends BaseAction {

  private String title;
  private Long type;
  private String body;
  private String landingPageURL;


  private Long marketingMaterialId;

  @Autowired
  private MarketingService marketingService;

  @DefaultHandler
  public Resolution createOrEditMarketingMaterial() {
    if (marketingMaterialId != null) {
      MarketingMaterial marketingMaterial = getMarketingService().getMarektingMaterialById(marketingMaterialId);
      title = marketingMaterial.getTitle();
      type = marketingMaterial.getMarketingMaterialType().getId();
      body = marketingMaterial.getBody();
      landingPageURL = marketingMaterial.getLandingPageUrl();
    }


    return new ForwardResolution("/pages/marketing/mmCrud.jsp");
  }


  public Resolution saveMarketingMaterial() {
    MarketingMaterial marketingMaterial;

    if (marketingMaterialId != null) {
      marketingMaterial = getMarketingService().getMarektingMaterialById(marketingMaterialId);
    } else {
      marketingMaterial = new MarketingMaterial();
    }

    if (marketingMaterial != null) {

      //TODO: validate type of material here while setting values

      User loggedInUser = SecurityHelper.getLoggedInUser();
      String companyShortName = loggedInUser.getCompanyShortName();
      marketingMaterial.setBody(body);
      marketingMaterial.setCompanyShortname(companyShortName);
      marketingMaterial.setLandingPageUrl(landingPageURL);
      marketingMaterial.setTitle(title);
      MarketingMaterialType marketingMaterialType = EnumMarketingMaterialType.getById(type).asMarketingMaterialType();
      marketingMaterial.setMarketingMaterialType(marketingMaterialType);

      marketingMaterial = getMarketingService().saveMarketingMaterial(marketingMaterial);

      addRedirectAlertMessage(new SimpleMessage("Changes saved successfully.Please upload a banner"));
      return new RedirectResolution(MarketingMaterialAction.class, "createOrEditMarketingMaterial").addParameter("marketingMaterialId", marketingMaterial.getId());
    } else {
      addRedirectAlertMessage(new SimpleMessage("Could not find/create supplier"));
      return new RedirectResolution(MarketingMaterialAction.class, "createOrEditMarketingMaterial");
    }
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Long getMarketingMaterialId() {
    return marketingMaterialId;
  }

  public void setMarketingMaterialId(Long marketingMaterialId) {
    this.marketingMaterialId = marketingMaterialId;
  }

  public String getLandingPageURL() {
    return landingPageURL;
  }

  public void setLandingPageURL(String landingPageURL) {
    this.landingPageURL = landingPageURL;
  }

  public MarketingService getMarketingService() {
    return marketingService;
  }
}
