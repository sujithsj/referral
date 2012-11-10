package com.ds.impl.service.product;

import com.ds.domain.product.ProductGroup;
import com.ds.exception.InvalidParameterException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.product.ProductService;
import com.ds.search.impl.ProductGroupQuery;
import com.ds.web.action.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adlakha.vaibhav
 */
@Service
public class ProductServiceImpl implements ProductService{

  @Autowired
  private SearchService searchService;

  @Autowired
  private BaseDao baseDao;
  
  @Override
  public Page searchProductGroup(String groupName, String productName, String companyProductId, String companyShortName, int pageNo, int perPage) {
    ProductGroupQuery productGroupQuery = new ProductGroupQuery();
    productGroupQuery.setCompanyShortName(companyShortName);
    productGroupQuery.setGroupName(groupName).setProductName(productName);
    productGroupQuery.setCompanyProductId(companyProductId);

    productGroupQuery.setOrderByField("nm").setPageNo(pageNo).setRows(perPage);

    return getSearchService().list(productGroupQuery);
  }

  @Override
  public ProductGroup getProductGroupById(Long productGroupId) {
    if (productGroupId == null) {
      throw new InvalidParameterException("PRODUCT_GRP_ID_CANNOT_BE_BLANK");
    }
    return (ProductGroup) getBaseDao().findUniqueByNamedQueryAndNamedParam("getProductGroupById", new String[]{"productGroupId"}, new Object[]{productGroupId});
  }


  public SearchService getSearchService() {
    return searchService;
  }

  public BaseDao getBaseDao() {
    return baseDao;
  }
}
