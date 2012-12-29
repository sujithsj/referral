package com.ds.web.filter;

import com.ds.web.locale.AffiliateLocaleContext;
import com.ds.web.locale.AffiliateLocaleContextHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author adlakha.vaibhav
 */
public class RedirectFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    try {

      if (servletRequest instanceof HttpServletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURL = req.getRequestURL().toString();

        String subdomain = null;
        if (requestURL != null && requestURL.length() > "http://".length()) {
          requestURL = requestURL.substring(7);
        }
        if (requestURL != null && requestURL.contains(".")) {
          subdomain = requestURL.substring(0, requestURL.indexOf("."));
        }

        AffiliateLocaleContext affiliateLocaleContext = AffiliateLocaleContextHolder.getAffiliateLocaleContext();

        if (StringUtils.isNotBlank(affiliateLocaleContext.getCompanyShortName())) {
          /**
           * TODO: check if affiliate is logged in redirect to fk.healthkart.com
           */

          if (requestURL.equals("http://" + affiliateLocaleContext.getCompanyShortName() + AffiliateLocalizationFilter.BASE_URL) ||
              requestURL.equals(affiliateLocaleContext.getCompanyShortName() + AffiliateLocalizationFilter.BASE_URL) ||
             requestURL.equals(affiliateLocaleContext.getCompanyShortName() + AffiliateLocalizationFilter.BASE_URL + "/") ||
              requestURL.equals("http://" + affiliateLocaleContext.getCompanyShortName() + AffiliateLocalizationFilter.BASE_URL + "/") ) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendRedirect("http://" + affiliateLocaleContext.getCompanyShortName() + AffiliateLocalizationFilter.BASE_URL + "/pages/aff/affiliateLogin.jsp");
          }
        }
      }

      filterChain.doFilter(servletRequest, servletResponse);
    } finally {
      LocaleContextHolder.resetLocaleContext();
    }

  }

  public void destroy() {
  }

  public void init(FilterConfig arg0) throws ServletException {
  }


}
