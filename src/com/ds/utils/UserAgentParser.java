package com.ds.utils;

import com.ds.exception.DSException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author adlakha.vaibhav
 */
public class UserAgentParser {

  private static final Logger logger = LoggerFactory.getLogger(UserAgentParser.class);

  private static String pattern = "^([^/[:space:]]*)(/([^[:space:]]*))?([[:space:]]*/[[a-zA-Z][a-zA-Z]/])?[[:space:]]*(\\((([^()]|(\\([^()]*\\)))*)\\))?[[:space:]]*";

  /**
   * Parse the user agent and return a list containing browserName, browserVersion, OS Name, OS Version
   *
   * @param userAgent
   * @return
   */
  public static List<String> parseUserAgent(String userAgent) {
    String browserName = "", browserVersion = "", osName = "", osVersion = "";

    List<String> tokens = applyPatternToUserAgent(userAgent);

    try {
      if (tokens.size() > 0) {
        String productToken = tokens.get(0);
        String versionToken = tokens.size() >= 1 ? tokens.get(1) : "";

        /* if a product in the list matches one of those that correctly declare themselves, returns it */
        if (productToken.contains("firefox") ||
            productToken.contains("netscape") ||
            productToken.contains("safari") ||
            productToken.contains("camino") ||
            productToken.contains("mosaic") ||
            productToken.contains("opera")
            ) {
          browserName = productToken;
          browserVersion = productToken.substring(productToken.indexOf("/"));

          /* if opera uses not standard format to declare version, matches it */
          //if (product == OPERA) verify_if_string_match_pattern_like(“Opera 5.11 [en]”);

        }
        /**
         * need to handle konqueror differently
         */
        else if (productToken.contains("mozilla") && versionToken.contains("compatible")
          //&& !versionToken.contains( "konqueror" )
            ) {
          String[] commTokens = versionToken.substring(versionToken.indexOf("(")).split(";");
          String productVerString = commTokens.length >= 1 ? commTokens[1] : "";
          String osString = commTokens.length > 2 ? commTokens[2] : "";
          String[] osResults = getOsNameAndVersion(osString);
          osName = osResults[0];
          osVersion = osResults[1];

          if (StringUtils.isNotBlank(productVerString)) {
            String arr[];

            if (productVerString.contains("/"))
              arr = productVerString.split("/");
            else
              arr = productVerString.split(" ");

            browserName = arr.length >= 0 ? arr[1] : "";
            browserVersion = arr.length >= 1 ? arr[2] : "";


          }
        } else if (productToken.contains("mozilla")) {
          if (StringUtils.isNotBlank(versionToken)) {
            String versionNumString = versionToken.substring(0, versionToken.indexOf("("));
            float verNo = -1;
            try {
              verNo = Float.parseFloat(versionNumString);
            } catch (NumberFormatException nfe) {
              nfe.printStackTrace();
            }

            if (verNo != -1) {
              if (verNo < 5.0) {
                browserName = "NETSCAPE";
                browserVersion = productToken.substring(productToken.indexOf("/"));
              }

              /**
               * identifying real firefox here
               */
              else {
                browserName = "MOZILLA";
                String verStr = tokens.get(3);
                String verStrArr[] = verStr.split(" ");
                browserVersion = verStrArr[0];

                String osString = tokens.get(1);
                String arr[] = osString.split(";");
                String[] osResults = getOsNameAndVersion(arr[2]);
                osName = osResults[0];
                osVersion = osResults[1];

              }
            }
          }
        } else {
          browserName = productToken;
          browserVersion = productToken.substring(productToken.indexOf("/"));
        }

      }
    } catch (Exception e) {
      logger.error("Could not parse user agent", e);
      throw new DSException("EXCEPTION_PARSING_USER_AGENT", e);
    }


    return Arrays.asList(new String[]{browserName.trim(), browserVersion.trim(), osName.trim(), osVersion.trim()});
  }

  private static List<String> applyPatternToUserAgent(String userAgent) {
    List<String> result = new ArrayList<String>();

    while (userAgent.length() > 0) {
      userAgent = userAgent.toLowerCase();
      Pattern pat = Pattern.compile(pattern);
      Matcher matcher = pat.matcher(userAgent);

      if (matcher.find()) {
        result.add(userAgent.substring(matcher.start(), matcher.end()));
        userAgent = userAgent.substring(matcher.end());
      }
    }

    return result;
  }

  private static String[] getOsNameAndVersion(String osNameVerString) {

    if (osNameVerString.contains("("))
      osNameVerString = osNameVerString.replace("(", "");
    if (osNameVerString.contains(")"))
      osNameVerString = osNameVerString.replace(")", "");


    Pattern pattern = Pattern.compile("\\d");
    Matcher matcher = pattern.matcher(osNameVerString);

    if (matcher.find()) {
      return new String[]{osNameVerString.substring(0, matcher.start()), osNameVerString.substring(matcher.start())};
    } else {
      return new String[]{"", ""};
    }

  }

  public static void main(String[] args) {
    parseUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.18) Gecko/2010020220 Firefox/3.0.18 (.NET CLR 3.5.30729)");
    parseUserAgent("Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)");
  }

}
