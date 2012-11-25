package com.ds.dto.affiliate;

import com.ds.domain.affiliate.CompanyAffiliate;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 23, 2012
 * Time: 2:37:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateDTO {

	private Long parentCompanyAffiliateId;
	private Long affiliateId;
	private String companyShortName;
	private Long companyAffiliateId;

	public CompanyAffiliate extractCompanyAffiliate(CompanyAffiliate companyAffiliate) {
		
		if (companyAffiliate == null) {
			companyAffiliate = new CompanyAffiliate();
		}
		if (companyShortName != null) {
			companyAffiliate.setCompanyShortName(companyShortName);
		}
		if (companyAffiliateId != null) {
			companyAffiliate.setId(companyAffiliateId);
		}
		

		return companyAffiliate;
	}

	public void bindCompanyAffiliate(CompanyAffiliate companyAffiliate) {

		if (companyAffiliate.getId() != null){
			companyAffiliateId = companyAffiliate.getId();
		}
		if (companyAffiliate.getParentCompanyAffiliate() != null) {
			parentCompanyAffiliateId = companyAffiliate.getParentCompanyAffiliate().getId();
		}
		if (companyAffiliate.getAffiliate() != null) {
			affiliateId = companyAffiliate.getAffiliate().getId();
		}
		companyShortName = companyAffiliate.getCompanyShortName();
	}

	public Long getParentCompanyAffiliateId() {
		return parentCompanyAffiliateId;
	}

	public void setParentCompanyAffiliateId(Long parentCompanyAffiliateId) {
		this.parentCompanyAffiliateId = parentCompanyAffiliateId;
	}

	public Long getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}

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
}
