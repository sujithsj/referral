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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
@Service
public class MarketingServiceImpl implements MarketingService {

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
  public Map<Long, Long> getCountForMarketingMaterialByType(String companyShortName) {
    Map<Long, Long> resultsCount = new HashMap<Long, Long>();
    String query = "select mm.marketingMaterialType.id , count(*) from MarketingMaterial mm where mm.companyShortName = '"+ companyShortName + "' group by mm.marketingMaterialType.id";

    List<Object[]> results = (List<Object[]>) getBaseDao().findByQuery(query);

    for (Object[] objectArr : results) {

      Long count = (Long) objectArr[1];
      if (count == null) {
        count = 0L;
      }
      resultsCount.put((Long) objectArr[0], count);
    }

    return resultsCount;
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
