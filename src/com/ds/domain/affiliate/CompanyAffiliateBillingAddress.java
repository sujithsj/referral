package com.ds.domain.affiliate;

import com.ds.domain.BaseDataObject;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Dec 8, 2012
 * Time: 7:22:35 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "company_affiliate_billing_address")
public class CompanyAffiliateBillingAddress extends BaseDataObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANY_AFFILIATE_ID", nullable = false)
	private CompanyAffiliate companyAffiliate;

	@Column(name = "LINE1", nullable = false, length = 45)
	private String line1;

	@Column(name = "LINE2", nullable = true, length = 45)
	private String  line2;

	@Column(name = "CITY", nullable = true, length = 45)
	private String  city;

	@Column(name = "COUNTRY", nullable = true, length = 45)
	private String  country;

	@Column(name = "STATE", nullable = true, length = 45)
	private String  state;

	@Column(name = "PINCODE", nullable = true, length = 45)
	private String  pincode;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompanyAffiliate getCompanyAffiliate() {
		return companyAffiliate;
	}

	public void setCompanyAffiliate(CompanyAffiliate companyAffiliate) {
		this.companyAffiliate = companyAffiliate;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}


