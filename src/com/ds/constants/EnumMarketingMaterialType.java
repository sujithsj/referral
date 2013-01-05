package com.ds.constants;

import com.ds.domain.marketing.MarketingMaterialType;

import java.util.Arrays;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public enum EnumMarketingMaterialType {

  ALL(-999L,"All"),     //to be used only on UI 
  Banner(10L, "Banner"),
  TextLink(20L, "Text Link"),
  TextAd(30L, "Text Ad");  


  private java.lang.String type;

  private java.lang.Long id;

  EnumMarketingMaterialType(Long id, java.lang.String type) {
    this.type = type;
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public java.lang.Long getId() {
    return id;
  }


  public static List<EnumMarketingMaterialType> getAllMMTypes() {
		return Arrays.asList(
				EnumMarketingMaterialType.Banner,
				EnumMarketingMaterialType.TextAd
				);

	}


  public MarketingMaterialType asMarketingMaterialType() {
    MarketingMaterialType marketingMaterialType  = new MarketingMaterialType();
    marketingMaterialType.setId(this.getId());
    marketingMaterialType.setType(this.getType());
    return marketingMaterialType;
  }

  public static EnumMarketingMaterialType getById(Long typeId){
    for(EnumMarketingMaterialType enumMarketingMaterialType : EnumMarketingMaterialType.values()){
      if(enumMarketingMaterialType.getId().equals(typeId)){
        return enumMarketingMaterialType;
      }
    }
    return null;
  }



}
