package com.ds.constants;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Feb 9, 2013
 * Time: 2:30:30 PM
 * To change this template use File | Settings | File Templates.
 */
public enum EnumCompanyAffiliateSource {

    REFERAL(10L, "Social Referal");


    private java.lang.String type;

    private java.lang.Long id;

    EnumCompanyAffiliateSource(Long id, java.lang.String type) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public java.lang.Long getId() {
        return id;
    }

}
