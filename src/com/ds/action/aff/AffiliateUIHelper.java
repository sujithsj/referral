package com.ds.action.aff;

import com.ds.web.locale.AffiliateLocaleContext;
import com.ds.web.locale.AffiliateLocaleContextHolder;

/**
 * @author adlakha.vaibhav
 */
public class AffiliateUIHelper {


  public static String getCompanyShortNameForLoggedInAffiliate() {
    String companyShortNameForLoggedInAffiliate = null;
    AffiliateLocaleContext affiliateLocaleContext = AffiliateLocaleContextHolder.getAffiliateLocaleContext();
    if (affiliateLocaleContext != null) {
      companyShortNameForLoggedInAffiliate = affiliateLocaleContext.getCompanyShortName();
    }

    return companyShortNameForLoggedInAffiliate;
  }


}
