package com.ds.action.employee;

import com.ds.domain.user.User;
import com.ds.security.service.UserService;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * @author adlakha.vaibhav
 */
@Component
public class UserSearchAction extends BasePaginatedAction {

  private String userName;
  private String email;

  private Page userPage;
  private List<User> users;

  @Autowired
  private UserService userService;


  @SuppressWarnings("unchecked")
  public Resolution searchUsers() {
    userPage = getUserService().searchUser(userName, email, getPageNo(), getPerPage());
    users = userPage.getList();

    return new ForwardResolution("/pages/company/users.jsp");

  }

  @Override
  public int getPageCount() {
    return userPage != null ? userPage.getTotalPages() : 0;
  }

  @Override
  public int getResultCount() {
    return userPage != null ? userPage.getTotalResults() : 0;
  }

  @Override
  public Set<String> getParamSet() {
    HashSet<String> params = new HashSet<String>();
    params.add("userName");
    params.add("email");

    return params;
  }

  public UserService getUserService() {
    return userService;
  }

  public Page getUserPage() {
    return userPage;
  }

  public void setUserPage(Page userPage) {
    this.userPage = userPage;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
