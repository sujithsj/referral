package com.ds.constants;

import com.ds.domain.campaign.CampaignType;
import com.ds.domain.commission.CommissionEarningStatus;

import java.util.List;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 24, 2013
 * Time: 2:08:09 AM
 * To change this template use File | Settings | File Templates.
 */
public enum EnumCommissionEarningStatus {

    ALL(-999L, "All"), //to be used only on UI 

    PENDING_APPROVAL(10L, "Pending For Approval"),
    APPROVED(50L, "Approved"),
    REJECTED(100L, "Rejected");

    private java.lang.String status;

    private java.lang.Long id;

    EnumCommissionEarningStatus(Long id, java.lang.String status) {
        this.status = status;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public java.lang.Long getId() {
        return id;
    }


    public static List<EnumCommissionEarningStatus> getAllCommissionEarningStatus() {
        return Arrays.asList(
                EnumCommissionEarningStatus.PENDING_APPROVAL,
                EnumCommissionEarningStatus.APPROVED,
                EnumCommissionEarningStatus.REJECTED
        );

    }


    public CommissionEarningStatus asCommissionEarningStatus() {
        CommissionEarningStatus commissionEarningStatus = new CommissionEarningStatus();
        commissionEarningStatus.setId(this.getId());
        commissionEarningStatus.setStatus(this.getStatus());
        return commissionEarningStatus;
    }

    public static EnumCommissionEarningStatus getById(Long statusId) {
        for (EnumCommissionEarningStatus enumCommissionEarningStatus : EnumCommissionEarningStatus.values()) {
            if (enumCommissionEarningStatus.getId().equals(statusId)) {
                return enumCommissionEarningStatus;
            }
        }
        return null;
    }
}
