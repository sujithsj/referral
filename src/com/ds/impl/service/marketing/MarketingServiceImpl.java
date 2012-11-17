package com.ds.impl.service.marketing;

import com.ds.domain.marketing.MarketingMaterial;
import com.ds.exception.InvalidParameterException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.marketing.MarketingService;
import com.ds.search.impl.MarketingMaterialQuery;
import com.ds.web.action.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adlakha.vaibhav
 */
@Service
public class MarketingServiceImpl implements MarketingService{

  @Autowired
  private SearchService searchService;

  @Autowired
  private BaseDao baseDao;

  @Override
  public MarketingMaterial getMarektingMaterialById(Long mmId) {
    if (mmId == null) {
      throw new InvalidParameterException("MM_ID_CANNOT_BE_BLANK");
    }
    return (MarketingMaterial) getBaseDao().findUniqueByNamedQueryAndNamedParam("getMarketingMaterialById", new String[]{"mmId"}, new Object[]{mmId});
  }
  
  @Override
  public Page searchMarketingMaterial(String title, Long type, String companyShortName, String landingPage, int pageNo, int perPage) {
    MarketingMaterialQuery marketingMaterialQuery = new MarketingMaterialQuery();
    marketingMaterialQuery.setCompanyShortName(companyShortName);
    marketingMaterialQuery.setLandingPage(landingPage).setTitle(title);
    marketingMaterialQuery.setType(type);

    marketingMaterialQuery.setOrderByField("title").setPageNo(pageNo).setRows(perPage);

    return getSearchService().list(marketingMaterialQuery);
  }

  @Transactional
  public MarketingMaterial saveMarketingMaterial(MarketingMaterial marketingMaterial) {
    return (MarketingMaterial) getBaseDao().save(marketingMaterial);
  }

  public SearchService getSearchService() {
    return searchService;
  }

  public BaseDao getBaseDao() {
    return baseDao;
  }
}
