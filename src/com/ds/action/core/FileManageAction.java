package com.ds.action.core;

import com.ds.action.company.CompanyAction;
import com.ds.pact.service.core.FileManageService;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class FileManageAction extends BaseAction {

  private String companyShortName;

  private Long marketingMarketingId;

  @Autowired
  private FileManageService fileManageService;


  public Resolution deleteCompanyLogo() {
    getFileManageService().deleteCompanyLogo(companyShortName);
    return new RedirectResolution(CompanyAction.class).addParameter("companyShortName", companyShortName);
  }

  public Resolution deleteMarketingMaterialImage() {
    getFileManageService().deleteMarketingMaterialImage(marketingMarketingId);
    return new RedirectResolution(CompanyAction.class).addParameter("companyShortName", companyShortName);
  }

  public FileManageService getFileManageService() {
    return fileManageService;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public Long getMarketingMarketingId() {
    return marketingMarketingId;
  }

  public void setMarketingMarketingId(Long marketingMarketingId) {
    this.marketingMarketingId = marketingMarketingId;
  }
}
