package com.ds.action.marketing;

import com.ds.domain.marketing.MarketingMaterial;
import com.ds.web.action.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class MarketingMaterialAction extends BaseAction {

  private String title;
  private int type;
  private String body;

  private Long marketingMaterialId;

  private String landingPageURL;


  @DefaultHandler
  public Resolution createOrEditMarketingMaterial(){
       if(marketingMaterialId !=null){
         MarketingMaterial marketingMaterial =
       }
  }
  
}
