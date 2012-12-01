package com.ds.action.affiliate;

import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.user.User;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.security.helper.SecurityHelper;
import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import net.sourceforge.stripes.action.DefaultHandler;
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
public class CompanyAffiliateSearchAction extends BasePaginatedAction {

	private String login;
	private String email;
	private String companyShortName;

	private Page companyAffiliatePage;
	private List<CompanyAffiliate> companyAffiliates;

	@Autowired
	private CompanyAffiliateService companyAffiliateService;


	@DefaultHandler
	@SuppressWarnings("unchecked")
	public Resolution searchCompanyAffiliates() {

		User loggedInUser = SecurityHelper.getLoggedInUser();
		companyShortName = loggedInUser.getCompanyShortName();

		companyAffiliatePage = getCompanyAffiliateService().searchCompanyAffiliate(login, email, companyShortName, getPageNo(), getPerPage());
		companyAffiliates = companyAffiliatePage.getList();

		return new ForwardResolution("/pages/affiliate/companyAffiliates.jsp");

	}

	@Override
	public int getPageCount() {
		return companyAffiliatePage != null ? companyAffiliatePage.getTotalPages() : 0;
	}

	@Override
	public int getResultCount() {
		return companyAffiliatePage != null ? companyAffiliatePage.getTotalResults() : 0;
	}

	@Override
	public Set<String> getParamSet() {
		HashSet<String> params = new HashSet<String>();
		params.add("login");
		params.add("email");

		return params;
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

	public List<CompanyAffiliate> getCompanyAffiliates() {
		return companyAffiliates;
	}

	public void setCompanyAffiliates(List<CompanyAffiliate> companyAffiliates) {
		this.companyAffiliates = companyAffiliates;
	}
}
