package com.ds.pact.service.core;

import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.domain.marketing.MarketingMaterial;

/**
 * @author adlakha.vaibhav
 */
public interface FileManageService {


  public Company deleteCompanyLogo(String companyShortName);


  public Company associateCompanyLogo(String companyShortName, Long fileAttachmentId);


  public void associateUserImage(String companyShortName, Long fileAttachmentId,String userId);


  public User deleteUserImage(String companyShortName,String userId);



  public MarketingMaterial associateMaketingMaterialImage(Long marketingMaterialId, Long fileAttachmentId);

  public MarketingMaterial deleteMarketingMaterialImage(Long marketingMaterialId);
}
