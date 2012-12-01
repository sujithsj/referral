package com.ds.impl.service.marketing;

/**
 * @author adlakha.vaibhav
 */
public class MarketingMaterialSharingTemplate {

  private static final String IMG_HOST = "dev.healthkart.com";

  private static final String BANNER_HTML_CONTENT = "<a href=\"http://#baseUrl/mm/#bannerId/#affiliateId\" title=\"\"><img src=\"http://" + IMG_HOST + "/getImage/#imageId\" alt=\"\" title=\"\" style=\"border: none\"   /></a>";

  private static final String BANNER_SHARE_TEMPLATE =
      "<script type=\"text/javascript\">\n" +
          "  var zfBaseURL=((\"https:\"==document.location.protocol)\n" +
          "                 ? \"https://#baseUrl/api/mm/js/#bannerId/#affiliateId\"\n" +
          "                 : \"http://#baseUrl/api/mm/js/#bannerId/#affiliateId\");\n" +
          "  document.write(unescape(\"%3Cscript src=\" + zfBaseURL\n" +
          "  + \" type=\\\"text/javascript\\\"%3E%3C/script%3E\"));\n" +
          "</script>\n" +
          "<noscript>" + BANNER_HTML_CONTENT + "</noscript>";

  private static final String BANNER_THRU_JS = "document.write('" + BANNER_HTML_CONTENT + "');";


  public static String getSharingCodeForBanner(String baseUrl, Long bannerId, Long affiliateId, Long imageId) {

    String sharingCode = new String(BANNER_SHARE_TEMPLATE);
    sharingCode = sharingCode.replaceAll("#baseUrl", baseUrl);
    sharingCode = sharingCode.replaceAll("#bannerId", bannerId.toString());
    sharingCode = sharingCode.replaceAll("#affiliateId", affiliateId.toString());
    sharingCode = sharingCode.replaceAll("#imageId", imageId.toString());


    return sharingCode;
  }

  public static String getBannerByJS(String baseUrl, Long bannerId, Long affiliateId, Long imageId) {
    String bannerJSContent = new String(BANNER_THRU_JS);
    bannerJSContent = bannerJSContent.replaceAll("#baseUrl", baseUrl);
    bannerJSContent = bannerJSContent.replaceAll("#bannerId", bannerId.toString());
    bannerJSContent = bannerJSContent.replaceAll("#affiliateId", affiliateId.toString());
    bannerJSContent = bannerJSContent.replaceAll("#imageId", imageId.toString());


    return bannerJSContent;
  }

  public static void main(String[] args) {
    System.out.println(getSharingCodeForBanner("dev.healthkart.com", 2L, 999L, 1L));
  }
}
