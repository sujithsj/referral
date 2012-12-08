package com.ds.visualization.dto;

import java.math.BigInteger;

/**
 * @author adlakha.vaibhav
 */
public class CompanyTrendDTO {


  private Double impressionCount  = new Double("0");
  private BigInteger saleCount = new BigInteger("0");
  private BigInteger clickCount  = new BigInteger("0");


  public Double getImpressionCount() {
    return impressionCount;
  }

  public void setImpressionCount(Double impressionCount) {
    this.impressionCount = impressionCount;
  }

  public BigInteger getSaleCount() {
    return saleCount;
  }

  public void setSaleCount(BigInteger saleCount) {
    this.saleCount = saleCount;
  }

  public BigInteger getClickCount() {
    return clickCount;
  }

  public void setClickCount(BigInteger clickCount) {
    this.clickCount = clickCount;
  }
}
