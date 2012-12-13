package com.ds.web.filter;

import com.ds.web.locale.AffiliateLocaleContext;
import com.ds.web.locale.AffiliateLocaleContextHolder;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author adlakha.vaibhav
 */
public class AffiliateLocalizationFilter implements Filter {


  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    try {
      AffiliateLocaleContext affiliateLocaleContext = new AffiliateLocaleContext();
      
      AffiliateLocaleContextHolder.setAffiliateLocaleContext(affiliateLocaleContext);
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
