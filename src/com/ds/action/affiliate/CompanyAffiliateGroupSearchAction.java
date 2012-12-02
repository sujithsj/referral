package com.ds.action.affiliate;

import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import com.ds.domain.user.User;
import com.ds.domain.affiliate.CompanyAffiliateGroup;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.pact.service.affiliate.CompanyAffiliateGroupService;
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

	private Page companyAffiliateGroupPage;
	private List<CompanyAffiliateGroup> companyAffiliateGroups;
	private int numberOfAffiliatesInGroup;
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyAffiliateGroupService companyAffiliateGroupService;


	@DefaultHandler
	@SuppressWarnings("unchecked")
	public Resolution searchCompanyAffiliateGroups() {

		User user = SecurityHelper.getLoggedInUser();
		companyShortName = user.getCompanyShortName();
		companyAffiliateGroupPage = getCompanyAffiliateGroupService().searchCompanyAffiliateGroup(name, companyShortName, getPageNo(), getPerPage());
		companyAffiliateGroups = companyAffiliateGroupPage.getList();

		return new ForwardResolution("/pages/affiliate/companyAffiliateGroups.jsp");

	}

	@Override
	public int getPageCount() {
		return companyAffiliateGroupPage != null ? companyAffiliateGroupPage.getTotalPages() : 0;
	}

	@Override
	public int getResultCount() {
		return companyAffiliateGroupPage != null ? companyAffiliateGroupPage.getTotalResults() : 0;
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

	public Page getCompanyAffiliateGroupPage() {
		return companyAffiliateGroupPage;
	}

	public void setCompanyAffiliateGroupPage(Page companyAffiliateGroupPage) {
		this.companyAffiliateGroupPage = companyAffiliateGroupPage;
	}

	public List<CompanyAffiliateGroup> getCompanyAffiliateGroups() {
		return companyAffiliateGroups;
	}

	public void setCompanyAffiliateGroups(List<CompanyAffiliateGroup> companyAffiliateGroups) {
		this.companyAffiliateGroups = companyAffiliateGroups;
	}

	public CompanyAffiliateGroupService getCompanyAffiliateGroupService() {
		return companyAffiliateGroupService;
	}

	public void setCompanyAffiliateGroupService(CompanyAffiliateGroupService companyAffiliateGroupService) {
		this.companyAffiliateGroupService = companyAffiliateGroupService;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public int getNumberOfAffiliatesInGroup() {
		return numberOfAffiliatesInGroup;
	}

	public void setNumberOfAffiliatesInGroup(int numberOfAffiliatesInGroup) {
		this.numberOfAffiliatesInGroup = numberOfAffiliatesInGroup;
	}
}
