package com.ds.impl.service.marketing;

import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.search.impl.MarketingMaterialQuery;
import com.ds.web.action.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adlakha.vaibhav
 */
@Service
public class MarketingServiceImpl implements MarketingService{

  @Autowired
  private SearchService searchService;

  @Override
  public Page searchMarketingMaterial(String title, int type, String companyShortName, String landingPage, int pageNo, int perPage) {
    MarketingMaterialQuery marketingMaterialQuery = new MarketingMaterialQuery();
    marketingMaterialQuery.setCompanyShortName(companyShortName);
    marketingMaterialQuery.setLandingPage(landingPage).setTitle(title);
    marketingMaterialQuery.setType(type);
    
    marketingMaterialQuery.setOrderByField("title").setPageNo(pageNo).setRows(perPage);

    return getSearchService().list(marketingMaterialQuery);
  }

  public SearchService getSearchService() {
    return searchService;
  }
}
