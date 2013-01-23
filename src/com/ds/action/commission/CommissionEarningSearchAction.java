package com.ds.action.commission;

import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import com.ds.domain.marketing.MarketingMaterial;
import com.ds.domain.commission.CommissionEarning;
import com.ds.pact.service.commission.CommissionEarningService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 24, 2013
 * Time: 1:19:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CommissionEarningSearchAction extends BasePaginatedAction {

    private Long affiliateId;
    private Date startDate, endDate;

    private String status; //TODO: implement later
    private String customer;


    private Page commissionEarningPage;
    private List<CommissionEarning> commissionEarnings;


    @Autowired
    private CommissionEarningService commissionEarningService;
}
