package com.ds.action.marketing;

import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.user.User;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
@Component
public class MarketingMaterialSearchAction extends BasePaginatedAction {

  private String title;
  private int type;
  private String landingPage;
  private String companyShortName;


  private Page marketingMaterialPage;
  private List<MarketingMaterial> marketingMaterials;


  @Autowired
  private MarketingService marketingService;

  @DefaultHandler
  public Resolution pre() {
    return new ForwardResolution("/pages/marketing/marketingMaterial.jsp");
  }

  @SuppressWarnings("unchecked")
  public Resolution searchMarketingMaterial() {
    User loggedInUser = SecurityHelper.getLoggedInUser();
    companyShortName = loggedInUser.getCompanyShortName();
    marketingMaterialPage = getMarketingService().searchMarketingMaterial(title, type, companyShortName, landingPage, getPageNo(), getPerPage());
    marketingMaterials = marketingMaterialPage.getList();

    return new ForwardResolution("/pages/company/marketingMaterial.jsp");

  }

  @Override
  public int getPageCount() {
    return marketingMaterialPage != null ? marketingMaterialPage.getTotalPages() : 0;
  }

  @Override
  public int getResultCount() {
    return marketingMaterialPage != null ? marketingMaterialPage.getTotalResults() : 0;
  }

  @Override
  public Set<String> getParamSet() {
    HashSet<String> params = new HashSet<String>();
    params.add("title");
    params.add("type");
    params.add("landingPage");
    params.add("companyShortName");

    return params;
  }


  public MarketingService getMarketingService() {
    return marketingService;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getLandingPage() {
    return landingPage;
  }

  public void setLandingPage(String landingPage) {
    this.landingPage = landingPage;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public Page getMarketingMaterialPage() {
    return marketingMaterialPage;
  }

  public void setMarketingMaterialPage(Page marketingMaterialPage) {
    this.marketingMaterialPage = marketingMaterialPage;
  }

  public List<MarketingMaterial> getMarketingMaterials() {
    return marketingMaterials;
  }

  public void setMarketingMaterials(List<MarketingMaterial> marketingMaterials) {
    this.marketingMaterials = marketingMaterials;
  }
}
