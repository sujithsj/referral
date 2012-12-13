package com.ds.action.aff;

import com.ds.constants.EnumMarketingMaterialType;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.pact.service.marketing.MarketingService;
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
public class AffiliateAdSearchAction extends BasePaginatedAction {

  private Long type;
  private String title;

  private Page marketingMaterialPage;
  private List<MarketingMaterial> marketingMaterials;

  @Autowired
  private MarketingService marketingService;

  @DefaultHandler
  public Resolution pre() {

    type = EnumMarketingMaterialType.ALL.getId();
    return searchMarketingMaterial();
  }

  @SuppressWarnings("unchecked")
  public Resolution searchMarketingMaterial() {
    String companyShortNameForLoggedInAffiliate = AffiliateUIHelper.getCompanyShortNameForLoggedInAffiliate();

    if (EnumMarketingMaterialType.ALL.getId().equals(type)) {
      type = null;
    }

    marketingMaterialPage = getMarketingService().searchMarketingMaterial(null, type, companyShortNameForLoggedInAffiliate, null, getPageNo(), getPerPage());
    marketingMaterials = marketingMaterialPage.getList();

    return new ForwardResolution("/pages/aff/affiliateMarketingMaterial.jsp");
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
    params.add("type");


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

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
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
