package com.ds.impl.service.marketing;

/**
 * @author adlakha.vaibhav
 */
public class MMTemplateBuilder {

  public static String getSharingCodeForBanner(String baseUrl, Long bannerId, Long affiliateId, Long imageId) {
    String sharingCode = MMSharingTemplates.BANNER_SHARE_TEMPLATE;
    sharingCode = replaceTokensInBannerTemplate(sharingCode, baseUrl, bannerId, affiliateId, imageId);
    return sharingCode;
  }

  public static String getBannerByJS(String baseUrl, Long bannerId, Long affiliateId, Long imageId) {
    String bannerJSContent = MMSharingTemplates.BANNER_THRU_JS;
    bannerJSContent = replaceTokensInBannerTemplate(bannerJSContent, baseUrl, bannerId, affiliateId, imageId);
    return bannerJSContent;
  }

  private static String replaceTokensInBannerTemplate(String templateContent, String baseUrl, Long bannerId, Long affiliateId, Long imageId) {
    templateContent = templateContent.replaceAll("#baseUrl", baseUrl);
    templateContent = templateContent.replaceAll("#bannerId", bannerId.toString());
    templateContent = templateContent.replaceAll("#affiliateId", affiliateId.toString());
    templateContent = templateContent.replaceAll("#imageId", imageId.toString());
    return templateContent;
  }


public static String getSharingCodeForTextAd(String baseUrl, Long bannerId, Long affiliateId, String mmTitle, String mmBody, String companyName) {
    String sharingCode = MMSharingTemplates.TEXT_AD_SHARE_TEMPLATE;
    sharingCode = replaceTokensInTextAdTemplate(sharingCode, baseUrl, bannerId, affiliateId, mmTitle, mmBody, companyName);
    return sharingCode;
  }

  public static String getTextAdByJS(String baseUrl, Long bannerId, Long affiliateId, String mmTitle, String mmBody, String companyName) {
    String bannerJSContent = MMSharingTemplates.TEXT_AD_THRU_JS;
    bannerJSContent = replaceTokensInTextAdTemplate(bannerJSContent, baseUrl, bannerId, affiliateId, mmTitle, mmBody, companyName);
    return bannerJSContent;
  }

  private static String replaceTokensInTextAdTemplate(String templateContent, String baseUrl, Long bannerId, Long affiliateId, String mmTitle, String mmBody, String companyName) {
    templateContent = templateContent.replaceAll("#baseUrl", baseUrl);
    templateContent = templateContent.replaceAll("#bannerId", bannerId.toString());
    templateContent = templateContent.replaceAll("#affiliateId", affiliateId.toString());
    templateContent = templateContent.replaceAll("#mmBody", mmBody);
    templateContent = templateContent.replaceAll("#mmTitle", mmTitle);
    templateContent = templateContent.replaceAll("#companyName", companyName);
    return templateContent;
  }


  
  public static void main(String[] args) {
    //System.out.println(getSharingCodeForBanner("dev.healthkart.com", 2L, 999L, 1L));
    System.out.println(getSharingCodeForTextAd("dev.healthkart.com", 2L, 999L, "Test Text ad", "Buy from Test Text ad", "healthkart.pricemia.com"));
  }
}
