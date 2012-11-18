package com.ds.constants;

import com.ds.domain.commission.CommissionStrategy;

import java.util.Arrays;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public enum EnumCommisionStrategy {

  /**
   * type of strategy 1: percentage 2 : dollars
   */

  RECUR_REV_SHARE(10L, "With a recurring share of the revenue", 1L),
  RECUR_COMMISSION(20L, "With a recurring commission", 2L),
  ONE_TIME_REV_SHARE(30L, "With a one-time share of the revenue", 1L),
  ONE_TIME_FLAT_COMM(30L, "With a one-time flat commission", 2L);

/*
"With a non-monetary reward (revenue share)
"With a non-monetary reward (flat)
*/

  private java.lang.String name;
  private java.lang.Long id;
  private java.lang.Long payoutType;


  EnumCommisionStrategy(Long id, java.lang.String name, Long payoutType) {
    this.name = name;
    this.id = id;
    this.payoutType = payoutType;

  }

  public Long getPayoutType() {
    return payoutType;
  }

  public String getName() {
    return name;
  }

  public java.lang.Long getId() {
    return id;
  }


  public static List<EnumCommisionStrategy> getAllCommissionStategies() {
    return Arrays.asList(
        EnumCommisionStrategy.RECUR_REV_SHARE

    );

  }


  public CommissionStrategy asCommissionStrategy() {
    CommissionStrategy commissionStrategy = new CommissionStrategy();
    commissionStrategy.setId(this.getId());
    commissionStrategy.setName(this.getName());
    return commissionStrategy;
  }

  public static EnumCommisionStrategy getById(Long strategyId) {
    for (EnumCommisionStrategy enumCommisionStrategy : EnumCommisionStrategy.values()) {
      if (enumCommisionStrategy.getId().equals(strategyId)) {
        return enumCommisionStrategy;
      }
    }
    return null;
  }
}
