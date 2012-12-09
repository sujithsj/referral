package com.ds.domain.affiliate;

import com.ds.domain.BaseDataObject;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 8, 2012
 * Time: 5:06:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "company_affiliate_invite")
public class CompanyAffiliateInvite extends BaseDataObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "COMPANY_SHORTNAME", nullable = false)
	private String companyShortName;

	@Column(name = "AFFILIATE_EMAIL", nullable = false, length = 45)
	private String affiliateEmail;

	@Column(name = "CONVERTED", nullable = false)
	private Boolean converted = false;

	//to be used when the company wants to delete an invite which it might not want to track at all.
	@Column(name = "DELETED", nullable = false)
	private Boolean deleted = false;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public String getAffiliateEmail() {
		return affiliateEmail;
	}

	public void setAffiliateEmail(String affiliateEmail) {
		this.affiliateEmail = affiliateEmail;
	}

	public Boolean isConverted() {
		return converted;
	}

	public Boolean getConverted() {
		return converted;
	}

	public void setConverted(Boolean converted) {
		this.converted = converted;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
