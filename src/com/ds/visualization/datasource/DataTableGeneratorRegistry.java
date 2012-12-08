package com.ds.visualization.datasource;

import com.ds.impl.service.ServiceLocatorFactory;
import com.google.visualization.datasource.DataTableGenerator;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author adlakha.vaibhav
 */
public class DataTableGeneratorRegistry {

  private static ConcurrentHashMap<String, DataTableGenerator> dataTableGeneratorCache = new ConcurrentHashMap<String, DataTableGenerator>();

  public static DataTableGenerator getDataTableGenerator(String dsName){

    DataTableGenerator dataTableGenerator = dataTableGeneratorCache.get(dsName);

    if(dataTableGenerator == null){
      if("cIt".equalsIgnoreCase(dsName)){
        CompanyImpressionTrendChart companyImpressionTrendChart = (CompanyImpressionTrendChart) ServiceLocatorFactory.getService("CompanyImpressionTrendChart");
        dataTableGeneratorCache.put(dsName, companyImpressionTrendChart);
      }
      else if("ttAffByClick".equalsIgnoreCase(dsName)){
        TopTenAffiliateByClickPieChart topTenAffiliateByClickPieChart = (TopTenAffiliateByClickPieChart) ServiceLocatorFactory.getService("TopTenAffiliateByClickPieChart");
        dataTableGeneratorCache.put(dsName, topTenAffiliateByClickPieChart);
      }
       else if("ttAffByCommission".equalsIgnoreCase(dsName)){
        TopTenAffiliateByCommissionPieChart topTenAffiliateByCommissionPieChart = (TopTenAffiliateByCommissionPieChart) ServiceLocatorFactory.getService("TopTenAffiliateByCommissionPieChart");
        dataTableGeneratorCache.put(dsName, topTenAffiliateByCommissionPieChart);
      }
    }

    return dataTableGeneratorCache.get(dsName);

  }
}
