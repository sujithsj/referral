package com.ds.dto.affiliate;

import com.ds.domain.affiliate.Affiliate;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 30, 2013
 * Time: 11:11:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffiliateSignupResponse {

    private Affiliate affiliate;

    public Affiliate getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
    }
}
