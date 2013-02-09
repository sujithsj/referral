package com.ds.dto.dashboard;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 10, 2013
 * Time: 12:53:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateByCommissionDto {

    private String affiliateLogin;
    private double commission;


    public String getAffiliateLogin() {
        return affiliateLogin;
    }

    public void setAffiliateLogin(String affiliateLogin) {
        this.affiliateLogin = affiliateLogin;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
