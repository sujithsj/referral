package com.ds.action.aff;

import com.ds.domain.affiliate.Affiliate;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.security.aff.AffAuthentication;
import com.ds.security.aff.AffUsernamePasswordAuthenticationToken;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 4, 2012
 * Time: 9:49:42 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AffiliateLoginAction extends BaseAction {

  private String password;
  private String login;

  @Autowired
  private AffiliateService affiliateService;

  @Autowired
  private MessageDigestPasswordEncoder messageDigestPasswordEncoder;


  @DefaultHandler
  public Resolution pre() {
    return new RedirectResolution("/pages/login.jsp");
  }

  public Resolution loginAffiliate() {
    AffAuthentication authResult;
    try {
      authResult = attemptAuthentication();
      if (authResult == null) {
        // return immediately as subclass has indicated that it hasn't completed authentication
        return null;
      }
    } catch (AffAuthenticationException failed) {
      unsuccessfulAuthentication(failed);
      return null;
    }

    successfulAuthentication(authResult);


    return null;


  }


  protected void unsuccessfulAuthentication(AffAuthenticationException failed) {
    //getFailureHandler().handleAuthenticationFailure(request, response, failed);
  }

  protected void successfulAuthentication(AffAuthentication authResult)  {
    //getSuccessHandler().handleAuthenticationSuccess(request, response, authResult);
  }

  protected AffAuthentication attemptAuthentication() throws AffAuthenticationException {
    login = login.trim();
    AffUsernamePasswordAuthenticationToken authRequest = new AffUsernamePasswordAuthenticationToken(login, password);
    return authenticate(authRequest);
  }

  public AffAuthentication authenticate(AffAuthentication authentication) throws AffAuthenticationException {

    if (authentication instanceof AffUsernamePasswordAuthenticationToken) {

      String login = (String) authentication.getPrincipal();
      String password = (String) authentication.getCredentials();


      Affiliate affiliate = getAffiliateService().getAffiliateByLogin(login);


      if (affiliate == null) {
        throw new AffNotFoundException("AFF_NOT_FOUND", login);
      }

      String encodedPassword = getMessageDigestPasswordEncoder().encodePassword(password, affiliate.getLogin());
     /* if (encodedPassword.equals(affiliate.getPasswordChecksum())) {
        authentication = new AffUsernamePasswordAuthenticationToken(login, password);
      } else {
        throw new AffAuthenticationException("INVALID_AFF_CREDENTIAL",(String) authentication.getCredentials());
      }*/
      return authentication;
    } else {
      // throw some authentication exception here.
    }

    return null;

  }


  public String getPassword() {
    return password;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AffiliateService getAffiliateService() {
    return affiliateService;
  }

  public MessageDigestPasswordEncoder getMessageDigestPasswordEncoder() {
    return messageDigestPasswordEncoder;
  }
}
