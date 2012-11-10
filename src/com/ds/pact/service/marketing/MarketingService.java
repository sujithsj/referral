package com.ds.pact.service.marketing;

import com.ds.web.action.Page;

/**
 * @author adlakha.vaibhav
 */
public interface MarketingService {

  public Page searchMarketingMaterial(String title, int type, String companyShortName, String landingPage, int pageNo, int perPage);
}
