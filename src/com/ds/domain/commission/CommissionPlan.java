package com.ds.domain.commission;


import com.ds.domain.BaseDataObject;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "commission_plan")
@NamedQueries({
    @NamedQuery(name = "getCommissionPlanById", query = "select cp from CommissionPlan cp where cp.id = :commissionPlanId")
})
public class CommissionPlan extends BaseDataObject {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMMISSION_CURRENCY_ID")
  private CommissionCurrency commissionCurrency;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMMISSION_STRATEGY_ID", nullable = false)
  private CommissionStrategy commissionStrategy;

  @Column(name = "TIERED", nullable = false, length = 1)
  private Boolean tiered = false;

  @Column(name = "AUTO_APPROVE_COMM", nullable = false, length = 1)
  private Boolean autoApproveComm = true;

  @Column(name = "COMPANY_SHORT_NAME", nullable = false, length = 50)
  private String companyShortName;

  @Column(name = "ONE_TIME_COM", precision = 22, scale = 0)
  private Double oneTimeCom;

  @Column(name = "INIT_COM", precision = 22, scale = 0)
  private Double initCom;


  @Column(name = "RECUR_COM", precision = 22, scale = 0)
  private Double recurCom;

  @Column(name = "TIER_1_ONE_TIME_COM", precision = 22, scale = 0)
  private Double tier1OneTimeCom;

  @Column(name = "TIER_1_INIT_COM", precision = 22, scale = 0)
  private Double tier1InitCom;

  @Column(name = "TIER_1_RECUR_COM", precision = 22, scale = 0)
  private Double tier1RecurCom;


  @Column(name = "TIER_2_ONE_TIME_COM", precision = 22, scale = 0)
  private Double tier2OneTimeCom;

  @Column(name = "TIER_2_INIT_COM", precision = 22, scale = 0)
  private Double tier2InitCom;


  @Column(name = "TIER_2_RECUR_COM", precision = 22, scale = 0)
  private Double tier2RecurCom;

  @Column(name = "TIER_3_ONE_TIME_COM", precision = 22, scale = 0)
  private Double tier3OneTimeCom;
  
  @Column(name = "TIER_3_INIT_COM", precision = 22, scale = 0)
  private Double tier3InitCom;


  @Column(name = "TIER_3_RECUR_COM", precision = 22, scale = 0)
  private Double tier3RecurCom;


  @Column(name = "LIMIT_RECUR_COMM_DAYS")
  private Long limitRecurCommDays;


  @Column(name = "LIMIT_RECUR_COMM_TXN")
  private Long limitRecurCommTxn;


  @Column(name = "RECUR_COMM_GRADE", precision = 22, scale = 0)
  private Double recurCommGrade;


  @Version
  @Column(name = "LOCK_VERSION", nullable = false)
  private Long version;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CommissionCurrency getCommissionCurrency() {
    return this.commissionCurrency;
  }

  public void setCommissionCurrency(CommissionCurrency commissionCurrency) {
    this.commissionCurrency = commissionCurrency;
  }


  public Double getInitCom() {
    return this.initCom;
  }

  public void setInitCom(Double initCom) {
    this.initCom = initCom;
  }

  public Double getRecurCom() {
    return this.recurCom;
  }

  public void setRecurCom(Double recurCom) {
    this.recurCom = recurCom;
  }

  public Double getTier1InitCom() {
    return this.tier1InitCom;
  }

  public void setTier1InitCom(Double tier1InitCom) {
    this.tier1InitCom = tier1InitCom;
  }

  public Double getTier1RecurCom() {
    return this.tier1RecurCom;
  }

  public void setTier1RecurCom(Double tier1RecurCom) {
    this.tier1RecurCom = tier1RecurCom;
  }

  public Double getTier2InitCom() {
    return this.tier2InitCom;
  }

  public void setTier2InitCom(Double tier2InitCom) {
    this.tier2InitCom = tier2InitCom;
  }

  public Double getTier2RecurCom() {
    return this.tier2RecurCom;
  }

  public void setTier2RecurCom(Double tier2RecurCom) {
    this.tier2RecurCom = tier2RecurCom;
  }

  public Double getTier3InitCom() {
    return this.tier3InitCom;
  }

  public void setTier3InitCom(Double tier3InitCom) {
    this.tier3InitCom = tier3InitCom;
  }

  public Double getTier3RecurCom() {
    return this.tier3RecurCom;
  }

  public void setTier3RecurCom(Double tier3RecurCom) {
    this.tier3RecurCom = tier3RecurCom;
  }

  public Long getLimitRecurCommDays() {
    return this.limitRecurCommDays;
  }

  public void setLimitRecurCommDays(Long limitRecurCommDays) {
    this.limitRecurCommDays = limitRecurCommDays;
  }

  public Long getLimitRecurCommTxn() {
    return this.limitRecurCommTxn;
  }

  public void setLimitRecurCommTxn(Long limitRecurCommTxn) {
    this.limitRecurCommTxn = limitRecurCommTxn;
  }

  public Double getRecurCommGrade() {
    return this.recurCommGrade;
  }

  public void setRecurCommGrade(Double recurCommGrade) {
    this.recurCommGrade = recurCommGrade;
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

  public CommissionStrategy getCommissionStrategy() {
    return commissionStrategy;
  }

  public void setCommissionStrategy(CommissionStrategy commissionStrategy) {
    this.commissionStrategy = commissionStrategy;
  }

  public Double getOneTimeCom() {
    return oneTimeCom;
  }

  public void setOneTimeCom(Double oneTimeCom) {
    this.oneTimeCom = oneTimeCom;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Double getTier1OneTimeCom() {
    return tier1OneTimeCom;
  }

  public void setTier1OneTimeCom(Double tier1OneTimeCom) {
    this.tier1OneTimeCom = tier1OneTimeCom;
  }

  public Double getTier2OneTimeCom() {
    return tier2OneTimeCom;
  }

  public void setTier2OneTimeCom(Double tier2OneTimeCom) {
    this.tier2OneTimeCom = tier2OneTimeCom;
  }

  public Double getTier3OneTimeCom() {
    return tier3OneTimeCom;
  }

  public void setTier3OneTimeCom(Double tier3OneTimeCom) {
    this.tier3OneTimeCom = tier3OneTimeCom;
  }
}


