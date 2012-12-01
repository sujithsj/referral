package com.ds.impl.service.marketing;

/**
 * @author adlakha.vaibhav
 */
public class MarketingMaterialSharingTemplate {

  private static final String BANNER_SHARE_TEMPLATE =
      "<script type=\"text/javascript\">\n" +
          "  var zfBaseURL=((\"https:\"==document.location.protocol)\n" +
          "                 ? \"https://$baseUrl/mjs/$bannerId/$affiliateId\"\n" +
          "                 : \"http://$baseUrl/mjs/$bannerId/$affiliateId\");\n" +
          "  document.write(unescape(\"%3Cscript src=\" + zfBaseURL\n" +
          "  + \" type=\\\"text/javascript\\\"%3E%3C/script%3E\"));\n" +
          "</script>\n" +
          "<noscript><a href=\"http://$baeUrl/$bannerId/$affiliateId\" title=\"\"><img src=\"http://$baseUrl/getImage/$imageId\" alt=\"\" title=\"\" style=\"border: none\"   /></a></noscript>";

  public static String getSharingCodeForBanner(String baseUrl, Long bannerId, Long affiliateId, Long imageId) {

    String sharingCode = BANNER_SHARE_TEMPLATE;
    sharingCode = sharingCode.replaceAll("$baseUrl", baseUrl);
    sharingCode = sharingCode.replaceAll("$bannerId", bannerId.toString());
    sharingCode = sharingCode.replaceAll("$affiliateId", affiliateId.toString());
    sharingCode = sharingCode.replaceAll("$imageId", imageId.toString());


    return sharingCode;
  }
}
