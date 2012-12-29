package com.ds.web.filter;

import com.ds.domain.company.Company;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.admin.AdminService;
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
public class AffiliateLocalizationFilter implements Filter {


  private AdminService adminService;

  public static final String BASE_URL = ".healthkart.com";

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

        String environment = System.getProperty("env");

        if (StringUtils.isBlank(environment)) {
          environment = "dev";
        }

        if (subdomain != null && !subdomain.equals("dev") && !subdomain.equals("www")) {

          Company company = getAdminService().getCompany(subdomain);

          if (company == null) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendRedirect("http://" + environment + BASE_URL);
          } else {
            AffiliateLocaleContext affiliateLocaleContext = new AffiliateLocaleContext();
            affiliateLocaleContext.setCompanyShortName(company.getShortName());
            AffiliateLocaleContextHolder.setAffiliateLocaleContext(affiliateLocaleContext);
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

  public AdminService getAdminService() {
    if (adminService == null) {
      adminService = (AdminService) ServiceLocatorFactory.getService(AdminService.class);
    }
    return adminService;
  }
}
