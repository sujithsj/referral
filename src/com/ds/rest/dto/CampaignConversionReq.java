package com.ds.rest.dto;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 28, 2013
 * Time: 8:33:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CampaignConversionReq {


    private String rf_c_short_code; //your company's username
    private String rf_campaign_uid;  //your campaign's ID  , can be a comma separated list
    private String rf_u_email;        //your customer's email address
    private String rf_u_f_name;
    private String rf_u_l_name;
    private Double rf_revenue;
    private String rf_tx_uid;


    private boolean rf_email_new_referrer = true;
    private boolean rf_disable_new_referrer = false;
    private boolean rf_auto_create = true;


    /**
     * TODO: support later
     */
    private String rf_uid; //	your customer's internal unique ID
    private String rf_grp_id;       //comma separated for addition to multiple groups
    private String rf_custom_param_1;
    private String rf_custom_param_2;
    private String rf_custom_param_3;

    public String getRf_c_short_code() {
        return rf_c_short_code;
    }

    public void setRf_c_short_code(String rf_c_short_code) {
        this.rf_c_short_code = rf_c_short_code;
    }

    public String getRf_campaign_uid() {
        return rf_campaign_uid;
    }

    public void setRf_campaign_uid(String rf_campaign_uid) {
        this.rf_campaign_uid = rf_campaign_uid;
    }

    public String getRf_u_email() {
        return rf_u_email;
    }

    public void setRf_u_email(String rf_u_email) {
        this.rf_u_email = rf_u_email;
    }

    public String getRf_u_f_name() {
        return rf_u_f_name;
    }

    public void setRf_u_f_name(String rf_u_f_name) {
        this.rf_u_f_name = rf_u_f_name;
    }

    public String getRf_u_l_name() {
        return rf_u_l_name;
    }

    public void setRf_u_l_name(String rf_u_l_name) {
        this.rf_u_l_name = rf_u_l_name;
    }

    public Double getRf_revenue() {
        return rf_revenue;
    }

    public void setRf_revenue(Double rf_revenue) {
        this.rf_revenue = rf_revenue;
    }

    public String getRf_tx_uid() {
        return rf_tx_uid;
    }

    public void setRf_tx_uid(String rf_tx_uid) {
        this.rf_tx_uid = rf_tx_uid;
    }

    public boolean isRf_email_new_referrer() {
        return rf_email_new_referrer;
    }

    public void setRf_email_new_referrer(boolean rf_email_new_referrer) {
        this.rf_email_new_referrer = rf_email_new_referrer;
    }

    public boolean isRf_disable_new_referrer() {
        return rf_disable_new_referrer;
    }

    public void setRf_disable_new_referrer(boolean rf_disable_new_referrer) {
        this.rf_disable_new_referrer = rf_disable_new_referrer;
    }

    public boolean isRf_auto_create() {
        return rf_auto_create;
    }

    public void setRf_auto_create(boolean rf_auto_create) {
        this.rf_auto_create = rf_auto_create;
    }

    public String getRf_uid() {
        return rf_uid;
    }

    public void setRf_uid(String rf_uid) {
        this.rf_uid = rf_uid;
    }

    public String getRf_grp_id() {
        return rf_grp_id;
    }

    public void setRf_grp_id(String rf_grp_id) {
        this.rf_grp_id = rf_grp_id;
    }

    public String getRf_custom_param_1() {
        return rf_custom_param_1;
    }

    public void setRf_custom_param_1(String rf_custom_param_1) {
        this.rf_custom_param_1 = rf_custom_param_1;
    }

    public String getRf_custom_param_2() {
        return rf_custom_param_2;
    }

    public void setRf_custom_param_2(String rf_custom_param_2) {
        this.rf_custom_param_2 = rf_custom_param_2;
    }

    public String getRf_custom_param_3() {
        return rf_custom_param_3;
    }

    public void setRf_custom_param_3(String rf_custom_param_3) {
        this.rf_custom_param_3 = rf_custom_param_3;
    }
}
