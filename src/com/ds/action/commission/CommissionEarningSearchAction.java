package com.ds.action.commission;

import com.ds.web.action.BasePaginatedAction;
import com.ds.web.action.Page;
import com.ds.domain.marketing.MarketingMaterial;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 24, 2013
 * Time: 1:19:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommissionEarningSearchAction extends BasePaginatedAction {

    private Long affiliateId;
    private Date startDate, endDate;

    private String status; //TODO: implement later
    private String customer;


    private Page marketingMaterialPage;
  private List<MarketingMaterial> marketingMaterials;

}
