package com.ds.impl.service.marketing;

/**
 * @author adlakha.vaibhav
 */
public class MMSharingTemplates {

  private static final String IMG_HOST = "dolusmia.com";

  /***
   * banner related tepmlates and constants.
   */

  private static final String BANNER_HTML_CONTENT = "<a href=\"http://#baseUrl/mmr/#bannerId/#affiliateId\" title=\"\">" +
                   "<img src=\"http://" + IMG_HOST + "/getImage/#imageId\" alt=\"\" title=\"\" style=\"border: none\"   /></a>";

private static final String strVar="<style type=\"text\\/css\" media=\"screen\">"+
 " .ad a {"+
 "   float: none !important;"+
 "   padding: 0 !important;"+
 " }"+
 " .ad .adt,"+
 " .ad .adt span,"+
 " .ad .adb,"+
 " .ad .add a {"+
 "    font-family: Tahoma;"+
 "  }"+
 " .ad {"+
 "    width: 248px !important;"+
 "    border: 1px solid #B2B2B2 !important;"+
 "    padding:4px !important;"+
 "    background-color: #ffffff !important;"+
 "    text-align: left !important;"+
 "  }."+
 " .ad .adt {"+
 "   font-size: 14px !important;"+
"              color:#129FCD !important;"+
"              cursor:pointer !important;"+
"              font-weight:bold !important;"+
"              padding:1px !important;"+
"              text-decoration:underline !important;"+
"            }"+
"            .ad a.adt {"+
"              font-size: 14px !important;"+
"              text-decoration: none !important;"+
"              border-bottom: none !important;"+
"              color:#0F5290 !important;"+
"              font-weight: bold !important;"+
"            }"+
"            .ad .adb {"+
"              color:#666666 !important;"+
"              font-size:12px !important;"+
"              line-height:15px !important;"+
"              margin-bottom:2px !important;"+
"              padding:1px !important;"+
"            }"+
"            .ad .add a {"+
"              color:#31B100 !important;"+
"              font-size:10px !important;"+
"              line-height:12px !important;"+
"              overflow:hidden !important;"+
"              white-space:nowrap !important;"+
"            }"+
"          <\\/style>"+
"          <div class=\"ad\">"+
"            <a target=\"_top\" href=\"http://#baseUrl/mm/#bannerId/#affiliateId\" class=\"adt\"><span>#mmTitle</span></a>"+
"            <div class=\"adb\">#mmBody</div>"+
"            <div class=\"add\"><a target=\"_top\" href=\"http://#baseUrl/mmr/#bannerId/#affiliateId\" class=\"adt\">#companyName</a></div>"+
"          </div>";




  private static final String TEXT_AD_HTML_CONTENT =
      "          <div class=\"ad\">\n" +
      "            <a target=\"_top\" href=\"http://#baseUrl/mm/#bannerId/#affiliateId\" class=\"adt\"><span>#mmTitle</span></a>\n" +
      "            <div class=\"adb\">#mmBody</div>\n" +
      "            <div class=\"add\"><a target=\"_top\" href=\"http://#baseUrl/mm/#bannerId/#affiliateId\" class=\"adt\">#companyName</a></div>\n" +
      "          </div>";


  private static final String TEXT_AD_CSS_CONTENT = "<style type=\"text/css\" media=\"screen\">\n" +
      "            .ad a {\n" +
      "              float: none !important;\n" +
      "              padding: 0 !important;\n" +
      "            }\n" +
      "            .ad .adt,\n" +
      "            .ad .adt span,\n" +
      "            .ad .adb,\n" +
      "            .ad .add a {\n" +
      "               font-family: Tahoma;\n" +
      "             }\n" +
      "            .ad {\n" +
      "               width: 248px !important;\n" +
      "               border: 1px solid #B2B2B2 !important;\n" +
      "               padding:4px !important;\n" +
      "               background-color: #ffffff !important;\n" +
      "               text-align: left !important;\n" +
      "             }.\n" +
      "            .ad .adt {\n" +
      "              font-size: 14px !important;\n" +
      "              color:#129FCD !important;\n" +
      "              cursor:pointer !important;\n" +
      "              font-weight:bold !important;\n" +
      "              padding:1px !important;\n" +
      "              text-decoration:underline !important;\n" +
      "            }\n" +
      "            .ad a.adt {\n" +
      "              font-size: 14px !important;\n" +
      "              text-decoration: none !important;\n" +
      "              border-bottom: none !important;\n" +
      "              color:#0F5290 !important;\n" +
      "              font-weight: bold !important;\n" +
      "            }\n" +
      "            .ad .adb {\n" +
      "              color:#666666 !important;\n" +
      "              font-size:12px !important;\n" +
      "              line-height:15px !important;\n" +
      "              margin-bottom:2px !important;\n" +
      "              padding:1px !important;\n" +
      "            }\n" +
      "            .ad .add a {\n" +
      "              color:#31B100 !important;\n" +
      "              font-size:10px !important;\n" +
      "              line-height:12px !important;\n" +
      "              overflow:hidden !important;\n" +
      "              white-space:nowrap !important;\n" +
      "            }\n" +
      "          </style>\n" ;
  
  private static final String MM_SHARE__BASIC_TEMPLATE =
      "<script type=\"text/javascript\">\n" +
          "  var zfBaseURL=((\"https:\"==document.location.protocol)\n" +
          "                 ? \"https://#baseUrl/api/mm/js/#bannerId/#affiliateId\"\n" +
          "                 : \"http://#baseUrl/api/mm/js/#bannerId/#affiliateId\");\n" +
          "  document.write(unescape(\"%3Cscript src=\" + zfBaseURL\n" +
          "  + \" type=\\\"text/javascript\\\"%3E%3C/script%3E\"));\n" +
          "</script>\n" ;


  public static final String BANNER_SHARE_TEMPLATE = MM_SHARE__BASIC_TEMPLATE
      +  "<noscript>" + BANNER_HTML_CONTENT + "</noscript>";
  public static final String BANNER_THRU_JS = "document.write('" + BANNER_HTML_CONTENT + "');";

  public static final String TEXT_AD_SHARE_TEMPLATE = MM_SHARE__BASIC_TEMPLATE
     +  "<noscript>" + strVar + "</noscript>";
  public static final String TEXT_AD_THRU_JS = "document.write('"+ strVar +"');";


  /**
   * text ad related templates and constants
   */


  
}
