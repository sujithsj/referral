package com.ds.action.affiliate;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.user.User;
import com.ds.security.service.UserService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import com.ds.pact.service.admin.AffiliateService;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 21, 2012
 * Time: 11:52:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateSearchAction extends BasePaginatedAction {

	private String login;
	private String email;
	private String companyShortName;

	private Page userPage;
	private Page affiliatePage;
	private List<User> users;
	private List<Affiliate> affiliates;

	@Autowired
	private UserService userService;
	@Autowired
	private AffiliateService affiliateService;


	@SuppressWarnings("unchecked")
	public Resolution searchUsers() {
		User loggedInUser = SecurityHelper.getLoggedInUser();
	  companyShortName = loggedInUser.getCompanyShortName();
		affiliatePage = getAffiliateService().searchAffiliate(login, email, companyShortName, getPageNo(), getPerPage());
		affiliates = affiliatePage.getList();

		return new ForwardResolution("/pages/affiliate/affiliates.jsp");

	}

	@SuppressWarnings("unchecked")
	public Resolution searchAffiliates() {

		User user = SecurityHelper.getLoggedInUser();
		companyShortName = user.getCompanyShortName();
		affiliatePage = getAffiliateService().searchAffiliate(login, email, companyShortName, getPageNo(), getPerPage());
		affiliates = affiliatePage.getList();

		return new ForwardResolution("/pages/affiliate/affiliates.jsp");

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
		params.add("login");
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Affiliate> getAffiliates() {
		return affiliates;
	}

	public void setAffiliates(List<Affiliate> affiliates) {
		this.affiliates = affiliates;
	}

	public Page getAffiliatePage() {
		return affiliatePage;
	}

	public void setAffiliatePage(Page affiliatePage) {
		this.affiliatePage = affiliatePage;
	}

	public AffiliateService getAffiliateService() {
		return affiliateService;
	}

	public void setAffiliateService(AffiliateService affiliateService) {
		this.affiliateService = affiliateService;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}
}
