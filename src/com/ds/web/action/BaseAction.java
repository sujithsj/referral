package com.ds.web.action;


import com.ds.pact.dao.BaseDao;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class BaseAction implements ActionBean {
  private ActionBeanContext context;

  /*@Autowired
  private SecurityManager securityManager;

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;*/

  @Autowired
  private BaseDao baseDao;

  public ActionBeanContext getContext() {
    return context;
  }

  public void setContext(ActionBeanContext context) {
    this.context = context;
  }

  /*public org.apache.shiro.mgt.SecurityManager getSecurityManager() {
    return securityManager;
  }*/

  protected void addValidationError(String key, SimpleError localizableError) {
    getContext().getValidationErrors().add(key, localizableError);
  }

  public List<ValidationError> getMessageList(ValidationErrors validationErrors) {
    List<ValidationError> messageList = new ArrayList<ValidationError>();
    Collection<List<ValidationError>> errorCol = validationErrors.values();
    for (List<ValidationError> errors : errorCol) {
      messageList.addAll(errors);
    }
    return messageList;
  }

  public void addRedirectAlertMessage(SimpleMessage message) {
    List<net.sourceforge.stripes.action.Message> messages = getContext().getMessages("generalMessages");
    messages.add(message);
  }

 /* public PrincipalImpl getPrincipal() {
    return (PrincipalImpl) securityManager.getSubject().getPrincipal();
  }

  public User getPrincipalUser() {
    if (getPrincipal() == null)
      return null;
    return getUserService().getUserById(getPrincipal().getId());
  }

  public Subject getSubject() {
    return securityManager.getSubject();
  }

  public BreadcrumbInterceptor.Crumb getPreviousBreadcrumb() {
    return BreadcrumbInterceptor.getPreviousBreadcrumb(getContext().getRequest().getSession());
  }*/

  /*public BreadcrumbInterceptor.Crumb getCurrentBreadcrumb() {
    return BreadcrumbInterceptor.getCurrentBreadcrumb(getContext().getRequest().getSession());
  }*/

  public void noCache() {
    HttpServletResponse response = getContext().getResponse();
    response.addHeader("Pragma", "no-cache");
    response.addHeader("Cache-Control", "no-cache");
    response.addHeader("Cache-Control", "private");
    response.addHeader("Cache-Control", "no-store");
    response.addHeader("Cache-Control", "max-age=0");
    response.addHeader("Cache-Control", "s-maxage=0");
    response.addHeader("Cache-Control", "must-revalidate");
    response.addHeader("Cache-Control", "proxy-revalidate");
  }

  protected boolean isSecureRequest() {
    return getContext().getRequest().isSecure();
  }

  /*public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public void setSecurityManager(SecurityManager securityManager) {
    this.securityManager = securityManager;
  }*/

  public BaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(BaseDao baseDao) {
    this.baseDao = baseDao;
  }

  /*public RoleService getRoleService() {
    return roleService;
  }

  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }*/

}

