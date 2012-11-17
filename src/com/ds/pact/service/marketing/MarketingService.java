package com.ds.pact.service.marketing;

import com.ds.domain.marketing.MarketingMaterial;
import com.ds.web.action.Page;

/**
 * @author adlakha.vaibhav
 */
public interface MarketingService {

  public Page searchMarketingMaterial(String title, Long type, String companyShortName, String landingPage, int pageNo, int perPage);

  public MarketingMaterial getMarektingMaterialById(Long mmId);

  public MarketingMaterial saveMarketingMaterial(MarketingMaterial marketingMaterial);

}
