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
    }

    return dataTableGeneratorCache.get(dsName);

  }
}
