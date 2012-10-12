package com.ds.security.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author adlakha.vaibhav
 */
public class AuthenticationFailureRedirectHandler extends SimpleUrlAuthenticationFailureHandler {
  private Logger logger = LoggerFactory.getLogger(AuthenticationFailureRedirectHandler.class);

  private String defaultFailureUrl;
  private boolean forwardToDestination = false;
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  public AuthenticationFailureRedirectHandler() {
  }

  public AuthenticationFailureRedirectHandler(String defaultFailureUrl) {
    setDefaultFailureUrl(defaultFailureUrl);
  }

  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

    if (defaultFailureUrl == null) {
      logger.debug("No failure URL set, sending 401 Unauthorized error");

      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
    } else {
      if (forwardToDestination) {
        logger.debug("Forwarding to " + defaultFailureUrl);

        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
      } else {
        logger.debug("Redirecting to " + defaultFailureUrl);
        redirectStrategy.sendRedirect(request, response, defaultFailureUrl + "&j_username=" + request.getParameter("j_username"));
      }
    }
  }

  /**
   * The URL which will be used as the failure destination.
   *
   * @param defaultFailureUrl the failure URL, for example "/loginFailed.jsp".
   */
  public void setDefaultFailureUrl(String defaultFailureUrl) {
    Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl), "'" + defaultFailureUrl + "' is not a valid redirect URL");
    this.defaultFailureUrl = defaultFailureUrl;
  }

  protected boolean isUseForward() {
    return forwardToDestination;
  }

  /**
   * If set to <tt>true</tt>, performs a forward to the failure destination URL instead of a redirect. Defaults to
   * <tt>false</tt>.
   */
  public void setUseForward(boolean forwardToDestination) {
    this.forwardToDestination = forwardToDestination;
  }

  /**
   * Allows overriding of the behaviour when redirecting to a target URL.
   */
  public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
    this.redirectStrategy = redirectStrategy;
  }

  protected RedirectStrategy getRedirectStrategy() {
    return redirectStrategy;
  }
}
