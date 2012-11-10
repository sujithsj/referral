package com.ds.impl.service.product;

import com.ds.domain.product.ProductGroup;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.core.SearchService;
import com.ds.pact.service.product.ProductService;
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
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public ProductGroup getProductGroupById(Long productGroupId) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
