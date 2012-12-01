package com.ds.dto.affiliate;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 30, 2012
 * Time: 9:47:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateGroupDTO {

	private String companyShortName;
	private Long companyAffiliateId;
	private String description;
	private String name;
	private boolean defaultGroup;
	private boolean deleted;

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public Long getCompanyAffiliateId() {
		return companyAffiliateId;
	}

	public void setCompanyAffiliateId(Long companyAffiliateId) {
		this.companyAffiliateId = companyAffiliateId;
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
}
