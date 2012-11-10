package com.ds.action.campaign;

import com.ds.web.action.BaseAction;
import com.ds.domain.marketing.MarketingMaterial;

import java.util.Date;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

/**
 * @author adlakha.vaibhav
 */
public class CampaignAction extends BaseAction {

  private String name;
  private Date startDate, endDate;

  private boolean visibleToAll;
  private int type;

  private Long campaignId;



  @DefaultHandler
  public Resolution createOrEditMarketingMaterial(){
       if(marketingMaterialId !=null){
         MarketingMaterial marketingMaterial =
       }
  }

}
