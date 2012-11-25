package com.ds.action.affiliate;

import com.ds.api.CacheAPI;
import com.ds.api.FeatureAPI;
import com.ds.constants.FeatureType;
import com.ds.domain.affiliate.Affiliate;
import com.ds.pact.service.affiliate.AffiliateService;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.dto.user.UserDTO;
import com.ds.exception.CompositeValidationException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.admin.AdminService;
import com.ds.security.api.SecurityAPI;
import com.ds.security.helper.SecurityHelper;
import com.ds.security.service.UserService;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:00:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateGroupAction extends BaseAction {

	private String DEFAULT_USER_PWD = "password";

	private String companyShortName;
	private UserDTO userDTO;
	private CompanyAffiliateDTO companyAffiliateDTO;

	//private String employeeId;
	private Long affiliateId;
	private Long affiliateGroupId;
	private String roleName;
	private String employeeEmail;

	private Set<Affiliate> companyAffiliates;
	private Long parentAffiliateId;
	private User loggedInUser;
	private Company company;

	@Autowired
	private AdminService adminService;
	@Autowired
	private AffiliateService affiliateService;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private FeatureAPI featureAPI;
	@Autowired
	private SecurityAPI securityAPI;
	@Autowired
	private UserService userService;
	@Autowired
	private CacheAPI cacheAPI;


	@DefaultHandler
	public Resolution createOrEditAffiliateGroup() {
		loggedInUser = SecurityHelper.getLoggedInUser();
		companyShortName = loggedInUser.getCompanyShortName();
		company = getAdminService().getCompany(companyShortName);
		//companyAffiliates = company.getAffiliates();
		//companyAffiliates = new HashSet<Affiliate>(company.getAffiliates());

		if (affiliateId != null) {
			Affiliate affiliate = getAffiliateService().getAffiliate(affiliateId);
			companyAffiliates.remove(affiliate);
			//UserSettings userSettings = getUserService().getUserSettings(user.getUsername());
			companyAffiliateDTO = new CompanyAffiliateDTO();
			//companyAffiliateDTO.bindCompanyAffiliate(affiliate);
		} else {
			companyAffiliateDTO = createNewAffiliate();
		}
		return setParamsForView(companyAffiliateDTO);
		//return new ForwardResolution("/pages/affiliate/companyAffiliateCrud.jsp").addParameter("affiliateId", affiliateId);
	}

	@SuppressWarnings("unchecked")
	private Resolution setParamsForView(CompanyAffiliateDTO companyAffiliateDTO) {

		/*Set<Affiliate> affiliateSet = new HashSet<Affiliate>();
		affiliateSet = company.getAffiliates();
		Set<CompanyAffiliate> afiAffiliateCompanies = new HashSet<CompanyAffiliate>();
		afiAffiliateCompanies = company.getAffiliateCompanies();*/
		return new ForwardResolution("/pages/affiliate/companyAffiliateCrud.jsp").addParameter("affiliateId", affiliateId);
	}

	public CompanyAffiliateDTO createNewAffiliate() {

		//Company company = getAdminService().getCompany(companyShortName);
		getFeatureAPI().doesCompanyHaveAccessTo(company, FeatureType.AFFILIATE_COUNT, getAffiliateService().affiliatesCount(companyShortName) + 1);

		companyAffiliateDTO = new CompanyAffiliateDTO();
		//companyAffiliateDTO.setCompanyShortName(companyShortName);
		return companyAffiliateDTO;
	}


	public Resolution updateAffiliate() {

		return updateAffiliateDetails(companyAffiliateDTO, affiliateId);
	}

	private Resolution updateAffiliateDetails(CompanyAffiliateDTO companyAffiliateDTO, Long affiliateId) {
		Affiliate affiliate = null;
		boolean existingAffiliate = true;
		if (affiliateId != null) {
			affiliate = getAffiliateService().getAffiliate(affiliateId);
		}
		if (affiliate == null) {
			existingAffiliate = false;
		}
		//affiliate = companyAffiliateDTO.extractCompanyAffiliate(affiliate);
		System.out.println("affiliate about to be saved -> " + affiliate.getLogin());
		try {
			affiliate = getAffiliateService().saveNewAffiliate(affiliate);
		} catch (CompositeValidationException cve) {
			//return
			cve.printStackTrace();
		}
		if (!existingAffiliate) {
			getAffiliateService().saveAffiliateCompany(affiliate, companyShortName);
			getAffiliateService().sendWelcomeEmail(affiliate);
		}

		//UserSettings userSettings = userDTOForUpdate.extactUserSettings();
		return new ForwardResolution("/pages/affiliate/companyAffiliates.jsp");
	}

	public Resolution resetPassword() {
		getAdminService().resetEmployeePassword(employeeEmail);
		return new ForwardResolution("/pages/setup.jsp");
	}

	public Resolution resendWelcomeEmail() {
		Affiliate affiliate = getAffiliateService().getAffiliate(affiliateId);
		getAffiliateService().sendWelcomeEmail(affiliate);
		return new ForwardResolution("/pages/setup.jsp");
	}


	/*public Resolution deleteEmployee() {
		getAdminService().deleteEmployee(employeeId);
		return new ForwardResolution("/pages/setup.jsp");
	}*/


	/*public Resolution deleteEmployeeRole() {
		User user = getAdminService().getUser(employeeId);
		if (user != null) {
			getSecurityAPI().revokeRolesFromUser(user, "admin".equals(roleName) ? Role.RoleType.admin : Role.RoleType.moderator);
			getCacheAPI().remove(CacheAPI.CacheConfig.USER_CACHE, employeeId);

		}

		return new ForwardResolution("/pages/setup.jsp");
	}*/

	public AdminService getAdminService() {
		return adminService;
	}

	public FeatureAPI getFeatureAPI() {
		return featureAPI;
	}

	public UserService getUserService() {
		return userService;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public SecurityAPI getSecurityAPI() {
		return securityAPI;
	}

/*
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
*/

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public CacheAPI getCacheAPI() {
		return cacheAPI;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public AffiliateService getAffiliateService() {
		return affiliateService;
	}

	public void setAffiliateService(AffiliateService affiliateService) {
		this.affiliateService = affiliateService;
	}

	public CompanyAffiliateDTO getAffiliateDTO() {
		return companyAffiliateDTO;
	}

	public void setAffiliateDTO(CompanyAffiliateDTO companyAffiliateDTO) {
		this.companyAffiliateDTO = companyAffiliateDTO;
	}

	public Long getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}

	public Set<Affiliate> getCompanyAffiliates() {
		return companyAffiliates;
	}

	public void setCompanyAffiliates(Set<Affiliate> companyAffiliates) {
		this.companyAffiliates = companyAffiliates;
	}

	public Long getParentAffiliateId() {
		return parentAffiliateId;
	}

	public void setParentAffiliateId(Long parentAffiliateId) {
		this.parentAffiliateId = parentAffiliateId;
	}
}
