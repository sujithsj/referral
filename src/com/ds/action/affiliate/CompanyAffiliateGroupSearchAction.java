package com.ds.action.affiliate;

import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import com.ds.domain.user.User;
import com.ds.domain.affiliate.CompanyAffiliateGroup;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.security.service.UserService;
import com.ds.security.helper.SecurityHelper;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.DefaultHandler;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 17, 2012
 * Time: 6:01:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateGroupSearchAction extends BasePaginatedAction {

	private String login;
	private String name;
	private String companyShortName;

	private Page userPage;
	//private Page affiliatePage;
	private Page affiliateGroupPage;
	private List<User> users;
	//private List<Affiliate> affiliates;
	private List<CompanyAffiliateGroup> companyAffiliateGroups;

	@Autowired
	private UserService userService;
	@Autowired
	private CompanyAffiliateService companyAffiliateService;


	@DefaultHandler
	@SuppressWarnings("unchecked")
	public Resolution searchCompanyAffiliateGroups() {

		User user = SecurityHelper.getLoggedInUser();
		companyShortName = user.getCompanyShortName();
		affiliateGroupPage = getCompanyAffiliateService().searchCompanyAffiliateGroup(name, companyShortName, getPageNo(), getPerPage());
		companyAffiliateGroups = affiliateGroupPage.getList();

		return new ForwardResolution("/pages/affiliate/companyAffiliateGroups.jsp");

	}

	@Override
	public int getPageCount() {
		return affiliateGroupPage != null ? affiliateGroupPage.getTotalPages() : 0;
	}

	@Override
	public int getResultCount() {
		return affiliateGroupPage != null ? affiliateGroupPage.getTotalResults() : 0;
	}

	@Override
	public Set<String> getParamSet() {
		HashSet<String> params = new HashSet<String>();
		params.add("name");

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Page getAffiliateGroupPage() {
		return affiliateGroupPage;
	}

	public void setAffiliateGroupPage(Page affiliateGroupPage) {
		this.affiliateGroupPage = affiliateGroupPage;
	}

	public List<CompanyAffiliateGroup> getCompanyAffiliateGroups() {
		return companyAffiliateGroups;
	}

	public void setCompanyAffiliateGroups(List<CompanyAffiliateGroup> companyAffiliateGroups) {
		this.companyAffiliateGroups = companyAffiliateGroups;
	}

	public CompanyAffiliateService getCompanyAffiliateService() {
		return companyAffiliateService;
	}

	public void setCompanyAffiliateService(CompanyAffiliateService companyAffiliateService) {
		this.companyAffiliateService = companyAffiliateService;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}
}
