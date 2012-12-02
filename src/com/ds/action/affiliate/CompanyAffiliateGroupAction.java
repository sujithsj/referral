package com.ds.action.affiliate;

import com.ds.api.CacheAPI;
import com.ds.api.FeatureAPI;
import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;
import com.ds.domain.affiliate.CompanyAffiliateGroup;
import com.ds.domain.company.Company;
import com.ds.domain.user.User;
import com.ds.dto.affiliate.CompanyAffiliateDTO;
import com.ds.dto.affiliate.CompanyAffiliateGroupDTO;
import com.ds.dto.user.UserDTO;
import com.ds.exception.InvalidParameterException;
import com.ds.exception.ValidationException;
import com.ds.pact.dao.BaseDao;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.affiliate.CompanyAffiliateGroupService;
import com.ds.pact.service.affiliate.CompanyAffiliateService;
import com.ds.security.api.SecurityAPI;
import com.ds.security.helper.SecurityHelper;
import com.ds.security.service.UserService;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 22, 2012
 * Time: 12:00:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateGroupAction extends BaseAction {

	private String DEFAULT_USER_PWD = "password";

	private String companyShortName;
	private UserDTO userDTO;
	private CompanyAffiliateDTO companyAffiliateDTO;
	private CompanyAffiliateGroupDTO companyAffiliateGroupDTO;
	private List<CompanyAffiliate> allCompanyAffiliates;
	private List<CompanyAffiliate> assignedCompanyAffiliates;
	private List<Long> assignedAffiliates = new ArrayList<Long>(0);
	private List<Long> availableAffiliates = new ArrayList<Long>(0);
	private List<Long> allAffiliates = new ArrayList<Long>(0);
	private Map<Long, String> companyAffiliateDescMap = new HashMap<Long, String>();
	

	private Long companyAffiliateGroupId;
	private String roleName;
	private String employeeEmail;

	private Set<Affiliate> companyAffiliates;
	private Long parentAffiliateId;
	private User loggedInUser;
	private Company company;

	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyAffiliateGroupService companyAffiliateGroupService;
	@Autowired
	private CompanyAffiliateService companyAffiliateService;
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
	public Resolution createOrEditCompanyAffiliateGroup() {
		CompanyAffiliateGroup companyAffiliateGroup = null;
		loggedInUser = SecurityHelper.getLoggedInUser();
		companyShortName = loggedInUser.getCompanyShortName();
		company = getAdminService().getCompany(companyShortName);
		if (companyAffiliateGroupDTO == null) {
			companyAffiliateGroupDTO = new CompanyAffiliateGroupDTO();
		}
		if (companyAffiliateGroupId != null) {
			companyAffiliateGroup = getCompanyAffiliateGroupService().getCompanyAffiliateGroup(companyAffiliateGroupId);
			companyAffiliateGroupDTO.bindCompanyAffiliateGroup(companyAffiliateGroup);
		}
		return setParamsForView(companyAffiliateGroup, companyAffiliateGroupDTO, company);
		//return new ForwardResolution("/pages/affiliate/companyAffiliateCrud.jsp").addParameter("companyAffiliateGroupId", companyAffiliateGroupId);
	}

	@SuppressWarnings("unchecked")
	private Resolution setParamsForView(CompanyAffiliateGroup companyAffiliateGroup, CompanyAffiliateGroupDTO companyAffiliateGroupDTO, Company comppany) {

		allCompanyAffiliates = getCompanyAffiliateService().getAllCompanyAffiliates(companyShortName);
		if (companyAffiliateGroup != null) {
			assignedCompanyAffiliates = companyAffiliateGroup.getCompanyAffiliates();
		}
		if (allCompanyAffiliates != null && allCompanyAffiliates.size() > 0) {
			if (assignedCompanyAffiliates != null && assignedCompanyAffiliates.size() > 0) {
				for (CompanyAffiliate companyAffiliate : assignedCompanyAffiliates) {
					assignedAffiliates.add(companyAffiliate.getId());
				}
				for (CompanyAffiliate companyAffiliate : (List<CompanyAffiliate>) CollectionUtils.subtract(allCompanyAffiliates, assignedCompanyAffiliates)) {
					availableAffiliates.add(companyAffiliate.getId());
				}
			} else {
				for (CompanyAffiliate companyAffiliate : allCompanyAffiliates) {
					availableAffiliates.add(companyAffiliate.getId());
				}
			}
		}

		for (CompanyAffiliate companyAffiliate : allCompanyAffiliates) {
			String readableName = companyAffiliate.getAffiliate().getFirstName();
			String lastName = companyAffiliate.getAffiliate().getLastName();
			if (lastName != null){
				readableName += " " + lastName;
			}
			companyAffiliateDescMap.put(companyAffiliate.getId(), readableName);
		}

		return new ForwardResolution("/pages/affiliate/companyAffiliateGroupCrud.jsp");
	}


	public Resolution saveCompanyAffiliateGroup() {
		CompanyAffiliateGroup companyAffiliateGroup;
		try {
			companyAffiliateGroup = getCompanyAffiliateGroupService().createOrUpdateCompanyAffiliateGroup(companyAffiliateGroupDTO, companyShortName, assignedAffiliates);
		} catch (ValidationException vae) {
			addRedirectAlertMessage(new SimpleMessage(vae.getMessage()));
			return new RedirectResolution(CompanyAffiliateGroupAction.class, "createOrEditCompanyAffiliateGroup");
		} catch (InvalidParameterException ipe) {
			addRedirectAlertMessage(new SimpleMessage(ipe.getMessage()));
			return new RedirectResolution(CompanyAffiliateGroupAction.class, "createOrEditCompanyAffiliateGroup");
		}

		if (companyAffiliateGroup == null) {
			addRedirectAlertMessage(new SimpleMessage("Could not find/create affiliate group"));
			return new RedirectResolution(CompanyAffiliateGroupAction.class, "createOrEditCompanyAffiliateGroup");

		} else {
			addRedirectAlertMessage(new SimpleMessage("Affiliate Updated Successfully"));
			return new RedirectResolution(CompanyAffiliateGroupSearchAction.class, "searchCompanyAffiliateGroups");

		}
	}

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

	public CompanyAffiliateDTO getAffiliateDTO() {
		return companyAffiliateDTO;
	}

	public void setAffiliateDTO(CompanyAffiliateDTO companyAffiliateDTO) {
		this.companyAffiliateDTO = companyAffiliateDTO;
	}

	public Long getCompanyAffiliateGroupId() {
		return companyAffiliateGroupId;
	}

	public void setCompanyAffiliateGroupId(Long companyAffiliateGroupId) {
		this.companyAffiliateGroupId = companyAffiliateGroupId;
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

	public CompanyAffiliateGroupService getCompanyAffiliateGroupService() {
		return companyAffiliateGroupService;
	}

	public void setCompanyAffiliateGroupService(CompanyAffiliateGroupService companyAffiliateGroupService) {
		this.companyAffiliateGroupService = companyAffiliateGroupService;
	}

	public CompanyAffiliateService getCompanyAffiliateService() {
		return companyAffiliateService;
	}

	public void setCompanyAffiliateService(CompanyAffiliateService companyAffiliateService) {
		this.companyAffiliateService = companyAffiliateService;
	}

	public Map<Long, String> getCompanyAffiliateDescMap() {
		return companyAffiliateDescMap;
	}

	public void setCompanyAffiliateDescMap(Map<Long, String> companyAffiliateDescMap) {
		this.companyAffiliateDescMap = companyAffiliateDescMap;
	}

	public List<Long> getAllAffiliates() {
		return allAffiliates;
	}

	public void setAllAffiliates(List<Long> allAffiliates) {
		this.allAffiliates = allAffiliates;
	}

	public List<Long> getAvailableAffiliates() {
		return availableAffiliates;
	}

	public void setAvailableAffiliates(List<Long> availableAffiliates) {
		this.availableAffiliates = availableAffiliates;
	}

	public List<Long> getAssignedAffiliates() {
		return assignedAffiliates;
	}

	public void setAssignedAffiliates(List<Long> assignedAffiliates) {
		this.assignedAffiliates = assignedAffiliates;
	}

	public List<CompanyAffiliate> getAssignedCompanyAffiliates() {
		return assignedCompanyAffiliates;
	}

	public void setAssignedCompanyAffiliates(List<CompanyAffiliate> assignedCompanyAffiliates) {
		this.assignedCompanyAffiliates = assignedCompanyAffiliates;
	}

	public List<CompanyAffiliate> getAllCompanyAffiliates() {
		return allCompanyAffiliates;
	}

	public void setAllCompanyAffiliates(List<CompanyAffiliate> allCompanyAffiliates) {
		this.allCompanyAffiliates = allCompanyAffiliates;
	}

	public CompanyAffiliateGroupDTO getCompanyAffiliateGroupDTO() {
		return companyAffiliateGroupDTO;
	}

	public void setCompanyAffiliateGroupDTO(CompanyAffiliateGroupDTO companyAffiliateGroupDTO) {
		this.companyAffiliateGroupDTO = companyAffiliateGroupDTO;
	}

}
