package com.ds.visualization.dto;

/**
 * @author adlakha.vaibhav
 */
public class CompanyTrendDTO {


  private Double impressionCount;
  private Double saleCount;
  private Double clickCount;


  public Double getImpressionCount() {
    return impressionCount;
  }

  public void setImpressionCount(Double impressionCount) {
    this.impressionCount = impressionCount;
  }

  public Double getSaleCount() {
    return saleCount;
  }

  public void setSaleCount(Double saleCount) {
    this.saleCount = saleCount;
  }

  public Double getClickCount() {
    return clickCount;
  }

  public void setClickCount(Double clickCount) {
    this.clickCount = clickCount;
  }
}
