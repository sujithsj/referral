package com.ds.pact.service.product;

import com.ds.domain.product.ProductGroup;
import com.ds.web.action.Page;

/**
 * @author adlakha.vaibhav
 */
public interface ProductService {

  public Page searchProductGroup(String groupName, String productName, String companyProductId, String companyShortName, int pageNo, int perPage);

  public ProductGroup getProductGroupById(Long productGroupId);
}
