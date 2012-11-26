package com.ds.domain.affiliate;
// Generated Oct 19, 2012 1:58:01 AM by Hibernate Tools 3.2.4.CR1


import com.ds.domain.BaseDataObject;
import com.ds.domain.commission.CommissionPlan;

import javax.persistence.*;


@Entity
@Table(name = "affiliate_company_commission_plan")
public class AffiliateCompanyCommissionPlan extends BaseDataObject{


	@Id

	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AFFILIATE_COMPANY_ID", nullable = false)
	private CompanyAffiliate companyAffiliate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMMISSION_PLAN_ID", nullable = false)
	private CommissionPlan commissionPlan;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompanyAffiliate getAffiliateCompany() {
		return this.companyAffiliate;
	}

	public void setAffiliateCompany(CompanyAffiliate companyAffiliate) {
		this.companyAffiliate = companyAffiliate;
	}

	public CommissionPlan getCommissionPlan() {
		return this.commissionPlan;
	}

	public void setCommissionPlan(CommissionPlan commissionPlan) {
		this.commissionPlan = commissionPlan;
	}


}


