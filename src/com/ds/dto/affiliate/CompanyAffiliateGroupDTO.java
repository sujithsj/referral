package com.ds.dto.affiliate;

import com.ds.domain.affiliate.CompanyAffiliateGroup;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 30, 2012
 * Time: 9:47:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateGroupDTO {

	private String companyShortName;
	private Long companyAffiliateGroupId;
	private String description;
	private String name;
	private boolean defaultGroup;
	private boolean deleted;

	public CompanyAffiliateGroup extractCompanyAffiliateGroup(CompanyAffiliateGroup companyAffiliateGroup) {

		if (companyAffiliateGroup == null) {
			companyAffiliateGroup = new CompanyAffiliateGroup();
		}

		if (companyShortName != null) {
			companyAffiliateGroup.setCompanyShortName(companyShortName);
		}

		if (companyAffiliateGroupId != null) {
			companyAffiliateGroup.setId(companyAffiliateGroupId);
		}

		if (description != null) {
			companyAffiliateGroup.setDescription(description);
		}

		if (name != null) {
			companyAffiliateGroup.setName(name);
		}

		companyAffiliateGroup.setDeleted(deleted);
		companyAffiliateGroup.setDefaultGroup(defaultGroup);
		return companyAffiliateGroup;

	}


	public void bindCompanyAffiliateGroup(CompanyAffiliateGroup companyAffiliateGroup) {
		if (companyAffiliateGroup != null){
			companyAffiliateGroupId = companyAffiliateGroup.getId();
			companyShortName = companyAffiliateGroup.getCompanyShortName();
			description = companyAffiliateGroup.getDescription();
			name = companyAffiliateGroup.getName();
			defaultGroup = companyAffiliateGroup.getDefaultGroup();
			deleted = companyAffiliateGroup.getDeleted();
		}
	}
	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDefaultGroup() {
		return defaultGroup;
	}


	public boolean getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(boolean defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getCompanyAffiliateGroupId() {
		return companyAffiliateGroupId;
	}

	public void setCompanyAffiliateGroupId(Long companyAffiliateGroupId) {
		this.companyAffiliateGroupId = companyAffiliateGroupId;
	}
}
