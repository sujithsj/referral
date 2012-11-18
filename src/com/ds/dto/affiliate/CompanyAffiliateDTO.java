package com.ds.dto.affiliate;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.affiliate.CompanyAffiliate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 23, 2012
 * Time: 2:37:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyAffiliateDTO {

	private AffiliateDTO affiliateDTO;
	private Long parentCompanyAffiliateId;

	public CompanyAffiliateDTO(CompanyAffiliate companyAffiliate) {

		//Affiliate affiliate = companyAffiliate.getAffiliate();
		//this.affiliateDTO = AffiliateDTO(affiliate);

		//this.companyShortName = affiliate.getCompanyShortName();
		/*if (affiliate.getParentAffiliate() != null) {
			this.parentAffiliateId = affiliate.getParentAffiliate().getId();
		}*/

	}

	public CompanyAffiliateDTO() {
	}

	public CompanyAffiliate extractCompanyAffiliate(CompanyAffiliate companyAffiliate) {

		if(companyAffiliate == null){
			companyAffiliate = new CompanyAffiliate();
		}
		if(parentCompanyAffiliateId != null){
			companyAffiliate.setParentCompanyAffiliate(get);
		}
		//companyAffiliate

		return companyAffiliate;
	}

	public void bindCompanyAffiliate(CompanyAffiliate companyAffiliate) {

		affiliateDTO = new AffiliateDTO();
		affiliateDTO.bindAffiliate(companyAffiliate.getAffiliate());
		if(companyAffiliate.getParentCompanyAffiliate() != null){
			parentCompanyAffiliateId = companyAffiliate.getParentCompanyAffiliate().getId();
		}

		
	}

	public Long getParentCompanyAffiliateId() {
		return parentCompanyAffiliateId;
	}

	public void setParentCompanyAffiliateId(Long parentCompanyAffiliateId) {
		this.parentCompanyAffiliateId = parentCompanyAffiliateId;
	}

	public AffiliateDTO getAffiliateDTO() {
		return affiliateDTO;
	}

	public void setAffiliateDTO(AffiliateDTO affiliateDTO) {
		this.affiliateDTO = affiliateDTO;
	}
}
