package com.ds.search.impl;

import com.ds.search.query.AbstractSearchQuery;
import com.ds.search.query.SortField;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 24, 2013
 * Time: 2:26:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommissionEarningQuery extends AbstractSearchQuery {


    private Long affiliateId;
    private String companyShortName, customer;
    private Date startDate, endDate;
    private Long commissionEarningStatusId;


    public CommissionEarningQuery setAffiliateId(Long affiliateId) {
        this.affiliateId = affiliateId;
        return this;
    }

    public CommissionEarningQuery setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
        return this;
    }

    public CommissionEarningQuery setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public CommissionEarningQuery setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public CommissionEarningQuery setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public CommissionEarningQuery setCommissionEarningStatusId(Long commissionEarningStatusId) {
        this.commissionEarningStatusId = commissionEarningStatusId;
        return this;
    }


    @Override
    protected String getBaseQuery() {
        StringBuilder queryStr = new StringBuilder("select ce from CommissionEarning ce join fetch ce.eventTracking  et where 1=1 ");


        /*private Long affiliateId;

        private Long commissionEarningStatusId;*/


        if (StringUtils.isNotBlank(customer)) {
            queryStr.append(" and et.customerId like  :customer ");
            getQueryParams().put("customer", "%" + customer + "%");
        }

        /*if(affiliateId !=null)*/

        if (StringUtils.isNotBlank(companyShortName)) {
            queryStr.append(" and c.companyShortName =  :companyShortName ");
            getQueryParams().put("companyShortName", companyShortName);
        }

        if (startDate != null && endDate != null) {
            queryStr.append(" and ce.earningDate >=  :startDate and ce.earningDate <=:endDate");
            getQueryParams().put("startDate", startDate);
            getQueryParams().put("endDate", endDate);
        }


        /*if (campaignType != null) {
            queryStr.append(" and c.campaignType.id =  :campaignTypeId ");
            getQueryParams().put("campaignTypeId", campaignType.getId());
        }*/

        queryStr.append(" and c.active =  :active ");
        queryStr.append(" and c.isPrivate =  :isPrivate ");
        /*getQueryParams().put("active", active);
        getQueryParams().put("isPrivate", isPrivate);*/


        return queryStr.toString();
    }

    @Override
    protected List<SortField> getSortFields() {
        List<SortField> sortFields = new ArrayList<SortField>();
        sortFields.add(new SortField("ed", "earningDate"));

        return sortFields;

    }

    @Override
    protected String getAliasToApply() {
        return "ce";
    }
}
