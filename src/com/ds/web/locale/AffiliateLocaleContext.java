package com.ds.web.locale;

/**
 * @author adlakha.vaibhav
 */
public class AffiliateLocaleContext {

  //TODO: remove this hard coding
  private String companyShortName  = "hk";

  private String login;

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }
}
