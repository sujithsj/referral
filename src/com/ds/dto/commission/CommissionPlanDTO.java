package com.ds.dto.commission;

import com.ds.domain.commission.CommissionPlan;

/**
 * @author adlakha.vaibhav
 */
public class CommissionPlanDTO {


  private Long commissionPlanId;
  private Long commissionCurrencyId;
  private Long commissionStrategyId;

  private Boolean tiered;
  private Boolean autoApproveComm ;
  private String companyShortName;
  private Double initCom;
  private Double recurCom;
  private Double tier1InitCom;
  private Double tier1RecurCom;
  private Double tier2InitCom;
  private Double tier2RecurCom;
  private Double tier3InitCom;
  private Double tier3RecurCom;
  private Long limitRecurCommDays;
  private Long limitRecurCommTxn;
  private Double recurCommGrade;


  public CommissionPlanDTO(CommissionPlan commissionPlan) {


    this.commissionPlanId = commissionPlan.getId();
    if (commissionPlan.getCommissionCurrency() != null) {
      this.commissionCurrencyId = commissionPlan.getCommissionCurrency().getId();
    }
    this.commissionStrategyId = commissionPlan.getCommissionStrategy().getId();

    this.tiered = commissionPlan.isTiered();
    this.autoApproveComm = commissionPlan.isAutoApproveComm();
    this.companyShortName = commissionPlan.getCompanyShortName();
    this.initCom = commissionPlan.getInitCom();
    this.recurCom = commissionPlan.getRecurCom();
    this.tier1InitCom = commissionPlan.getTier1InitCom();
    this.tier1RecurCom = commissionPlan.getTier2RecurCom();
    this.tier2InitCom = commissionPlan.getTier2InitCom();
    this.tier2RecurCom = commissionPlan.getTier2RecurCom();
    this.tier3InitCom = commissionPlan.getTier3InitCom();
    this.tier3RecurCom = commissionPlan.getTier3RecurCom();
    this.limitRecurCommDays = commissionPlan.getLimitRecurCommDays();
    this.limitRecurCommTxn = commissionPlan.getLimitRecurCommTxn();
    this.recurCommGrade = commissionPlan.getRecurCommGrade();

  }

  public CommissionPlan extractCommissionPlan(){
    CommissionPlan commissionPlan = new CommissionPlan();
    syncToCommissionPlan(commissionPlan);
    return commissionPlan;
  }

  public void syncToCommissionPlan(CommissionPlan commissionPlan){
    commissionPlan.setTiered(this.tiered);
    commissionPlan.setAutoApproveComm(this.autoApproveComm);
    commissionPlan.setInitCom(this.initCom);
    commissionPlan.setRecurCom(this.recurCom);
    commissionPlan.setTier1InitCom(this.tier1InitCom);
    commissionPlan.setTier1RecurCom(this.tier1RecurCom);
    commissionPlan.setTier2InitCom(this.tier2InitCom);
    commissionPlan.setTier2RecurCom(this.tier2RecurCom);
    commissionPlan.setTier3InitCom(this.tier3InitCom);
    commissionPlan.setTier3RecurCom(this.tier3RecurCom);
    commissionPlan.setLimitRecurCommDays(this.limitRecurCommDays);
    commissionPlan.setLimitRecurCommTxn(this.limitRecurCommTxn);
    commissionPlan.setRecurCommGrade(this.recurCommGrade);
  }


  public Long getCommissionPlanId() {
    return commissionPlanId;
  }

  public void setCommissionPlanId(Long commissionPlanId) {
    this.commissionPlanId = commissionPlanId;
  }

  public Long getCommissionCurrencyId() {
    return commissionCurrencyId;
  }

  public void setCommissionCurrencyId(Long commissionCurrencyId) {
    this.commissionCurrencyId = commissionCurrencyId;
  }

  public Long getCommissionStrategyId() {
    return commissionStrategyId;
  }

  public void setCommissionStrategyId(Long commissionStrategyId) {
    this.commissionStrategyId = commissionStrategyId;
  }

  public Boolean isTiered() {
    return tiered;
  }

  public void setTiered(Boolean tiered) {
    this.tiered = tiered;
  }

  public Boolean isAutoApproveComm() {
    return autoApproveComm;
  }

  public void setAutoApproveComm(Boolean autoApproveComm) {
    this.autoApproveComm = autoApproveComm;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public Double getInitCom() {
    return initCom;
  }

  public void setInitCom(Double initCom) {
    this.initCom = initCom;
  }

  public Double getRecurCom() {
    return recurCom;
  }

  public void setRecurCom(Double recurCom) {
    this.recurCom = recurCom;
  }

  public Double getTier1InitCom() {
    return tier1InitCom;
  }

  public void setTier1InitCom(Double tier1InitCom) {
    this.tier1InitCom = tier1InitCom;
  }

  public Double getTier1RecurCom() {
    return tier1RecurCom;
  }

  public void setTier1RecurCom(Double tier1RecurCom) {
    this.tier1RecurCom = tier1RecurCom;
  }

  public Double getTier2InitCom() {
    return tier2InitCom;
  }

  public void setTier2InitCom(Double tier2InitCom) {
    this.tier2InitCom = tier2InitCom;
  }

  public Double getTier2RecurCom() {
    return tier2RecurCom;
  }

  public void setTier2RecurCom(Double tier2RecurCom) {
    this.tier2RecurCom = tier2RecurCom;
  }

  public Double getTier3InitCom() {
    return tier3InitCom;
  }

  public void setTier3InitCom(Double tier3InitCom) {
    this.tier3InitCom = tier3InitCom;
  }

  public Double getTier3RecurCom() {
    return tier3RecurCom;
  }

  public void setTier3RecurCom(Double tier3RecurCom) {
    this.tier3RecurCom = tier3RecurCom;
  }

  public Long getLimitRecurCommDays() {
    return limitRecurCommDays;
  }

  public void setLimitRecurCommDays(Long limitRecurCommDays) {
    this.limitRecurCommDays = limitRecurCommDays;
  }

  public Long getLimitRecurCommTxn() {
    return limitRecurCommTxn;
  }

  public void setLimitRecurCommTxn(Long limitRecurCommTxn) {
    this.limitRecurCommTxn = limitRecurCommTxn;
  }

  public Double getRecurCommGrade() {
    return recurCommGrade;
  }

  public void setRecurCommGrade(Double recurCommGrade) {
    this.recurCommGrade = recurCommGrade;
  }
}
