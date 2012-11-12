package com.ds.action.product;

import com.ds.domain.product.ProductGroup;
import com.ds.domain.user.User;
import com.ds.pact.service.product.ProductService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author adlakha.vaibhav
 */
public class ProductGroupSearchAction extends BasePaginatedAction {

  private String groupName;
  private String productName;
  private String companyProductId;
  private String companyShortName;

  private Page productGroupsPage;
  private List<ProductGroup> productGroups;


  @Autowired
  private ProductService productService;

  @DefaultHandler
  public Resolution pre() {
    return new ForwardResolution("/pages/company/productGroup.jsp");
  }

  @SuppressWarnings("unchecked")
  public Resolution searchMarketingMaterial() {
    User loggedInUser = SecurityHelper.getLoggedInUser();
    companyShortName = loggedInUser.getCompanyShortName();
    productGroupsPage = getProductService().searchProductGroup(groupName, productName, companyProductId, companyShortName, getPageNo(), getPerPage());
    productGroups = productGroupsPage.getList();

    return new ForwardResolution("/pages/company/productGroup.jsp");

  }

  @Override
  public int getPageCount() {
    return productGroupsPage != null ? productGroupsPage.getTotalPages() : 0;
  }

  @Override
  public int getResultCount() {
    return productGroupsPage != null ? productGroupsPage.getTotalResults() : 0;
  }

  @Override
  public Set<String> getParamSet() {
    HashSet<String> params = new HashSet<String>();
    params.add("groupName");
    params.add("productName");
    params.add("companyProductId");
    params.add("companyShortName");

    return params;
  }

  public ProductService getProductService() {
    return productService;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getCompanyProductId() {
    return companyProductId;
  }

  public void setCompanyProductId(String companyProductId) {
    this.companyProductId = companyProductId;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public Page getProductGroupsPage() {
    return productGroupsPage;
  }

  public void setProductGroupsPage(Page productGroupsPage) {
    this.productGroupsPage = productGroupsPage;
  }

  public List<ProductGroup> getProductGroups() {
    return productGroups;
  }

  public void setProductGroups(List<ProductGroup> productGroups) {
    this.productGroups = productGroups;
  }
}
