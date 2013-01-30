package com.ds.dto.affiliate;

import com.ds.domain.affiliate.Affiliate;

/**
 * Created by IntelliJ IDEA.
 * User: Rahul
 * Date: Nov 18, 2012
 * Time: 1:52:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateDTO {

    private Long affiliateId;
    private String login;
    private String firstName;
    private String lastName;
    private String email;

    private String state;
    private String zip;
    private String mobile;
    private String address;
    private String city;

    private String password;

    /*private String passwordChecksum;*/
    private Boolean deleted;

    //private String companyShortName;


    /*private String newPassword;      //to be used only for change passwor requests*/

    /**
     * Accepts null as an affiliate and returns affiliate object accordingly
     *
     * @param affiliate
     * @return
     */
    public Affiliate extractAffiliate(Affiliate affiliate) {

        if (affiliate == null) {
            affiliate = new Affiliate();
        }
        affiliate.setId(this.affiliateId);
        if (this.login != null) {
            affiliate.setLogin(this.login);
        } else {
            affiliate.setLogin(this.email);
        }
        affiliate.setFirstName(this.firstName);
        affiliate.setLastName(this.lastName);
        affiliate.setEmail(this.email);

        affiliate.setState(this.state);
        affiliate.setZip(this.zip);
        affiliate.setMobile(this.mobile);
        affiliate.setAddress(this.address);
        affiliate.setCity(this.city);
        

        //affiliate.setCompanyShortName(this.companyShortName);
        /* affiliate.setPasswordChecksum(this.passwordChecksum);*/
        affiliate.setDeleted(Boolean.valueOf(false));
        //affiliate.getAffiliateCompanies()


        return affiliate;
    }

    public void bindAffiliate(Affiliate affiliate) {

        this.affiliateId = affiliate.getId();
        this.login = affiliate.getLogin();
        this.firstName = affiliate.getFirstName();
        this.lastName = affiliate.getLastName();
        this.email = affiliate.getEmail();

        this.state = affiliate.getState();
        this.zip = affiliate.getZip();
        this.mobile = affiliate.getMobile();
        this.address = affiliate.getAddress();
        this.city = affiliate.getCity();


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

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(Long affiliateId) {
        this.affiliateId = affiliateId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
