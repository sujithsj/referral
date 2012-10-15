package com.ds.security.web;


import com.ds.security.helper.SecurityHelper;
import com.ds.utils.GeneralUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author adlakha.vaibhav
 */
public class AuthenticationSuccessRedirectHandler extends SimpleUrlAuthenticationSuccessHandler {

  private boolean useReferer = false;

  private final String REFERED_FROM_PUBLIC_FORUM = "/forum";

  protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
    if (isAlwaysUseDefaultTargetUrl()) {
      return getDefaultTargetUrl();
    }

    // Check for the parameter and use that if available
    String targetUrl = request.getParameter(getTargetUrlParameter());

    if (StringUtils.hasText(targetUrl)) {
      try {
        targetUrl = URLDecoder.decode(targetUrl, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        throw new IllegalStateException("UTF-8 not supported. Shouldn't be possible");
      }

      logger.debug("Found targetUrlParameter in request: " + targetUrl);

      return targetUrl;
    }

    if (useReferer && !StringUtils.hasLength(targetUrl)) {
      targetUrl = request.getHeader("Referer");
      logger.debug("Using Referer header: " + targetUrl);
    }

    if (!StringUtils.hasText(targetUrl)) {
      String refererURL = GeneralUtils.getRefererURL(request);

      if (refererURL != null) {
        if (refererURL.contains(REFERED_FROM_PUBLIC_FORUM)) {
          targetUrl = refererURL;
        } else if (SecurityHelper.isLoggedInUserStoreEmployee()) {
          targetUrl = "/admin/store/vendors";
        } else {
          targetUrl = getDefaultTargetUrl();
        }
      } else if (SecurityHelper.isLoggedInUserStoreEmployee()) {
        targetUrl = "/admin/store/vendors";
      } else {
        targetUrl = getDefaultTargetUrl();
      }
      logger.debug("Using default Url: " + targetUrl);
    }

    return targetUrl;
  }

  /**
   * If set to <tt>true</tt> the <tt>Referer</tt> header will be used (if available). Defaults to <tt>false</tt>.
   */
  public void setUseReferer(boolean useReferer) {
    this.useReferer = useReferer;
  }
}
