package com.ds.constants;

/**
 * @author adlakha.vaibhav
 */
public enum FeatureType {
  PRODUCT_COUNT("ProductCount"),
  EMPLOYEE_COUNT("EmployeeCount"),
  DASHBOARD_SUPPORT("DashboardSupport"),
  MODERATION("Moderation"),
  REDMINE("Redmine"),
  BUGZILLA("Bugzilla"),
  JIRA("JIRA"),
  GOOGLE_ANALYTICS("GoogleAnalytics"),
  JAVASCRIPT_VARIABLES("JavascriptVariables"),
  EMAIL_NOTIFICATIONS("EmailNotifications"),
  DOMAIN_ALIASING("DomainAliasing"),
  ANALYTICS_SUPPORT("AnalyticsSupport");

  private String featureType;

  private FeatureType(String featureType) {
    this.featureType = featureType;
  }

  public String featureType() {
    return featureType;
  }


}
