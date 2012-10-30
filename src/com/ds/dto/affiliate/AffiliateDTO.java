package com.ds.dto.affiliate;

import com.ds.domain.affiliate.Affiliate;
import com.ds.domain.core.Permission;
import com.ds.domain.core.Role;
import com.ds.domain.user.User;
import com.ds.domain.user.UserSettings;

import java.util.Map;
import java.util.Set;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Oct 23, 2012
 * Time: 2:37:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateDTO {

	private String login;
	private String firstName;
	private String lastName;
	private String email;

	private String state;
	private String zip;
	private String mobile;
	private String address;
	private String city;

	private String passwordChecksum;
	private Boolean deleted;

	private String companyShortName;
	private AffiliateDTO parentAffiliateDTO;
	private Long parentAffiliateId;

	private String originalImageUrl;

	private String thumbnailImageUrl;

	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	//private List<Affiliate> allCompanyAffiliates;

	/**
	 * fields for user settings
	 */
	private boolean sendEmailOnAddAffiliate;
	private boolean sendEmailOnTerminateAffiliate;
	private boolean sendEmailOnJoinAffiliate;
	private boolean sendEmailOnPayout;
	private boolean sendEmailOnGoalConversion;


	private boolean syncRoles = true;

	private String newPassword;      //to be used only for change passwor requests

	public AffiliateDTO(Affiliate affiliate) {
		this.login = affiliate.getLogin();
		this.firstName = affiliate.getFirstName();
		this.lastName = affiliate.getLastName();
		this.email = affiliate.getEmail();

		this.state = affiliate.getState();
		this.zip = affiliate.getZip();
		this.mobile = affiliate.getMobile();
		this.address = affiliate.getAddress();
		this.city = affiliate.getCity();

		this.companyShortName = affiliate.getCompanyShortName();
		if (affiliate.getParentAffiliate() != null) {
			this.parentAffiliateId = affiliate.getParentAffiliate().getId();
		}

	}

	public AffiliateDTO() {
	}

	public Affiliate extractAffiliate(Affiliate affiliate) {

		if(affiliate == null){
			affiliate = new Affiliate();
		}
		affiliate.setLogin(this.login);
		affiliate.setFirstName(this.firstName);
		affiliate.setLastName(this.lastName);
		affiliate.setEmail(this.email);

		affiliate.setState(this.state);
		affiliate.setZip(this.zip);
		affiliate.setMobile(this.mobile);
		affiliate.setAddress(this.address);
		affiliate.setCity(this.city);

		affiliate.setCompanyShortName(this.companyShortName);
		affiliate.setPasswordChecksum(this.passwordChecksum);
		affiliate.setDeleted(Boolean.valueOf(false));


		return affiliate;
	}

	public void bindAffiliate(Affiliate affiliate) {

		this.login = affiliate.getLogin();
		this.firstName = affiliate.getFirstName();
		this.lastName = affiliate.getLastName();
		this.email = affiliate.getEmail();

		this.state = affiliate.getState();
		this.zip = affiliate.getZip();
		this.mobile = affiliate.getMobile();
		this.address = affiliate.getAddress();
		this.city = affiliate.getCity();

		this.companyShortName = affiliate.getCompanyShortName();
		this.passwordChecksum = affiliate.getPasswordChecksum();

		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPasswordChecksum() {
		return passwordChecksum;
	}

	public void setPasswordChecksum(String passwordChecksum) {
		this.passwordChecksum = passwordChecksum;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public Long getParentAffiliateId() {
		return parentAffiliateId;
	}

	public void setParentAffiliateId(Long parentAffiliateId) {
		this.parentAffiliateId = parentAffiliateId;
	}



}
