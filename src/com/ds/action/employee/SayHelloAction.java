package com.ds.action.employee;

import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.DefaultHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author adlakha.vaibhav
 */
@Component
public class SayHelloAction extends BaseAction {

  String name;
  String remoteAddr;
  String remoteHost;
  String a;

  @DefaultHandler
  public Resolution pre() {

    HttpServletRequest request = getContext().getRequest();

    Cookie[]  allCookies = request.getCookies();

    name = name + " concat";
    HttpServletResponse httpServletResponse = getContext().getResponse();
    Cookie cookie1 = new Cookie("rahul1", "abc");

    cookie1.setPath("/");
    cookie1.setDomain("healthkart.zferal.com");
    //httpServletResponse.addCookie(cookie1);

    Cookie cookie2 = new Cookie("rahul2", "abc");
    cookie2.setPath("/");
    cookie2.setDomain(".healthkart.com");
    //httpServletResponse.addCookie(cookie2);

    Cookie cookie3 = new Cookie("rahul3", "abc");
    //cookie.setPath("/");
    //cookie.setDomain(".healthkart.com");
   // httpServletResponse.addCookie(cookie3);

    remoteAddr = getContext().getRequest().getRemoteAddr();
    remoteHost = getContext().getRequest().getRemoteHost();

    //cookie.setDomain();
    return new ForwardResolution("/pages/campaign/campaign.jsp");


  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRemoteAddr() {
    return remoteAddr;
  }

  public void setRemoteAddr(String remoteAddr) {
    this.remoteAddr = remoteAddr;
  }

  public String getRemoteHost() {
    return remoteHost;
  }

  public void setRemoteHost(String remoteHost) {
    this.remoteHost = remoteHost;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }
}

