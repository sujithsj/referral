package com.ds.constants;

import com.ds.domain.marketing.MarketingMaterialType;

import java.util.Arrays;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public enum EnumMarketingMaterialType {

  Banner(10L, "Banner"),
  TextLink(20L, "Text Link");

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
				EnumMarketingMaterialType.TextLink
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
      if(typeId.equals(enumMarketingMaterialType.getId())){
        return enumMarketingMaterialType;
      }
    }
    return null;
  }



}
