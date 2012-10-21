package com.ds.domain.affiliate;
// Generated Oct 19, 2012 1:58:01 AM by Hibernate Tools 3.2.4.CR1


import com.ds.domain.BaseDataObject;
import com.ds.domain.company.Company;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * AffiliateGroup generated by hbm2java
 */
@Entity
@Table(name = "affiliate_group")
public class AffiliateGroup extends BaseDataObject {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "COMPANY_SHORT_NAME", nullable = false)
	private String companyShortName;

	@Column(name = "NAME", nullable = false, length = 45)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "DEFAULT_GROUP", nullable = false)
	private Boolean defaultGroup;

	@Column(name = "DELETE", nullable = false)
	private byte delete;

	/*
	@JsonSkip
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "category_has_product", joinColumns = {
			@JoinColumn(name = "product_id", nullable = false, updatable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "category_name", nullable = false, updatable = false) })
	private List<Category> categories       = new ArrayList<Category>(0);
	*/


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "affiliate_group_affiliate",  joinColumns = {
			@JoinColumn(name = "AFFILIATE_GROUP_ID", nullable = false, updatable = false)}, inverseJoinColumns = {
			@JoinColumn(name = "AFFILIATE_ID", nullable = false, updatable = false)})
	private Set<Affiliate> affiliates = new HashSet<Affiliate>(0);


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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDefaultGroup() {
		return this.defaultGroup;
	}

	public void setDefaultGroup(Boolean defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	public byte getDelete() {
		return this.delete;
	}

	public void setDelete(byte delete) {
		this.delete = delete;
	}

	public Set<Affiliate> getAffiliates() {
		return affiliates;
	}

	public void setAffiliates(Set<Affiliate> affiliates) {
		this.affiliates = affiliates;
	}
}


